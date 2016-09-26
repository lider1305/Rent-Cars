package by.pvt.service;

import by.pvt.pojo.EngineType;
import by.pvt.service.impl.EngineTypeService;
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
@ContextConfiguration(locations = {"/service-config-test.xml"})
@Transactional(propagation = Propagation.SUPPORTS)
public class EngineTypeServiceTest {
    @Autowired
    private EngineTypeService engineTypeService;
    private EngineType engineType;

    @Before
    public void setUp() throws Exception {
        //create engine type
        engineType = new EngineType();
        engineType.setEngineType("DIESEL");
    }

    @After
    public void tearDown() throws Exception {
        engineType = null;
    }

    @Test
    public void save() throws Exception {
        engineTypeService.save(engineType);
        EngineType actual = engineTypeService.get(EngineType.class, 4);
        assertEquals(engineType, actual);
    }

    @Test
    public void get() throws Exception {
        engineTypeService.save(engineType);
        EngineType actual = engineTypeService.get(EngineType.class, 3);
        assertEquals(engineType, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        EngineType actual = engineTypeService.get(EngineType.class, 4);
        actual.setEngineType("HYDROGEN");
        assertNotSame(actual, engineType);
       engineTypeService.delete(engineTypeService.get(EngineType.class, 4));
    }

    @Test
    public void testDelete() throws Exception {
        EngineType actual = engineTypeService.get(EngineType.class, 3);
        engineTypeService.delete(actual);
        assertNull(engineTypeService.get(EngineType.class, 3));

    }

    @Test
    public void testGetAll() throws Exception {
        List list = engineTypeService.getAll(0, 2);
        assertEquals(list.size(), 2);
    }
}