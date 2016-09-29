package by.pvt.dao;

import by.pvt.pojo.EngineType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/dao-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class EngineTypeDAOTest {
    @Autowired
    private IEngineTypeDAO engineTypeDAO;
    private EngineType engineType;
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Before
    public void setUp() throws Exception {
        //create engine type
        engineType = new EngineType();
        engineType.setEngineType("DIESEL");
    }

    @After
    public void tearDown() throws Exception {
        engineType = null;
        getSession().flush();
    }

    @Test
    public void save() throws Exception {
        engineTypeDAO.save(engineType);
        EngineType actual = (EngineType) engineTypeDAO.get(EngineType.class, 4);
        assertEquals(engineType, actual);
    }

    @Test
    public void get() throws Exception {
        engineTypeDAO.save(engineType);
        int id = (int) getMax();
        EngineType actual = (EngineType) engineTypeDAO.get(EngineType.class, 3);
        assertEquals(engineType, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        EngineType actual = (EngineType) engineTypeDAO.get(EngineType.class, 4);
        actual.setEngineType("HYDROGEN");
        assertNotSame(actual, engineType);
        engineTypeDAO.delete(engineTypeDAO.get(EngineType.class, 4));
    }

    @Test
    public void testDelete() throws Exception {
        EngineType actual = (EngineType) engineTypeDAO.get(EngineType.class, 3);
        engineTypeDAO.delete(actual);
        assertNull(engineTypeDAO.get(EngineType.class, 3));

    }

    @Test
    public void testGetAll() throws Exception {
        List list = engineTypeDAO.getAll(0, 2);
        assertEquals(list.size(), 2);
    }

    public long getMax() {
        Criteria criteria = getSession().createCriteria(EngineType.class);
        criteria.setProjection(Projections.rowCount());
        return (long) criteria.uniqueResult();
    }
}