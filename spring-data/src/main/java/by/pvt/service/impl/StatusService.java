package by.pvt.service.impl;

import by.pvt.pojo.OrderStatus;
import by.pvt.repository.StatusRepository;
import by.pvt.service.IStatusService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatusService implements IStatusService {
	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public OrderStatus save(OrderStatus orderStatus) {
		return statusRepository.save(orderStatus);
	}
	
	@Override
	public OrderStatus findById(Integer id) {
		return statusRepository.findOne(id);
	}
	
	@Override
	public void update(OrderStatus orderStatus) {
		statusRepository.save(orderStatus);
	}
	
	@Override
	public void delete(OrderStatus orderStatus) {
		statusRepository.delete(orderStatus);
	}
	
	@Override
	public List<OrderStatus> findAll() {
		return Lists.newArrayList(statusRepository.findAll());
	}
}
