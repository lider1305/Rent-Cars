package by.pvt.dao;

import by.pvt.pojo.BodyType;

import java.util.List;

/**
 * This class describe operations with database for POJO BodyType
 *
 * @param <T>
 */
public interface IBodyTypeDAO<T> extends DAO<T> {
    /**
     * get all body types entities
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return list of body types
     */
    List<BodyType> getAll(int page, int perPage);
}
