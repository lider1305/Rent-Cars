package by.pvt.dao.impl;

import by.pvt.pojo.StatusOfCar;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusOfCarDAO extends BaseDAO<StatusOfCar> {
    @Autowired
    public StatusOfCarDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
