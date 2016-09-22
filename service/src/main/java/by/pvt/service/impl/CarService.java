package by.pvt.service.impl;

import by.pvt.VO.CarDTO;
import by.pvt.VO.CarSortingDTO;
import by.pvt.dao.ICarDAO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Car;
import by.pvt.util.SystemLogger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.pvt.exception.ExceptionMessages.*;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
public class CarService extends BaseService<Car> {
    private String message;
    @Autowired
    private ICarDAO carDAO;

    public List getCarByFilter(CarDTO carDTO, int page, int perPage, CarSortingDTO sort) throws ServiceException {
        List result;
        try {
            result =carDAO.getCarByFilter(carDTO, page, perPage, sort);
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_LIST_OF_CARS;
            SystemLogger.getInstance().setLogger(getClass(), e,message);
            throw new ServiceException(message, e);
        }
        return result;
    }

    public long getCountCars(CarDTO carDTO) throws ServiceException {
        long count;
        try {
            count = carDAO.getCountCars(carDTO);
        } catch (HibernateException e) {
            message =  UNABLE_TO_GET_COUNT_OF_CARS;
            SystemLogger.getInstance().setLogger(getClass(), e,message);
            throw new ServiceException(message, e);
        }
        return count;
    }

    public List<Car> getAll(int page, int perPage) throws ServiceException {
        List<Car> all;
        try {
            all = carDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            String message = ERROR_GET_LIST_OF_CARS;
            SystemLogger.getInstance().setLogger(getClass(), e,message);
            throw new ServiceException(message, e);
        }
        return all;
    }
}