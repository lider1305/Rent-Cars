package by.pvt.dao;

import by.pvt.pojo.StatusOfOrder;

import java.util.List;

/**
 * This class describe operations with database for POJO StatusOfOrder
 * @param <T>
 */
public interface IStatusOfOrderDAO<T> extends DAO<T> {
    //return all order statuses from database
    List<StatusOfOrder> getAll();
}
