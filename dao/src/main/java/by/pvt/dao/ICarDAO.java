package by.pvt.dao;

import by.pvt.DTO.CarDTO;
import by.pvt.DTO.CarSortingDTO;
import by.pvt.pojo.Car;

import java.util.List;

/**
 * This class describe operations with database for POJO Car
 *
 * @param <T>
 */
public interface ICarDAO<T> extends DAO<T> {
    /**
     * return all cars that match the filter parameters and sorted in the desired order
     *
     * @param carDTO  - object from UI for filter params
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @param sort    - param for sorting
     * @return list of cars
     */
    List getCarByFilter(CarDTO carDTO, int page, int perPage, CarSortingDTO sort);

    /**
     * return count of cars that match the filter parameters
     *
     * @param carDTO- object from UI for filter params
     * @return number of cars for this request
     */
    long getCountCars(CarDTO carDTO);

    /**
     * return all cars entities
     *
     * @param page    - number of row in database
     * @param perPage - count of shown items
     * @return list of all cars
     */
    List<Car> getAll(int page, int perPage);
}
