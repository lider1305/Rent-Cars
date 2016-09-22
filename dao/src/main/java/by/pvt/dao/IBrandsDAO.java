package by.pvt.dao;

import by.pvt.pojo.Brands;

import java.util.List;

/**
 * This class describe operations with database for POJO Brands
 * @param <T>
 */
public interface IBrandsDAO<T> extends DAO<T> {

    //get all brands entities
    List<Brands> getAll(int page, int perPage);
}
