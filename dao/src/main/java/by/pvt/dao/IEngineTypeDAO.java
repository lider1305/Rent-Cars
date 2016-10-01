package by.pvt.dao;

import by.pvt.pojo.EngineType;

import java.util.List;

/**
 * This class describe operations with database for POJO EngineType
 *
 * @param <T>
 */
public interface IEngineTypeDAO<T> extends DAO<T> {
    /**
     * return all engine type from database
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return lisy of engine types
     */
    List<EngineType> getAll(int page, int perPage);
}
