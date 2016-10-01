package by.pvt.dao.impl;

import by.pvt.DTO.CarDTO;
import by.pvt.DTO.CarSortingDTO;
import by.pvt.dao.ICarDAO;
import by.pvt.pojo.Car;
import by.pvt.util.SortingUtil;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.pvt.constants.ConstantsValues.*;

@Repository
public class CarDAO extends BaseDAO<Car> implements ICarDAO<Car> {
    @Autowired
    private CarDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List getCarByFilter(CarDTO carDTO, int page, int perPage, CarSortingDTO sort) {
        List result;
        SortingUtil sortingUtil = SortingUtil.getInstance();
        Criteria criteria = getSession().createCriteria(Car.class);
        chooseParamsForCarCriteria(carDTO, criteria);
        if (sort.isASC()) {
            criteria.addOrder(Order.asc(sortingUtil.carSorting(sort)));
        } else {
            criteria.addOrder(Order.desc(sortingUtil.carSorting(sort)));
        }
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        result = criteria.list();
        return result;
    }


    @Override
    public long getCountCars(CarDTO carDTO) {
        Criteria criteria = getSession().createCriteria(Car.class);
        criteria.setProjection(Projections.rowCount());
        chooseParamsForCarCriteria(carDTO,criteria);
        return (long) criteria.uniqueResult();
    }

    @Override
    public List<Car> getAll(int page, int perPage) {

        Criteria criteria = getSession().createCriteria(Car.class);
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        return (List<Car>) criteria.list();
    }

    private void chooseParamsForCarCriteria(CarDTO carDTO, Criteria criteria) {
        if (carDTO.getBrand() != null) {
            criteria.add(Restrictions.like(BRAND, carDTO.getBrand()));
        }
        if (carDTO.getBodyType() != null) {
            criteria.add(Restrictions.like(BODY_TYPE, carDTO.getBodyType()));
        }
        if (carDTO.getEngineType() != null) {
            criteria.add(Restrictions.like(ENGINE_TYPE, carDTO.getEngineType()));
        }
        if (carDTO.getTransmissionType() != null) {
            criteria.add(Restrictions.like(TRANSMISSION_TYPE, carDTO.getTransmissionType()));
        }
        if (carDTO.getAmountFrom() != 0) {
            criteria.add(Restrictions.gt(AMOUNT, carDTO.getAmountFrom()));
        }
        if (carDTO.getAmountTo() != 0) {
            criteria.add(Restrictions.lt(AMOUNT, carDTO.getAmountTo()));
        }
        if (carDTO.getYearFrom() != 0) {
            criteria.add(Restrictions.gt(YEAR_OF_MANUFACTURE, carDTO.getYearFrom()));
        }
        if (carDTO.getYearTo() != 0) {
            criteria.add(Restrictions.lt(YEAR_OF_MANUFACTURE, carDTO.getYearTo()));
        }
    }
}
