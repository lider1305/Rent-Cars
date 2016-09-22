package by.pvt.dao;

import by.pvt.pojo.Brands;
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
public class BrandsDAOTest {
    @Autowired
    private IBrandsDAO brandsDAO;
    private Brands brands;
    @Autowired
    private SessionFactory sessionFactory;


    @Before
    @Transactional(propagation = Propagation.NEVER)
    public void setUp() throws Exception {
        //create brand
        brands = new Brands();
        brands.setBrandName("AUDI");

    }
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
    @After
    @Transactional(propagation = Propagation.NEVER)
    public void tearDown() throws Exception {
        brands = null;
        getSession().flush();
    }

    @Test
    public void save() throws Exception {
        brandsDAO.save(brands);
        Brands actual = (Brands) brandsDAO.get(Brands.class,2);
        Assert.assertEquals(brands, actual);
    }

    @Test
    public void get() throws Exception {
        brandsDAO.save(brands);
        Brands actual = (Brands) brandsDAO.get(Brands.class,1);
        Assert.assertEquals(brands, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Brands actual = (Brands) brandsDAO.get(Brands.class,2);
        actual.setBrandName("MAN");
        Assert.assertNotSame(actual,brands);
    }

    @Test
    public void testDelete() throws Exception {
        Brands actual = (Brands) brandsDAO.get(Brands.class,1);
        brandsDAO.delete(actual);
        Assert.assertNull(brandsDAO.get(Brands.class,1));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = brandsDAO.getAll(0,2);
        Assert.assertEquals(list.size(), 1);
    }

}