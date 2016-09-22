package by.pvt.dao;

import by.pvt.pojo.Car;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.pojo.StatusOfOrder;
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

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/dao-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderDAOTest {
    private Order order;
    private Client client;
    private StatusOfOrder statusOfOrder;
    @Autowired
    private DAO baseDAO;
    @Autowired
    private ICarDAO carDAO;
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IClientDAO clientDAO;
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
//create order
        order = new Order();
        order.setClient((Client) clientDAO.get(Client.class, 1));
        order.setCar((Car) carDAO.get(Car.class, 2));
        statusOfOrder = new StatusOfOrder();
        statusOfOrder.setStatus("ПРИНЯТ");
        order.setOrderStatus(statusOfOrder);
        order.setStartDate(new Date(1111111111L));
        order.setEndDate(new Date(2222222222L));
        order.setAmount(60);
        order.setMessage("HELLO");


    }

    @After
    @Transactional(propagation = Propagation.NEVER)
    public void tearDown() throws Exception {
        order = null;
        getSession().flush();
    }

    @Test
    public void testGetAllRentCarForDate() throws Exception {
       List list = orderDAO.getAllRentCarForDate(new Date(1111111111L), new Date(2222222222L));
        Assert.assertEquals(list.size(),2);
    }

    @Test
    public void testGetAll() throws Exception {
        List all = orderDAO.getAll(0, 10);
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void save() throws Exception {
        baseDAO.save(statusOfOrder);
        orderDAO.save(order);
        Order actual = (Order) orderDAO.get(Order.class, 2);
        Assert.assertEquals(actual, order);
    }

    @Test
    public void get() throws Exception {
        baseDAO.save(statusOfOrder);
        orderDAO.save(order);
        Order actual = (Order) orderDAO.get(Order.class, 1);
        Assert.assertEquals(actual, order);
    }

    @Test
    public void testUpdate() throws Exception {
        Order actual = (Order) orderDAO.get(Order.class, 2);
        actual.setMessage("GOODBYE");
        Assert.assertNotSame(actual, order);
    }

    @Test
    public void testDelete() throws Exception {
        Order actual = (Order) orderDAO.get(Order.class, 1);
        orderDAO.delete(actual);
        Assert.assertNull(orderDAO.get(Order.class, 1));
    }

    @Test
    public void getClientOrders() throws Exception {
        baseDAO.save(statusOfOrder);
        orderDAO.save(order);
        Client client = (Client) clientDAO.get(Client.class, 1);
        List list = orderDAO.getClientOrders(client.getId(), 0, 10);
        Assert.assertEquals(list.size(), 2);
    }
    @Test(expected = NullPointerException.class)
    public void getClientOrdersException(){
        List list = orderDAO.getClientOrders(client.getId(), 0, 10);
    }


}