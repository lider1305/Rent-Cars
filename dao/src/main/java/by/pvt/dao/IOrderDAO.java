package by.pvt.dao;

import by.pvt.DTO.OrderSortingDTO;
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
    /**
     * returns all client orders
     *
     * @param id       - client id
     * @param page     - number of row in database
     * @param perPages - count of shown items
     * @return list of client orders
     */
    List getClientOrders(long id, int page, int perPages);

    /**
     * returns all cars which are rented for those dates
     *
     * @param start -date of order start
     * @param end   - date of order end
     * @return list of orders with same date period
     */
    List getAllRentCarForDate(Date start, Date end);

    /**
     * returns  all orders
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return list of all orders
     */
    List<Order> getAll(int page, int perPage);

    /**
     * returns count of orders
     *
     * @return number of all orders
     */
    long getCountOfAllOrders();

    /**
     * returns all orders that match sorted in the desired order
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @param sort    - param for sorting
     * @return sorted list of orders
     */
    List getOrderByFilter(int page, int perPage, OrderSortingDTO sort,Client client);

    /**
     * returns count of orders
     *
     * @param client -object of client
     * @return number of client orders
     */
    long getCountOrders(Client client);

}
