package by.pvt.service.impl;

import by.pvt.dao.DAO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.BaseEntity;
import by.pvt.service.IServices;
import by.pvt.util.SystemLogger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static by.pvt.exception.ExceptionMessages.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BaseService<T extends BaseEntity> implements IServices<T> {
    @Autowired
    private DAO<T> baseDAO;

    @Override
    public void save(T t) throws ServiceException {
        try {
            baseDAO.save(t);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_SAVE_OBJECT);
        }
    }

    @Override
    public T get(Class<T> clazz, Serializable id) throws ServiceException {
        T t;
        try {
            t = baseDAO.get(clazz, id);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_OBJECT);
        }
        return t;
    }

    @Override
    public void update(T t) throws ServiceException {
        try {
            baseDAO.update(t);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_UPDATE_OBJECT);
        }
    }

    @Override
    public void delete(T t) throws ServiceException {
        try {
            baseDAO.delete(t);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_DELETE_OBJECT);
        }
    }

}
