package by.pvt.dao.impl;

import by.pvt.dao.IClientDAO;
import by.pvt.pojo.Client;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAO extends BaseDAO<Client> implements IClientDAO<Client> {
    private static final String LOGIN = "FROM Client WHERE  email=:email AND password=:password";
    private static final String FORGOT_PASSWORD = "SELECT C.password FROM Client C WHERE  email=:email";

    @Autowired
    public ClientDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Client login(String email, String password){
            Query query = getSession().createQuery(LOGIN);
            query.setString("email", email);
            query.setString("password", password);
        return (Client) query.uniqueResult();
    }

    @Override
    public String forgotPassword(String email){
            Query query = getSession().createQuery(FORGOT_PASSWORD);
            query.setString("email", email);
        return (String) query.uniqueResult();
    }

    @Override
    public List<Client> getAll(int page, int perPage){
            Criteria criteria = getSession().createCriteria(Client.class);
            criteria.setFirstResult(page);
            criteria.setMaxResults(perPage);
        return (List<Client>) criteria.list();
    }
}
