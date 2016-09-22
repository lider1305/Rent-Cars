package by.pvt.dao.impl;

import by.pvt.VO.OrderSortingDTO;
import by.pvt.dao.IOrderDAO;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.util.SortingUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static by.pvt.constants.ConstantsValues.*;


@Repository
public class OrderDAO extends BaseDAO<Order> implements IOrderDAO<Order> {
    private static final String GET_ALL_ORDERS = "FROM Order O WHERE  O.client=:clientId";
    private static final String GET_CAR_FROM_PERIOD = "SELECT O.car FROM Order O WHERE  O.startDate >=:startDate AND O.endDate <=:endDate";


    @Autowired
    private OrderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List getClientOrders(long id, int page, int perPages){
            Query query = getSession().createQuery(GET_ALL_ORDERS);
            query.setLong(CLIENT_ID, id);
            query.setFirstResult(page);
            query.setMaxResults(perPages);
        return query.list();
    }

    @Override
    public List getAllRentCarForDate(Date start, Date end){
            Query query = getSession().createQuery(GET_CAR_FROM_PERIOD);
            query.setParameter(START_DATE, start);
            query.setParameter(END_DATE, end);
        return query.list();
    }
@Override
    public List<Order> getAll(int page, int perPage){
            Criteria criteria = getSession().createCriteria(Order.class);
            criteria.setFirstResult(page);
            criteria.setMaxResults(perPage);
    return (List<Order>) criteria.list();
    }

    @Override
    public List getOrderByFilter(int page, int perPage, OrderSortingDTO sort) {
        List result;
            SortingUtil sortingUtil = SortingUtil.getInstance();
        Criteria criteria = getSession().createCriteria(Order.class);
        if (sort.isASC()) {
            criteria.addOrder(org.hibernate.criterion.Order.asc(sortingUtil.orderSorting(sort)));
        } else {
            criteria.addOrder(org.hibernate.criterion.Order.desc(sortingUtil.orderSorting(sort)));
        }
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        result = criteria.list();
        return result;
    }

    @Override
    public long getCountOrders(Client client) {
        Criteria criteria = getSession().createCriteria(Order.class);
        criteria.setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq(CLIENT, client));
        return (long) criteria.uniqueResult();
    }
}
