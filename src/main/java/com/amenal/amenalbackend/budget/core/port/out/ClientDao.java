package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Client;

public interface ClientDao {
	Client findClientById(Integer id);
	
	List<Client> findAllClients();
	
	Client saveClient(Client client);
	
	Client updateClient(Client client);
	
	void deleteClient(Integer id);
	
}
