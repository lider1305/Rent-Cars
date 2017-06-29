package by.pvt.service.impl;

import by.pvt.pojo.Order;
import by.pvt.repository.OrderRepository;
import by.pvt.service.IOrderService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements IOrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	@Override
	public Order findById(Integer id) {
		return orderRepository.findOne(id);
	}
	
	@Override
	public void update(Order order) {
		orderRepository.save(order);
	}
	
	@Override
	public void delete(Order order) {
		orderRepository.delete(order);
	}
	
	@Override
	public List<Order> findAll() {
		return Lists.newArrayList(orderRepository.findAll());
	}
}
