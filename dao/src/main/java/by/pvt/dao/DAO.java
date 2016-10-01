package by.pvt.dao;

import java.io.Serializable;

/**
 * This class describe CRUD operation with database
 *
 * @param <T> POJO which are inheritance from class Entity
 */
public interface DAO<T> {
    /**
     * create entity in database
     *
     * @param t
     */
    void save(T t);

    /**
     * get entity by id
     */
    T get(Class<T> clazz, Serializable id);

    /**
     * update entity
     *
     * @param t
     */
    void update(T t);

    /**
     * delete entity
     *
     * @param t
     */
    void delete(T t);


}
