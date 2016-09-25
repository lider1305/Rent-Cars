package by.pvt.service.impl;

import by.pvt.dao.IEngineTypeDAO;
import by.pvt.exception.ExceptionMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.EngineType;
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
public class EngineTypeService extends BaseService<EngineType> {

    @Autowired
    private IEngineTypeDAO engineTypeDAO;

    public List<EngineType> getAll(int page, int perPage) throws ServiceException {
        List<EngineType> all;
        try {
            all = engineTypeDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ExceptionMessages.ERROR_GET_LIST_OF_ENGINE);
        }
        return all;
    }
}