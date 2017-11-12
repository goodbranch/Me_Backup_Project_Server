package com.me.backup.dao.impl;

import com.me.backup.dao.BaseDao;
import com.me.backup.dao.HibernateSessionFactory;
import com.me.backup.pojo.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {


    private Class<T> clazz;

    /**
     * 通过构造方法指定DAO的具体实现类
     */
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    public void save(T entity) {

        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();

    }

    public void update(T entity) {

        Session session = getSession();

        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T entity) {

        Session session = getSession();

        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public T findById(Serializable id) {
        Session session = getSession();

        session.beginTransaction();
        T userEntities = session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return userEntities;
    }

    public List<T> findByHql(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                Object o = params[i];
                query.setParameter(i, o);
            }
        }

        return query.list();
    }

}
