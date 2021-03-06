package com.me.backup.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    boolean save(T entity);

    boolean save(List<T> entityList);

    boolean saveOrUpdate(T entity);

    boolean saveOrUpdate(List<T> entityList);

    boolean update(T entity);

    boolean update(List<T> entityList);

    boolean delete(T entity);

    T findById(Serializable id);

    List<T> findByHql(String hql, Object... params);

}
