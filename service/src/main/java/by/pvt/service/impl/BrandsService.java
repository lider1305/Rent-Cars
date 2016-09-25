package by.pvt.service.impl;

import by.pvt.dao.IBrandsDAO;
import by.pvt.exception.ExceptionMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Brands;
import by.pvt.util.SystemLogger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class BrandsService extends BaseService<Brands> {
    @Autowired
    IBrandsDAO brandsDAO;

    public List<Brands> getAll(int page, int perPage) throws ServiceException {
        List<Brands> all;
        try {
            all = brandsDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ExceptionMessages.ERROR_GET_LIST_OF_BRANDS);
        }
        return all;
    }
}
