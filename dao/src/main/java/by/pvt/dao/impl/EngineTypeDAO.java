package by.pvt.dao.impl;

import by.pvt.dao.IEngineTypeDAO;
import by.pvt.pojo.EngineType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EngineTypeDAO extends BaseDAO<EngineType> implements IEngineTypeDAO<EngineType> {
    @Autowired
    public EngineTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<EngineType> getAll(int page, int perPage) {
        Criteria criteria = getSession().createCriteria(EngineType.class);
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        return (List<EngineType>) criteria.list();
    }
}
