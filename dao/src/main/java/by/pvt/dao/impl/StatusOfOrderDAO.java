package by.pvt.dao.impl;

import by.pvt.pojo.StatusOfOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusOfOrderDAO extends BaseDAO<StatusOfOrder> {
    @Autowired
    public StatusOfOrderDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }
}
