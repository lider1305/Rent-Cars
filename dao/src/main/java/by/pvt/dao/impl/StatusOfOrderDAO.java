package by.pvt.dao.impl;

import by.pvt.dao.IStatusOfOrderDAO;
import by.pvt.pojo.StatusOfOrder;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusOfOrderDAO extends BaseDAO<StatusOfOrder> implements IStatusOfOrderDAO<StatusOfOrder> {
    @Autowired
    public StatusOfOrderDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Override
    public List<StatusOfOrder> getAll() {
        Criteria criteria = getSession().createCriteria(StatusOfOrder.class);
        return (List<StatusOfOrder>) criteria.list();
    }
}
