package by.pvt.dao.impl;

import by.pvt.dao.IStatusOfOrderDAO;
import by.pvt.pojo.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusOfOrderDAO extends BaseDAO<OrderStatus> implements IStatusOfOrderDAO<OrderStatus> {
    @Autowired
    public StatusOfOrderDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Override
    public List<OrderStatus> getAll() {
        Criteria criteria = getSession().createCriteria(OrderStatus.class);
        return (List<OrderStatus>) criteria.list();
    }
}
