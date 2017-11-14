package com.me.backup.service.impl;

import com.me.backup.dao.BaseDao;
import com.me.backup.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseDao<T> dao;


    public boolean save(T entity) {

        return dao.save(entity);
    }

    public boolean saveOrUpdate(T entity) {
        return dao.saveOrUpdate(entity);
    }

    public boolean saveOrUpdate(List<T> entityList) {
        return dao.saveOrUpdate(entityList);
    }

    public boolean save(List<T> entityList) {

        return dao.save(entityList);
    }

    public boolean update(T entity) {

        return dao.update(entity);
    }

    public boolean update(List<T> entityList) {
        return dao.update(entityList);
    }

    public boolean delete(T entity) {

        return dao.delete(entity);
    }

    public T findById(Serializable id) {
        return dao.findById(id);
    }

    public List<T> findByHql(String hql, Object... params) {
        return dao.findByHql(hql, params);
    }
}
