package by.pvt.service.impl;

import by.pvt.dao.IClientDAO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Client;
import by.pvt.util.SystemLogger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.pvt.exception.ExceptionMessages.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class ClientService extends BaseService<Client> {
    private String message;
    @Autowired
    private IClientDAO clientDAO;


    public Client login(String email, String password) throws ServiceException {
        Client client;
        try {
            client = clientDAO.login(email, password);
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_CLIENT_BY_LOGIN_AND_PASSWORD;
            SystemLogger.getInstance().setLogger(getClass(), e,message);
            throw new ServiceException(message, e);
        }
        return client;
    }

    public String forgotPassword(String email) throws ServiceException {
        String password;
        try {
            password = clientDAO.forgotPassword(email);
        } catch (HibernateException e) {
            message = UNABLE_TO_GET_CLIENT_PASSWORD;
            SystemLogger.getInstance().setLogger(getClass(), e,message);
            throw new ServiceException(message, e);
        }
        return password;
    }

    public List<Client> getAll(int page, int perPage) throws ServiceException {
        List<Client> all;
        try {
            all = clientDAO.getAll(page, perPage);
        } catch (HibernateException e) {
            message = ERROR_GET_LIST_OF_CLIENT;
            SystemLogger.getInstance().setLogger(getClass(), e,message);
            throw new ServiceException(message, e);
        }
        return all;
    }
}