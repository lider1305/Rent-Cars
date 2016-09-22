package by.pvt.dao;

import by.pvt.pojo.BodyType;

import java.util.List;

/**
 * This class describe operations with database for POJO BodyType
 * @param <T>
 */
public interface IBodyTypeDAO<T> extends DAO<T> {
    //get all body types entities
    List<BodyType> getAll(int page, int perPage) ;
}
