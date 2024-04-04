package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Client;
import com.amenal.amenalbackend.budget.application.port.out.ClientDao;

public class ClientUseCase {
	
	private ClientDao clientDao;
	
	public ClientUseCase(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public Client findClientById(Integer id) {
	    return clientDao.findClientById(id);
	}

	public List<Client> findAllClients() {
		return clientDao.findAllClients();
	}
	
	public Client saveClient(Client client) {
		return clientDao.saveClient(client);
	}
	
	public Client updateClient(Client client) {
		return clientDao.updateClient(client);
	}
	
	public void deleteClient(Integer id) {
		clientDao.deleteClient(id);
	}
	

}
