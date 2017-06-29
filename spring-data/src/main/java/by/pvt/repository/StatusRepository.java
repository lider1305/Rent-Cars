package by.pvt.repository;

import by.pvt.pojo.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<OrderStatus, Integer>{
}
