package by.pvt.service.impl;

import by.pvt.pojo.Roles;
import by.pvt.repository.RoleRepository;
import by.pvt.service.IRoleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleService implements IRoleService {
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Roles save(Roles roles) {
		return roleRepository.save(roles);
	}
	
	@Override
	public Roles findById(Integer id) {
		return roleRepository.findOne(id);
	}
	
	@Override
	public void update(Roles roles) {
		roleRepository.save(roles);
	}
	
	@Override
	public void delete(Roles roles) {
		roleRepository.delete(roles);
	}
	
	@Override
	public List<Roles> findAll() {
		return Lists.newArrayList(roleRepository.findAll());
	}
}
