package by.pvt.service;


import by.pvt.exception.ServiceException;

import java.io.Serializable;

/**
 * Class describe service layer of CRUD operations
 * @param <T>
 */
public interface IServices<T> {
    // create or update entity in database
    void save(T t) throws ServiceException;

    // get entity by id
    T get(Class<T> clazz,Serializable id) throws ServiceException;

    // update entity
    void update(T t) throws ServiceException;

    // delete entity
    void delete(T t) throws ServiceException;

}
