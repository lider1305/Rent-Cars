package by.pvt.service.impl;

import by.pvt.pojo.Client;
import by.pvt.repository.ClientRepository;
import by.pvt.service.IClientService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService implements IClientService {
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	@Override
	public Client findById(Integer id) {
		return clientRepository.findOne(id);
	}
	
	@Override
	public void update(Client client) {
		clientRepository.save(client);
	}
	
	@Override
	public void delete(Client client) {
		clientRepository.delete(client);
	}
	
	@Override
	public List<Client> findAll() {
		return Lists.newArrayList(clientRepository.findAll());
	}
}
