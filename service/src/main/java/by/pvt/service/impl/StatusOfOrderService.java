package by.pvt.service.impl;

import by.pvt.dao.IStatusOfOrderDAO;
import by.pvt.exception.ExceptionMessages;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.StatusOfOrder;
import by.pvt.util.SystemLogger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
public class StatusOfOrderService extends BaseService<StatusOfOrder> {
    @Autowired
    private IStatusOfOrderDAO statusOfOrderDAO;

    public List<StatusOfOrder> getAll() throws ServiceException {
        List<StatusOfOrder> all;
        try {
            all = statusOfOrderDAO.getAll();
        } catch (HibernateException e) {
            String message = ExceptionMessages.ERROR_GET_LIST_OF_ORDER_STATUSES;
            SystemLogger.getInstance().setLogger(getClass(), e);
            throw new ServiceException(message, e);
        }
        return all;
    }
}
