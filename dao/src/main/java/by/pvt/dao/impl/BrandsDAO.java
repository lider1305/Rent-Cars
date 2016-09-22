package by.pvt.dao.impl;

import by.pvt.dao.IBrandsDAO;
import by.pvt.pojo.Brands;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandsDAO extends BaseDAO<Brands> implements IBrandsDAO<Brands> {
    @Autowired
    public BrandsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Brands> getAll(int page, int perPage) {
        Criteria criteria = getSession().createCriteria(Brands.class);
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        return (List<Brands>) criteria.list();
    }
}
