package com.me.backup.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T findById(Serializable id) ;

    public List<T> findByHql(String hql, Object... params);

}
