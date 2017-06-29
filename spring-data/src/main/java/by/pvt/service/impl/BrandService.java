package by.pvt.service.impl;

import by.pvt.pojo.Brands;
import by.pvt.repository.BrandRepository;
import by.pvt.service.IBrandService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("brandService")
@Transactional
@Lazy
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BrandService implements IBrandService {
	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public Brands save(Brands brands) {
		return brandRepository.save(brands);
	}
	
	@Override
	public Brands findById(Integer id) {
		return brandRepository.findOne(id);
	}
	
	@Override
	public void update(Brands brands) {
		 brandRepository.save(brands);
	}
	
	@Override
	public void delete(Brands brands) {
		brandRepository.delete(brands);
	}
	
	@Override
	public List<Brands> findAll() {
		return Lists.newArrayList(brandRepository.findAll());
	}
}
