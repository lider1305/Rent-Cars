package by.pvt.service;

import java.util.List;

public interface IBaseService<T> {
	// create or update entity in database
	T save(T t);
	
	// get entity by id
	T findById(Integer id);
	
	// update entity
	void update(T t);
	
	// delete entity
	void delete(T t);
	
	//get all
	List<T> findAll();
}
