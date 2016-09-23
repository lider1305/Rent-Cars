package by.pvt.service.impl;

import by.pvt.dao.DAO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Entity;
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
public class BaseService<T extends Entity> implements IServices<T> {
    private String message;
    @Autowired
    private DAO<T> baseDAO;

    @Override
    public void save(T t) throws ServiceException {
        try {
            baseDAO.save(t);
        } catch (HibernateException e) {
            message = ERROR_SAVE + t.getClass();
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
    }

    @Override
    public T get(Class<T> clazz, Serializable id) throws ServiceException {
        T t;
        try {
            t = baseDAO.get(clazz, id);
        } catch (HibernateException e) {
            message = ERROR_GETTING + clazz.getClass();
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return t;
    }

    @Override
    public void update(T t) throws ServiceException {
        try {
            baseDAO.update(t);
        } catch (HibernateException e) {
            message = ERROR_UPDATE + t.getClass();
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
    }

    @Override
    public void delete(T t) throws ServiceException {
        try {
            baseDAO.delete(t);
        } catch (HibernateException e) {
            message = ERROR_DELETE + t.getClass();
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
    }

}
