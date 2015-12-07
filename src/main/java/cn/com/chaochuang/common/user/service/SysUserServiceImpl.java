package cn.com.chaochuang.common.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.security.util.UserTool;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysDepartmentRepository;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.webservice.server.ITransferOAService;

@Service
@Transactional
public class SysUserServiceImpl extends SimpleLongIdCrudRestService<SysUser> implements SysUserService {
    /** webservice 函数库 */
    @Autowired
    private ITransferOAService      transferOAService;

    @Autowired
    private SysUserRepository       repository;

    @Autowired
    private SysDepartmentRepository departmentRepository;

    @Override
    public SimpleDomainRepository<SysUser, Long> getRepository() {
        return repository;
    }

    @Override
    public SysUser saveCurrUser(SysUser user) {
        SysUser u = repository.findOne(Long.parseLong(UserTool.getInstance().getCurrentUserId()));
        u.setUserName(user.getUserName());
        u.setSex(user.getSex());
        u.setMobile(user.getMobile());
        repository.save(u);
        return u;
    }

    /**
     * @see cn.com.chaochuang.common.user.service.SysUserService#analysisDataChange(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void analysisDataChange(SysDataChange dataChange) {
        // 分析要修改的类型，若修改类型是update或add，需要通过webservice获取变更数据；若类型为delete则直接删除指定的记录
        if (OperationType.修改.getKey().equals(dataChange.getOperationType())
                        || OperationType.新增.getKey().equals(dataChange.getOperationType())) {
            // 从webservice获取json字符串
            String json = this.transferOAService.getChangeUser(dataChange.getChangeScript());

            // 将json转成department对象
            JsonMapper mapper = JsonMapper.getInstance();
            try {
                SysUser newUser = mapper.readValue(json, SysUser.class);
                if (newUser == null || newUser.getRmUserId() == null) {
                    return;
                }
                // 根据原系统部门编号查询变更数据是否存在
                SysUser curUser = this.repository.findByRmUserId(newUser.getRmUserId());
                if (curUser == null) {
                    curUser = new SysUser();
                    // 新增人员要设置人员的本地部门编号（depId）
                    SysDepartment dept = this.departmentRepository.findByRmDepId(newUser.getRmDepId());
                    newUser.setDepartment(dept);
                } else {
                    // 保证编号在BeanUtils.copyProperties后不被刷掉
                    newUser.setId(curUser.getId());
                }
                // 变更数据存在则获取对象后覆盖再保存
                NullBeanUtils.copyProperties(curUser, newUser);
                this.repository.save(curUser);
            } catch (Exception ex) {
                return;
            }
        } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
            String[] items = dataChange.getChangeScript().split("=");
            if (items != null && items.length == 2) {
                // 根据原系统编号找出要删除的对象进行删除
                SysUser curUser = this.repository.findByRmUserId(Long.valueOf(items[1]));
                if (curUser != null) {
                    this.repository.delete(curUser);
                }
            }
        }
    }

    /**
     * @see cn.com.chaochuang.common.user.service.SysUserService#findByRmUserId(java.lang.Long)
     */
    @Override
    public SysUser findByRmUserId(Long rmUserId) {
        if (rmUserId == null) {
            return null;
        }
        return repository.findByRmUserId(rmUserId);
    }

    /**
     * @see cn.com.chaochuang.common.user.service.SysUserService#findByRmUserInfoId(java.lang.Long)
     */
    @Override
    public SysUser findByRmUserInfoId(Long rmUserInfoId) {
        return repository.findByRmUserInfoId(rmUserInfoId);
    }

    /**
     * @see cn.com.chaochuang.common.user.service.SysUserService#selectUserIdByInfoId(java.lang.Long)
     */
    @Override
    public Long selectUserIdByInfoId(Long rmUserInfoId) {
        if (rmUserInfoId == null) {
            return null;
        }
        SysUser user = this.findByRmUserInfoId(rmUserInfoId);
        if (user == null) {
            return null;
        }
        return user.getRmUserId();
    }

}
