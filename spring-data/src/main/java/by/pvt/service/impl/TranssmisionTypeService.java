package by.pvt.service.impl;

import by.pvt.pojo.TransmissionType;
import by.pvt.repository.TransmissionTypeRepository;
import by.pvt.service.ITransmissionTypeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TranssmisionTypeService implements ITransmissionTypeService {
	@Autowired
	TransmissionTypeRepository transmissionTypeRepository;
	
	@Override
	public TransmissionType save(TransmissionType transmissionType) {
		return transmissionTypeRepository.save(transmissionType);
	}
	
	@Override
	public TransmissionType findById(Integer id) {
		return transmissionTypeRepository.findOne(id);
	}
	
	@Override
	public void update(TransmissionType transmissionType) {
		transmissionTypeRepository.save(transmissionType);
	}
	
	@Override
	public void delete(TransmissionType transmissionType) {
		transmissionTypeRepository.delete(transmissionType);
	}
	
	@Override
	public List<TransmissionType> findAll() {
		return Lists.newArrayList(transmissionTypeRepository.findAll());
	}
}
