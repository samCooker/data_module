package springcommon.jpa;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Cookie on 2016/3/26.
 */
public interface CrudRestService<T_Entity, ID extends Serializable> {

    SimpleDomainRepository<T_Entity, ID> getRepository();

    @Transactional
    void saveAndFlush(T_Entity e);

    @Transactional
    void save(T_Entity e);

    @Transactional
    T_Entity findOne(ID id);

    @Transactional
    Page<T_Entity> findAllByPage(int page, int pagesize);

    @Transactional
    Iterable<T_Entity> findAll();

    @Transactional
    long count();

    @Transactional
    void delete(ID id);

}
