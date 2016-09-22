package by.pvt.dao.impl;


import by.pvt.dao.ITransmissionTypeDAO;
import by.pvt.pojo.TransmissionType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransmissionTypeDAO extends BaseDAO<TransmissionType> implements ITransmissionTypeDAO<TransmissionType> {
    @Autowired
    public TransmissionTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<TransmissionType> getAll(int page, int perPage){
            Criteria criteria = getSession().createCriteria(TransmissionType.class);
            criteria.setFirstResult(page);
            criteria.setMaxResults(perPage);
        return (List<TransmissionType>) criteria.list();
    }
}
