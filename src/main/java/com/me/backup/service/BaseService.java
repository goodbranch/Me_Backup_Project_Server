package com.me.backup.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T findById(Serializable id);

    public List<T> findByHql(String hql, Object... params);
}
