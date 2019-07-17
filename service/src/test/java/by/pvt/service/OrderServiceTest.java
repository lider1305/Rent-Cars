package by.pvt.service;

import by.pvt.DTO.OrderDTO;
import by.pvt.DTO.PaginationDTO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.Car;
import by.pvt.pojo.Client;
import by.pvt.pojo.Order;
import by.pvt.pojo.OrderStatus;
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
    private OrderStatus statusOfOrder;
    PaginationDTO paginationDTO;
    Date date;
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
        statusOfOrder = new OrderStatus();
        statusOfOrder.setStatus("Completed");
        order.setOrderStatus(statusOfOrder);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date = formatter.parse("2322-02-05");
        order.setStartDate(date);
        order.setEndDate(date);
        order.setAmount(60);
        order.setMessage("HELLO");
        paginationDTO = new PaginationDTO();
        paginationDTO.setPages(0);
        paginationDTO.setPerPages(10);
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
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testGetAll() throws Exception {
        List all = orderService.getAll(0, 10);
        Assert.assertEquals(all.size(), 1);
    }

    @Test(expected = ServiceException.class)
    public void save() throws Exception {
        OrderDTO orderDTO= new OrderDTO();
        orderDTO.setCarId(2);
        orderDTO.setClientId(1);
        orderDTO.setEndDate(date);
        orderDTO.setStartDate(date);
        orderDTO.setMessage("HELLO");
        statusOfOrderService.save(statusOfOrder);
        orderService.save(orderDTO);
    }

    @Test
    public void get() throws Exception {
        statusOfOrderService.save(statusOfOrder);
        orderService.save(order);
        Order actual = orderService.get(Order.class, 1);
        Assert.assertEquals(actual, order);
    }

    @Test(expected = ServiceException.class)
    public void testUpdate() throws ServiceException {
        OrderDTO orderDTO= new OrderDTO();
        orderDTO.setCarId(2);
        orderDTO.setClientId(1);
        orderDTO.setEndDate(date);
        orderDTO.setStartDate(date);
        orderDTO.setMessage("HELLO");
        orderService.update(orderDTO);

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
        List list = orderService.getClientOrders(client.getId(), paginationDTO);
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void testGetCountOrder() throws ServiceException {
        long count = orderService.getCountOfAllOrders();
        Assert.assertEquals(count, 2);
    }
    @Test
    public  void checkCarForBooking() throws ServiceException {
        List<Car> rentCar = orderService.getAllRentCarForDate(order.getStartDate(), order.getEndDate());

        for (Car aRentCar : rentCar) {
            if (carService.get(Car.class, 2).getId() == aRentCar.getId()) {
                Assert.assertEquals(rentCar.size(),1);
            }
        }

    }
}