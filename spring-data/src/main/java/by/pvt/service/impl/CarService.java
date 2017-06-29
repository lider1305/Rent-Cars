package by.pvt.service.impl;

import by.pvt.pojo.Car;
import by.pvt.repository.CarRepository;
import by.pvt.service.ICarService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarService implements ICarService {
	@Autowired
	CarRepository carRepository;
	@Override
	public Car save(Car car) {
		return carRepository.save(car);
	}
	
	@Override
	public Car findById(Integer id) {
		return carRepository.findOne(id);
	}
	
	@Override
	public void update(Car car) {
		carRepository.save(car);
	}
	
	@Override
	public void delete(Car car) {
		carRepository.delete(car);
	}
	
	@Override
	public List<Car> findAll() {
		return Lists.newArrayList(carRepository.findAll());
	}
}
