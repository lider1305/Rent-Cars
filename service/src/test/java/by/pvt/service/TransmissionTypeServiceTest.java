package by.pvt.service;

import by.pvt.pojo.TransmissionType;
import by.pvt.service.impl.TransmissionTypeService;
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
public class TransmissionTypeServiceTest {
    private TransmissionType transmissionType;
    @Autowired
    private TransmissionTypeService transmissionTypeService;

    @Before
    public void setUp() throws Exception {
        //create transmission type
        transmissionType = new TransmissionType();
        transmissionType.setTransmissionType("AUTOMATE");
    }

    @After
    public void tearDown() throws Exception {
        transmissionType = null;
    }

    @Test
    public void save() throws Exception {
        transmissionTypeService.save(transmissionType);
        TransmissionType actual = transmissionTypeService.get(TransmissionType.class, 4);
        Assert.assertEquals(transmissionType, actual);
    }

    @Test
    public void get() throws Exception {
        transmissionTypeService.save(transmissionType);
        TransmissionType actual = transmissionTypeService.get(TransmissionType.class, 3);
        Assert.assertEquals(transmissionType, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        TransmissionType actual = transmissionTypeService.get(TransmissionType.class, 4);
        actual.setTransmissionType("Manual");
        Assert.assertNotSame(actual, transmissionType);
        transmissionTypeService.delete(transmissionTypeService.get(TransmissionType.class, 4));
    }

    @Test
    public void testDelete() throws Exception {
        TransmissionType actual = transmissionTypeService.get(TransmissionType.class, 3);
        transmissionTypeService.delete(actual);
        Assert.assertNull(transmissionTypeService.get(TransmissionType.class, 3));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = transmissionTypeService.getAll(0, 3);
        Assert.assertEquals(list.size(), 3);
    }
}