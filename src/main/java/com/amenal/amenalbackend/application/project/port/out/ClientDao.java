package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Client;

public interface ClientDao {
	Client findClientById(Integer id);
	
	List<Client> findAllClients();
	
	Client saveClient(Client client);
	
	Client updateClient(Client client);
	
	void deleteClient(Integer id);
	
}
