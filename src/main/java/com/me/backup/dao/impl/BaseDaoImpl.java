package com.me.backup.dao.impl;

import com.me.backup.dao.BaseDao;
import com.me.backup.dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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

    public boolean save(T entity) {

        List<T> list = new ArrayList<T>();
        list.add(entity);

        return save(list);
    }

    public boolean save(List<T> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return false;
        }
        Session session = getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (T entity : entityList) {
                session.save(entity);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }


    public boolean saveOrUpdate(T entity) {
        List<T> list = new ArrayList<T>();
        list.add(entity);
        return saveOrUpdate(list);
    }

    public boolean saveOrUpdate(List<T> entityList) {

        if (entityList == null || entityList.isEmpty()) {
            return false;
        }
        Session session = getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (T entity : entityList) {
                session.saveOrUpdate(entity);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }


    public boolean update(T entity) {
        List<T> list = new ArrayList<T>();
        list.add(entity);
        return update(list);
    }

    public boolean update(List<T> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return false;
        }
        Session session = getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (T entity : entityList) {
                session.update(entity);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    public boolean delete(T entity) {

        Session session = getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    public T findById(Serializable id) {
        Session session = getSession();

        session.beginTransaction();
        T entity = session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return entity;
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
