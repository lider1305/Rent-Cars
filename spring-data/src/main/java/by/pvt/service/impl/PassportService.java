package by.pvt.service.impl;

import by.pvt.pojo.Passports;
import by.pvt.repository.PassportRepository;
import by.pvt.service.IPassportService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassportService implements IPassportService {
	@Autowired
	PassportRepository passportRepository;
	
	@Override
	public Passports save(Passports passports) {
		return passportRepository.save(passports);
	}
	
	@Override
	public Passports findById(Integer id) {
		return passportRepository.findOne(id);
	}
	
	@Override
	public void update(Passports passports) {
		passportRepository.save(passports);
	}
	
	@Override
	public void delete(Passports passports) {
		passportRepository.delete(passports);
	}
	
	@Override
	public List<Passports> findAll() {
		return Lists.newArrayList(passportRepository.findAll());
	}
}
