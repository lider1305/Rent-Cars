package by.pvt.service;

import by.pvt.pojo.BodyType;
import by.pvt.service.impl.BodyTypeService;
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
public class BodyTypeServiceTest {
    BodyType bodyType;
    @Autowired
    BodyTypeService bodyTypeService;
    @Before
    public void setUp() throws Exception {
        //create body type
        bodyType = new BodyType();
        bodyType.setBodyType("SEDAN");
    }

    @After
    public void tearDown() throws Exception {
        bodyType = null;
    }

    @Test
    public void save() throws Exception {
       bodyTypeService.save(bodyType);
        BodyType actual = bodyTypeService.get(BodyType.class, 2);
        Assert.assertEquals(bodyType, actual);
    }


    @Test
    public void get() throws Exception {
        bodyTypeService.save(bodyType);
        BodyType actual = bodyTypeService.get(BodyType.class, 1);
        Assert.assertEquals(bodyType, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        BodyType actual = bodyTypeService.get(BodyType.class, 2);
        actual.setBodyType("REFRIGERATOR");
        Assert.assertNotSame(actual, bodyType);
    }

    @Test
    public void testDelete() throws Exception {
        BodyType actual = bodyTypeService.get(BodyType.class, 1);
       bodyTypeService.delete(actual);
        Assert.assertNull(bodyTypeService.get(BodyType.class, 1));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = bodyTypeService.getAll(0, 3);
        Assert.assertEquals(list.size(), 1);
    }
}
