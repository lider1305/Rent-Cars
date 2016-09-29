package by.pvt.dao;

import by.pvt.pojo.TransmissionType;
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
@ContextConfiguration(locations = {"/dao-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class TransmissionTypeDAOTest {
    private TransmissionType transmissionType;
    @Autowired
    private ITransmissionTypeDAO transmissionTypeDAO;
    @Autowired
    private SessionFactory sessionFactory;


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Before
    public void setUp() throws Exception {
        //create transmission type
        transmissionType = new TransmissionType();
        transmissionType.setTransmissionType("AUTOMATE");
    }

    @After
    public void tearDown() throws Exception {
        transmissionType = null;
        getSession().flush();
    }

    @Test
    public void save() throws Exception {
        transmissionTypeDAO.save(transmissionType);
        TransmissionType actual = (TransmissionType) transmissionTypeDAO.get(TransmissionType.class, 4);
        Assert.assertEquals(transmissionType, actual);
    }

    @Test
    public void get() throws Exception {
        transmissionTypeDAO.save(transmissionType);
        TransmissionType actual = (TransmissionType) transmissionTypeDAO.get(TransmissionType.class, 3);
        Assert.assertEquals(transmissionType, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        TransmissionType actual = (TransmissionType) transmissionTypeDAO.get(TransmissionType.class, 4);
        actual.setTransmissionType("Manual");
        Assert.assertNotSame(actual, transmissionType);
        transmissionTypeDAO.delete(transmissionTypeDAO.get(TransmissionType.class, 4));
    }

    @Test
    public void testDelete() throws Exception {
        TransmissionType actual = (TransmissionType) transmissionTypeDAO.get(TransmissionType.class, 3);
        transmissionTypeDAO.delete(actual);
        Assert.assertNull(transmissionTypeDAO.get(TransmissionType.class, 3));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = transmissionTypeDAO.getAll(0, 3);
        Assert.assertEquals(list.size(), 3);
    }
}