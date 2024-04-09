package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Commande;
import com.amenal.amenalbackend.achat.core.port.out.CommandeDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.CommandeEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.CommandeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CommandeDaoAdapter implements CommandeDao {

	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Commande findCommandeById(Integer id) {
		CommandeEntity commandeEntity = commandeRepository.findById(id).get();
		Commande commande = modelMapper.map(commandeEntity, Commande.class);
		return commande;
	}

	@Override
	public List<Commande> findAllCommandes() {
		List<CommandeEntity> commandeEntities = commandeRepository.findAll();
		return commandeEntities.stream().map(commandeEntity -> modelMapper.map(commandeEntity, Commande.class))
				.collect(Collectors.toList());
	}

	@Override
	public Commande saveCommande(Commande commande) {
		CommandeEntity commandeEntity = modelMapper.map(commande, CommandeEntity.class);
		CommandeEntity savedEntity = commandeRepository.save(commandeEntity);
		return modelMapper.map(savedEntity, Commande.class);
	}

	@Override
	public Commande updateCommande(Commande commande) {
		CommandeEntity existingEntity = commandeRepository.findById(commande.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Commande to existingEntity
		modelMapper.map(commande, existingEntity);

		CommandeEntity updatedEntity = commandeRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Commande.class);
	}

	@Override
	public void deleteCommande(Integer id) {
		// Check if Commande with the given ID exists
		CommandeEntity commandeEntity = commandeRepository.findById(id).orElseThrow();

		// Delete the entity
		commandeRepository.delete(commandeEntity);
	}

}
