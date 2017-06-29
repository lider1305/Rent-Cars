package by.pvt.service.impl;

import by.pvt.pojo.BodyType;
import by.pvt.repository.BodyTypeRepository;
import by.pvt.service.IBodyTypeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BodyTypeService implements IBodyTypeService {
	@Autowired
	BodyTypeRepository bodyTypeRepository;
	@Override
	public BodyType save(BodyType bodyType) {
		return bodyTypeRepository.save(bodyType);
	}
	
	@Override
	public BodyType findById(Integer id) {
		return bodyTypeRepository.findOne(id);
	}
	
	@Override
	public void update(BodyType bodyType) {
		bodyTypeRepository.save(bodyType);
	}
	
	@Override
	public void delete(BodyType bodyType) {
		bodyTypeRepository.delete(bodyType);
	}
	
	@Override
	public List<BodyType> findAll() {
		return Lists.newArrayList(bodyTypeRepository.findAll());
	}
}
