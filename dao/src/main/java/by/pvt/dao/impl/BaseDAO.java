package by.pvt.dao.impl;

import by.pvt.dao.DAO;
import by.pvt.pojo.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class BaseDAO<T extends BaseEntity> implements DAO<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public T get(Class<T> clazz, Serializable id) {
        T t = (T) getSession().get(clazz, id);
        return t;
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }
}
