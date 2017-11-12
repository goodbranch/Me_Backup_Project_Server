package com.me.backup.service.impl;

import com.me.backup.dao.BaseDao;
import com.me.backup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseDao<T> dao;


    public void save(T entity) {

        dao.save(entity);
    }

    public void update(T entity) {

        dao.update(entity);
    }

    public void delete(T entity) {

        dao.delete(entity);
    }

    public T findById(Serializable id) {
        return dao.findById(id);
    }

    public List<T> findByHql(String hql, Object... params){
        return dao.findByHql(hql, params);
    }
}
