package by.pvt.dao;

import by.pvt.VO.CarDTO;
import by.pvt.VO.CarSortingDTO;
import by.pvt.pojo.Car;

import java.util.List;

/**
 * This class describe operations with database for POJO Car
 *
 * @param <T>
 */
public interface ICarDAO<T> extends DAO<T> {
    // return all cars that match the filter parameters and sorted in the desired order
    List getCarByFilter(CarDTO carDTO, int page, int perPage, CarSortingDTO sort);

    // return count of cars that match the filter parameters
    long getCountCars(CarDTO carDTO);

    //return all cars entities
    List<Car> getAll(int page, int perPage);
}
