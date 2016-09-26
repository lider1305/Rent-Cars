package by.pvt.service;

import by.pvt.VO.PaginationDTO;
import by.pvt.pojo.Car;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.pojo.StatusOfOrder;
import by.pvt.service.impl.CarService;
import by.pvt.service.impl.ClientService;
import by.pvt.service.impl.OrderService;
import by.pvt.service.impl.StatusOfOrderService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderServiceTest {
    private Order order;
    private Client client;
    private StatusOfOrder statusOfOrder;
    PaginationDTO paginationDTO;
    @Autowired
    private StatusOfOrderService statusOfOrderService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;

    @Before
    public void setUp() throws Exception {
//create order
        order = new Order();
        order.setClient(clientService.get(Client.class, 1));
        order.setCar(carService.get(Car.class, 2));
        statusOfOrder = new StatusOfOrder();
        statusOfOrder.setStatus("ПРИНЯТ");
        order.setOrderStatus(statusOfOrder);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2322-02-05");
        order.setStartDate(date);
        order.setEndDate(date);
        order.setAmount(60);
        order.setMessage("HELLO");
        paginationDTO =new PaginationDTO();
        paginationDTO.setPages(0);
        paginationDTO.setPerPage(10);
    }

    @After
    public void tearDown() throws Exception {
        order = null;
    }

    @Test
    public void testGetAllRentCarForDate() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2322-02-05");
       List list = orderService.getAllRentCarForDate(date, date);
        Assert.assertEquals(list.size(),2);
    }

    @Test
    public void testGetAll() throws Exception {
        List all = orderService.getAll(0, 10);
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void save() throws Exception {
       statusOfOrderService.save(statusOfOrder);
       orderService.save(order);
        Order actual = orderService.get(Order.class, 2);
        Assert.assertEquals(actual, order);
    }

    @Test
    public void get() throws Exception {
       statusOfOrderService.save(statusOfOrder);
        orderService.save(order);
        Order actual = orderService.get(Order.class, 1);
        Assert.assertEquals(actual, order);
    }

    @Test
    public void testUpdate() throws Exception {
        Order actual = orderService.get(Order.class, 2);
        actual.setMessage("GOODBYE");
        Assert.assertNotSame(actual, order);
    }

    @Test
    public void testDelete() throws Exception {
        Order actual = orderService.get(Order.class, 1);
        orderService.delete(actual);
        Assert.assertNull(orderService.get(Order.class, 1));
    }

    @Test
    public void getClientOrders() throws Exception {
        statusOfOrderService.save(statusOfOrder);
        orderService.save(order);
        Client client = clientService.get(Client.class, 1);
        List list = orderService.getClientOrders(client.getId(),paginationDTO);
        Assert.assertEquals(list.size(), 3);
    }

}