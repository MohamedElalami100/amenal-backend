package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Client;
import com.amenal.amenalbackend.budget.core.port.out.ClientDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ClientEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientDaoAdapter implements ClientDao {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Client findClientById(Integer id) {
		ClientEntity clientEntity = clientRepository.findById(id).get();
		Client client = modelMapper.map(clientEntity, Client.class);
		return client;
	}

	@Override
	public List<Client> findAllClients() {
		List<ClientEntity> clientEntities = clientRepository.findAll();
		return clientEntities.stream().map(clientEntity -> modelMapper.map(clientEntity, Client.class))
				.collect(Collectors.toList());
	}

	@Override
	public Client saveClient(Client client) {
		ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
		ClientEntity savedEntity = clientRepository.save(clientEntity);
		return modelMapper.map(savedEntity, Client.class);
	}

	@Override
	public Client updateClient(Client client) {
		ClientEntity existingEntity = clientRepository.findById(client.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Client to existingEntity
		modelMapper.map(client, existingEntity);

		ClientEntity updatedEntity = clientRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Client.class);
	}

	@Override
	public void deleteClient(Integer id) {
		// Check if Client with the given ID exists
		ClientEntity clientEntity = clientRepository.findById(id).orElseThrow();

		// Delete the entity
		clientRepository.delete(clientEntity);
	}

}
