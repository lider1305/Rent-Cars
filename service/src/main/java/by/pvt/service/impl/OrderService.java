package by.pvt.service.impl;


import by.pvt.VO.PaginationDTO;
import by.pvt.VO.OrderSortingDTO;
import by.pvt.dao.IOrderDAO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.util.SystemLogger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static by.pvt.exception.ExceptionMessages.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class OrderService extends BaseService<Order> {

    private String message;
    @Autowired
    private IOrderDAO orderDAO;

    public List getClientOrders(long id, PaginationDTO pagination) throws ServiceException {
        List orders;
        try {
            int page = pagination.getPages();
            int perPages = pagination.getPerPages();
            orders = orderDAO.getClientOrders(id, page, perPages);
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_CLIENT_ORDERS;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return orders;
    }

    public List getAllRentCarForDate(Date start, Date end) throws ServiceException {
        List result;
        try {
            result = orderDAO.getAllRentCarForDate(start, end);
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_LIST_OF_CARS;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return result;
    }

    public List<Order> getAll(int page, int perPage) throws ServiceException {
        List<Order> all;
        try {
            all = orderDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            message = ERROR_GET_LIST_OF_ORDERS;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return all;
    }

    public List getOrdersByFilter(int page, int perPage, OrderSortingDTO sort) throws ServiceException {
        List result;
        try {
            result = orderDAO.getOrderByFilter(page, perPage, sort);
        } catch (HibernateException e) {
            message = ERROR_GET_LIST_OF_ORDERS;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return result;
    }

    public long getCountOrder(Client client) throws ServiceException {
        long count;
        try {
            count = orderDAO.getCountOrders(client);
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_COUNT_OF_ORDERS;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return count;
    }

    public long getCountOfAllOrders() throws ServiceException {
        long count;
        try {
            count = orderDAO.getCountOfAllOrders();
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_COUNT_OF_ORDERS;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return count;

    }
}