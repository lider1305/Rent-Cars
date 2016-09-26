package by.pvt.service;

import by.pvt.pojo.Brands;
import by.pvt.service.impl.BrandsService;
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
public class BrandsServiceTest {
    @Autowired
    private BrandsService brandsService;
    private Brands brands;

    @Before
    public void setUp() throws Exception {
        //create brand
        brands = new Brands();
        brands.setBrandName("AUDI");

    }

    @After
    public void tearDown() throws Exception {
        brands = null;
    }

    @Test
    public void save() throws Exception {
        brandsService.save(brands);
        Brands actual = brandsService.get(Brands.class, 2);
        Assert.assertEquals(brands, actual);
    }

    @Test
    public void get() throws Exception {
        brandsService.save(brands);
        Brands actual = brandsService.get(Brands.class, 1);
        Assert.assertEquals(brands, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Brands actual = brandsService.get(Brands.class, 2);
        actual.setBrandName("MAN");
        Assert.assertNotSame(actual, brands);
    }

    @Test
    public void testDelete() throws Exception {
        Brands actual = brandsService.get(Brands.class, 1);
        brandsService.delete(actual);
        Assert.assertNull(brandsService.get(Brands.class, 1));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = brandsService.getAll(0, 2);
        Assert.assertEquals(list.size(), 1);
    }

}