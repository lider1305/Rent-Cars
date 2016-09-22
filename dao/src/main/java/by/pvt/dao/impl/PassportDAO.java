package by.pvt.dao.impl;

import by.pvt.pojo.Passports;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PassportDAO extends BaseDAO<Passports> {
    @Autowired
    private PassportDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
