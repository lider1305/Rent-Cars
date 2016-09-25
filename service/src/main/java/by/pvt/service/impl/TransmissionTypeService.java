package by.pvt.service.impl;


import by.pvt.dao.ITransmissionTypeDAO;
import by.pvt.exception.ExceptionMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.TransmissionType;
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
public class TransmissionTypeService extends BaseService<TransmissionType> {
    @Autowired
    private ITransmissionTypeDAO transmissionTypeDAO;

    public List<TransmissionType> getAll(int page, int perPage) throws ServiceException {
        List<TransmissionType> all;
        try {
            all = transmissionTypeDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(ExceptionMessages.ERROR_LIST_TRANSMISSIONS);
        }
        return all;
    }
}