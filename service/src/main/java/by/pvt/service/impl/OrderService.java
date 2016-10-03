package by.pvt.service.impl;

import by.pvt.util.DateAndAmount;
import by.pvt.DTO.OrderDTO;
import by.pvt.DTO.OrderSortingDTO;
import by.pvt.DTO.PaginationDTO;
import by.pvt.dao.IOrderDAO;
import by.pvt.exception.ExceptionMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Car;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.pojo.OrderStatus;
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
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private CarService carService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private StatusOfOrderService statusOfOrderService;

    public List getClientOrders(long id, PaginationDTO pagination) throws ServiceException {
        List orders;
        try {
            int page = pagination.getPages();
            int perPages = pagination.getPerPages();
            orders = orderDAO.getClientOrders(id, page, perPages);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_LIST_OF_ORDERS);
        }
        return orders;
    }

    public List getAllRentCarForDate(Date start, Date end) throws ServiceException {
        List result;
        try {
            result = orderDAO.getAllRentCarForDate(start, end);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_ALL_ORDERS);
        }
        return result;
    }

    public List<Order> getAll(int page, int perPage) throws ServiceException {
        List<Order> all;
        try {
            all = orderDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_LIST_OF_ORDERS);
        }
        return all;
    }

    public List getOrdersByFilter(int page, int perPage, OrderSortingDTO sort,Client client) throws ServiceException {
        List result;
        try {
            result = orderDAO.getOrderByFilter(page, perPage, sort, client);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_LIST_OF_ORDERS);
        }
        return result;
    }

    public long getCountOrder(Client client) throws ServiceException {
        long count;
        try {
            count = orderDAO.getCountOrders(client);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_COUNT);
        }
        return count;
    }

    public long getCountOfAllOrders() throws ServiceException {
        long count;
        try {
            count = orderDAO.getCountOfAllOrders();
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ERROR_GET_COUNT);
        }
        return count;

    }

    public void save(OrderDTO orderDTO) throws ServiceException {
        Order order = new Order();
        order = setSomeParamsToOrder(orderDTO, order);
        //check car for null
        if (orderDTO.getCarId() == 0) {
            throw new ServiceException(ExceptionMessages.PARAM_NO_CHOSEN);
        }
        order.setCar(carService.get(Car.class, orderDTO.getCarId()));
        //check booked car on selected dates
        if (isCarReserved(order.getCar(), orderDTO.getStartDate(), orderDTO.getEndDate())) {
            throw new ServiceException(ExceptionMessages.PARAM_CAR_IS_RENT);
        }
        //count total order price
        long amount = DateAndAmount.countTotalCostOfOrder(order.getCar(), order.getStartDate(), order.getEndDate());
        order.setAmount(amount);
        super.save(order);
    }

    public void update(OrderDTO orderDTO) throws ServiceException {
        Order order = new Order();
        if (orderDTO.getCarId() != 0) {
            order.setId(orderDTO.getOrderId());
            //sets params to order
            order = setSomeParamsToOrder(orderDTO, order);
            //get selected car
            order.setCar(carService.get(Car.class, orderDTO.getCarId()));
            //check booked car on selected dates
            if (isCarReserved(order.getCar(), orderDTO.getStartDate(), orderDTO.getEndDate())) {
                throw new ServiceException(ExceptionMessages.PARAM_CAR_IS_RENT);
            }
            //count total order price
            long amount = DateAndAmount.countTotalCostOfOrder(order.getCar(), order.getStartDate(), order.getEndDate());
            order.setAmount(amount);
        } else {
            order = get(Order.class, orderDTO.getOrderId());
            //sets params to order
            order = setSomeParamsToOrder(orderDTO, order);
            //check booked car on selected dates
            if (orderDTO.getStartDate().getTime() != order.getStartDate().getTime() | orderDTO.getEndDate().getTime() != order.getEndDate().getTime()) {
                if (isCarReserved(order.getCar(), orderDTO.getStartDate(), orderDTO.getEndDate())) {
                    throw new ServiceException(ExceptionMessages.PARAM_CAR_IS_RENT);
                }
            }
        }
        super.update(order);
    }
    //the method checks whether the machine is reserved on this date
    public  boolean isCarReserved(Car car, Date start, Date end) throws ServiceException {
        List<Car> rentCar = null;
        try {
            rentCar = getAllRentCarForDate(start, end);
        } catch (ServiceException e) {
            throw new ServiceException(ERROR_GET_LIST_CARS);
        }
        for (Car aRentCar : rentCar) {
            if (car.getId() == aRentCar.getId()) {
                return true;
            }
        }
        return false;
    }

    private Order setSomeParamsToOrder(OrderDTO orderDTO, Order order) throws ServiceException {
        //checks dates for relevance
        if (DateAndAmount.checkDateOnActual(orderDTO.getStartDate())) {
            throw new ServiceException(ExceptionMessages.PARAM_WRONG_DATE);
        }
        if (DateAndAmount.checkEndDateOnActual(orderDTO.getStartDate(), orderDTO.getEndDate())) {
            throw new ServiceException(ExceptionMessages.PARAM_WRONG_DATE_END);
        }
        order.setClient(clientService.get(Client.class, orderDTO.getClientId()));
        order.setStartDate(orderDTO.getStartDate());
        order.setEndDate(orderDTO.getEndDate());
        order.setMessage(orderDTO.getMessage());
        order.setOrderStatus(statusOfOrderService.get(OrderStatus.class, 1));
        return order;
    }
}