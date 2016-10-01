package by.pvt.service;

import by.pvt.DTO.CarAddDTO;
import by.pvt.DTO.CarDTO;
import by.pvt.DTO.CarSortingDTO;
import by.pvt.exception.ServiceException;
import by.pvt.pojo.*;
import by.pvt.service.impl.*;
import by.pvt.util.SystemLogger;
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
public class CarServiceTest {
    @Autowired
    private BrandsService brandsService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private TransmissionTypeService transmissionTypeService;
    @Autowired
    private StatusOfCarService statusOfCarService;
    @Autowired
    private CarService carService;
    private Car car;
    private Brands brands;
    private BodyType bodyType;
    private EngineType engineType;
    private TransmissionType transmissionType;
    private CarStatus statusOfCar;
    private CarDTO carDTO;
    private CarSortingDTO carSortingDTO;


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
    }

    @Test
    public void save() throws Exception {
        CarAddDTO carDTO = new CarAddDTO();
        carDTO.setBrand(2);
        carDTO.setEngineType(1);
        carDTO.setBodyType(2);
        carDTO.setModel("A4");
        carDTO.setTransmissionType(2);
        carDTO.setYearOfManufacture(2014);
        carDTO.setAmount(30);
        brandsService.save(brands);
        bodyTypeService.save(bodyType);
        engineTypeService.save(engineType);
        transmissionTypeService.save(transmissionType);
        statusOfCarService.save(statusOfCar);
        carService.save(carDTO);
        Car actual = carService.get(Car.class, 2);
        Assert.assertNotSame(car, actual);
    }

    @Test
    public void testGet() throws Exception {
        brandsService.save(brands);
        bodyTypeService.save(bodyType);
        engineTypeService.save(engineType);
        transmissionTypeService.save(transmissionType);
        statusOfCarService.save(statusOfCar);
        carService.save(car);
        Car actual = carService.get(Car.class, 1);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Car actual = carService.get(Car.class, 2);
        actual.setYearOfManufacture(2015);
        Assert.assertNotSame(actual, car);

    }

    @Test
    public void testDelete() throws Exception {
        Car actual = carService.get(Car.class, 1);
        carService.delete(actual);
        Assert.assertNull(carService.get(Car.class, 1));
    }

    @Test
    public void testGetAll() throws Exception {
        List list = carService.getAll(0, 4);
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void getCarByFilter(){
        try {
            carDTO.setEngineType(engineTypeService.get(EngineType.class, 1));
            List list = carService.getCarByFilter(carDTO, 0, 10, carSortingDTO);
            Assert.assertEquals(list.size(), 2);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
        }
        }

        @Test
        public void getCountCars ()throws Exception {
            long count = carService.getCountCars(carDTO);
            Assert.assertEquals(count, 1);
        }
    }