package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.ContactFournisseur;
import com.amenal.amenalbackend.achat.core.port.out.ContactFournisseurDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ContactFournisseurEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ContactFournisseurRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ContactFournisseurDaoAdapter implements ContactFournisseurDao {

	@Autowired
	private ContactFournisseurRepository contactFournisseurRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ContactFournisseur findContactFournisseurById(Integer id) {
		ContactFournisseurEntity contactFournisseurEntity = contactFournisseurRepository.findById(id).get();
		ContactFournisseur contactFournisseur = modelMapper.map(contactFournisseurEntity, ContactFournisseur.class);
		return contactFournisseur;
	}

	@Override
	public List<ContactFournisseur> findAllContactFournisseurs() {
		List<ContactFournisseurEntity> contactFournisseurEntities = contactFournisseurRepository.findAll();
		return contactFournisseurEntities.stream().map(contactFournisseurEntity -> modelMapper.map(contactFournisseurEntity, ContactFournisseur.class))
				.collect(Collectors.toList());
	}

	@Override
	public ContactFournisseur saveContactFournisseur(ContactFournisseur contactFournisseur) {
		ContactFournisseurEntity contactFournisseurEntity = modelMapper.map(contactFournisseur, ContactFournisseurEntity.class);
		ContactFournisseurEntity savedEntity = contactFournisseurRepository.save(contactFournisseurEntity);
		return modelMapper.map(savedEntity, ContactFournisseur.class);
	}

	@Override
	public ContactFournisseur updateContactFournisseur(ContactFournisseur contactFournisseur) {
		ContactFournisseurEntity existingEntity = contactFournisseurRepository.findById(contactFournisseur.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from ContactFournisseur to existingEntity
		ContactFournisseurEntity newEntity = modelMapper.map(contactFournisseur, ContactFournisseurEntity.class);

		ContactFournisseurEntity updatedEntity = contactFournisseurRepository.save(newEntity);
		return modelMapper.map(updatedEntity, ContactFournisseur.class);
	}

	@Override
	public void deleteContactFournisseur(Integer id) {
		// Check if ContactFournisseur with the given ID exists
		ContactFournisseurEntity contactFournisseurEntity = contactFournisseurRepository.findById(id).orElseThrow();

		// Delete the entity
		contactFournisseurRepository.delete(contactFournisseurEntity);
	}

}
