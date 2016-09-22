package by.pvt.dao.impl;

import by.pvt.dao.IBodyTypeDAO;
import by.pvt.pojo.BodyType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BodyTypeDAO extends BaseDAO<BodyType> implements IBodyTypeDAO<BodyType> {
    @Autowired
    public BodyTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<BodyType> getAll(int page, int perPage) {
        Criteria criteria = getSession().createCriteria(BodyType.class);
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        return (List<BodyType>) criteria.list();
    }
}