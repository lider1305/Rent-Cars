package by.pvt.dao;

import by.pvt.pojo.BodyType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/dao-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class BodyTypeDAOTest {
    @Autowired
    private IBodyTypeDAO bodyTypeDAO;
    private BodyType bodyType;
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional(propagation = Propagation.NEVER)
    protected Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Before
    @Transactional(propagation = Propagation.NEVER)
    public void setUp() throws Exception {
        //create body type
        bodyType = new BodyType();
        bodyType.setBodyType("SEDAN");


    }

    @After
    @Transactional(propagation = Propagation.NEVER)
    public void tearDown() throws Exception {
        bodyType = null;
        getSession().flush();
    }

    @Test
    public void save() throws Exception {
        bodyTypeDAO.save(bodyType);
        BodyType actual = (BodyType) bodyTypeDAO.get(BodyType.class,2);
        Assert.assertEquals(bodyType, actual);
    }

    @Test
    public void get() throws Exception {
        bodyTypeDAO.save(bodyType);
        BodyType actual = (BodyType) bodyTypeDAO.get(BodyType.class,1);
        Assert.assertEquals(bodyType, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        BodyType actual = (BodyType) bodyTypeDAO.get(BodyType.class,2);
        actual.setBodyType("REFRIGERATOR");
        Assert.assertNotSame(actual,bodyType);
    }

    @Test
    public void testDelete() throws Exception {
        BodyType actual = (BodyType) bodyTypeDAO.get(BodyType.class,1);
        bodyTypeDAO.delete(actual);
        Assert.assertNull(bodyTypeDAO.get(BodyType.class,1));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = bodyTypeDAO.getAll(0, 3);
        Assert.assertEquals(list.size(), 1);
    }

}