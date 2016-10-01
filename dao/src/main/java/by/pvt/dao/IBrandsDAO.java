package by.pvt.dao;

import by.pvt.pojo.Brands;

import java.util.List;

/**
 * This class describe operations with database for POJO Brands
 *
 * @param <T>
 */
public interface IBrandsDAO<T> extends DAO<T> {
    /**
     * get all brands entities
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return list of all brands
     */
    List<Brands> getAll(int page, int perPage);
}
