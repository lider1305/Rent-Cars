package by.pvt.dao.impl;

import by.pvt.pojo.Roles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends BaseDAO<Roles> {
    @Autowired
    private RoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
