package cn.com.chaochuang.common.syspower.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.common.syspower.reference.PowerType;
import cn.com.chaochuang.common.syspower.reference.PowerTypeConverter;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "power_id")) })
public class SysPower extends LongIdEntity {

    private static final long serialVersionUID = -3059154610506597099L;

    /** 权限显示顺序号 */
    private String            powerCode;

    /** 系统菜单名称 */
    private String            powerName;

    /** 系统菜单url */
    private String            url;

    /** 菜单类型 */
    @Convert(converter = PowerTypeConverter.class)
    private PowerType         powerTypeFlag    = PowerType.自动增加;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_power", joinColumns = { @JoinColumn(name = "power_id", referencedColumnName = "power_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
    private Set<SysRole>      roles;

    public String getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PowerType getPowerTypeFlag() {
        return powerTypeFlag;
    }

    public void setPowerTypeFlag(PowerType powerTypeFlag) {
        this.powerTypeFlag = powerTypeFlag;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

}
