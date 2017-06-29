package by.pvt.service.impl;

import by.pvt.pojo.EngineType;
import by.pvt.repository.EngineTypeRepository;
import by.pvt.service.IEngineTypeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EngineService implements IEngineTypeService {
	@Autowired
	EngineTypeRepository engineTypeRepository;
	
	@Override
	public EngineType save(EngineType engineType) {
		return engineTypeRepository.save(engineType);
	}
	
	@Override
	public EngineType findById(Integer id) {
		return engineTypeRepository.findOne(id);
	}
	
	@Override
	public void update(EngineType engineType) {
		engineTypeRepository.save(engineType);
	}
	
	@Override
	public void delete(EngineType engineType) {
		engineTypeRepository.delete(engineType);
	}
	
	@Override
	public List<EngineType> findAll() {
		return Lists.newArrayList(engineTypeRepository.findAll());
	}
}
