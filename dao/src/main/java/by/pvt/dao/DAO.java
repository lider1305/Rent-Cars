package by.pvt.dao;

import java.io.Serializable;

/**
 * This class describe CRUD operation with database
 * @param <T> POJO which are inheritance from class Entity
 */
public interface DAO<T> {
    // create entity in database
    void save(T t);

    // get entity by id
    T get(Class<T> clazz,Serializable id);

    // update entity
    void update(T t);

    // delete entity
    void delete(T t);


}
