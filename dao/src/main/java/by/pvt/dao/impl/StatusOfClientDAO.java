package by.pvt.dao.impl;

import by.pvt.pojo.ClientStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusOfClientDAO extends BaseDAO<ClientStatus> {
    @Autowired
    public StatusOfClientDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
