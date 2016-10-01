package by.pvt.service;

import by.pvt.pojo.OrderStatus;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderStatusServiceTest {
    private OrderStatus statusOfOrder;
    @Autowired
    private StatusOfOrderService statusOfOrderService;

    @Before
    public void setUp() throws Exception {
        //create transmission type
        statusOfOrder = new OrderStatus();
        statusOfOrder.setStatus("GET");
    }

    @After
    public void tearDown() throws Exception {
        statusOfOrder = null;
    }
    @Test
    public void testGetAll() throws Exception {
        statusOfOrderService.save(statusOfOrder);
        List list = statusOfOrderService.getAll();
        Assert.assertEquals(list.size(), 4);
    }
}
