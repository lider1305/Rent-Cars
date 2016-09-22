package by.pvt.dao;

import by.pvt.VO.OrderSortingDTO;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;

import java.util.Date;
import java.util.List;

/**
 * This class describe operations with database for POJO Order
 *
 * @param <T>
 */
public interface IOrderDAO<T> extends DAO<T> {
    //return all client orders
    List getClientOrders(long id, int page, int perPages);

    //return all cars which are rented for those dates
    List getAllRentCarForDate(Date start, Date end);

    //return  all orders
    List<Order> getAll(int page, int perPage);

    // return all orders that match sorted in the desired order
    List getOrderByFilter(int page, int perPage, OrderSortingDTO sort);

    // return count of orders
    long getCountOrders(Client client);

}
