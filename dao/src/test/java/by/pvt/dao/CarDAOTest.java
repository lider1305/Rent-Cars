package by.pvt.dao;

import by.pvt.DTO.CarDTO;
import by.pvt.DTO.CarSortingDTO;
import by.pvt.pojo.*;
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
public class CarDAOTest {
    @Autowired
    private DAO baseDAO;
    @Autowired
    private ICarDAO carDAO;
    private Car car;
    private Brands brands;
    private BodyType bodyType;
    private EngineType engineType;
    private TransmissionType transmissionType;
    private CarStatus statusOfCar;
    private CarDTO carDTO;
    private CarSortingDTO carSortingDTO;
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Before
    public void setUp() throws Exception {
        //create brand
        brands = new Brands();
        brands.setBrandName("AUDI");
        //create body type
        bodyType = new BodyType();
        bodyType.setBodyType("SEDAN");
        //create engine type
        engineType = new EngineType();
        engineType.setEngineType("DIESEL");
        //create transmission type
        transmissionType = new TransmissionType();
        transmissionType.setTransmissionType("AUTOMATE");
        //create status for car
        statusOfCar = new CarStatus();
        statusOfCar.setStatus("RESERVED");
        //create car
        car = new Car();
        car.setModel("A4");
        car.setBrand(brands);
        car.setEngineType(engineType);
        car.setBodyType(bodyType);
        car.setTransmissionType(transmissionType);
        car.setStatus(statusOfCar);
        car.setYearOfManufacture(2014);
        car.setAmount(30);
        //params of car from UI
        carDTO = new CarDTO();
        //param for sorting cars for UI
        carSortingDTO = new CarSortingDTO();
        carSortingDTO.setBrand("brand");
        carSortingDTO.setEngineType("");
        carSortingDTO.setBodyType("");
        carSortingDTO.setTransmissionType("");
        carSortingDTO.setAmount("");
        carSortingDTO.setASC(true);
        carSortingDTO.setYear("");
    }

    @After
    public void tearDown() throws Exception {
        brands = null;
        bodyType = null;
        engineType = null;
        transmissionType = null;
        statusOfCar = null;
        car = null;
        getSession().flush();
    }

    @Test
    public void save() throws Exception {
        baseDAO.save(brands);
        baseDAO.save(bodyType);
        baseDAO.save(engineType);
        baseDAO.save(transmissionType);
        baseDAO.save(statusOfCar);
        carDAO.save(car);
        Car actual = (Car) carDAO.get(Car.class, 2);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void testGet() throws Exception {
        baseDAO.save(brands);
        baseDAO.save(bodyType);
        baseDAO.save(engineType);
        baseDAO.save(transmissionType);
        baseDAO.save(statusOfCar);
        carDAO.save(car);
        Car actual = (Car) carDAO.get(Car.class, 1);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Car actual = (Car) carDAO.get(Car.class, 2);
        actual.setYearOfManufacture(2015);
        Assert.assertNotSame(actual, car);

    }

    @Test
    public void testDelete() throws Exception {
        Car actual = (Car) carDAO.get(Car.class, 1);
        carDAO.delete(actual);
        Assert.assertNull(carDAO.get(Car.class, 1));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = carDAO.getAll(0, 4);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void getCarByFilter() throws Exception {
        carDTO.setEngineType((EngineType) baseDAO.get(EngineType.class,1));
         List list = carDAO.getCarByFilter(carDTO, 0, 10, carSortingDTO);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void getCountCars() throws Exception {
        long count=carDAO.getCountCars(carDTO);
        Assert.assertEquals(count,1);
    }
}