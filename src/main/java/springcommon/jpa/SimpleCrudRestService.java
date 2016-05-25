package springcommon.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Cookie on 2016/3/26.
 */
public abstract class SimpleCrudRestService<T_Entity, ID extends Serializable> implements CrudRestService<T_Entity, ID> {

    @Override
    @Transactional
    public void saveAndFlush(T_Entity e) {
        getRepository().saveAndFlush(e);
    }

    @Override
    @Transactional
    public void save(T_Entity e) {
        getRepository().save(e);
    }

    @Override
    @Transactional
    public T_Entity findOne(ID id) {
        return getRepository().findOne(id);
    }

    @Override
    @Transactional
    public Page<T_Entity> findAllByPage(int page, int pagesize) {
        PageRequest pageable = new PageRequest(page - 1, pagesize);
        return getRepository().findAll(pageable);
    }

    @Override
    @Transactional
    public Iterable<T_Entity> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional
    public long count() {
        return getRepository().count();
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getRepository().delete(id);
    }

}
