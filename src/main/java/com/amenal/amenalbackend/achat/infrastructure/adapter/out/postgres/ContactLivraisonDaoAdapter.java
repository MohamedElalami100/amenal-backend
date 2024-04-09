package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.ContactLivraison;
import com.amenal.amenalbackend.achat.core.port.out.ContactLivraisonDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ContactLivraisonEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ContactLivraisonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ContactLivraisonDaoAdapter implements ContactLivraisonDao {

	@Autowired
	private ContactLivraisonRepository contactLivraisonRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ContactLivraison findContactLivraisonById(Integer id) {
		ContactLivraisonEntity contactLivraisonEntity = contactLivraisonRepository.findById(id).get();
		ContactLivraison contactLivraison = modelMapper.map(contactLivraisonEntity, ContactLivraison.class);
		return contactLivraison;
	}

	@Override
	public List<ContactLivraison> findAllContactLivraisons() {
		List<ContactLivraisonEntity> contactLivraisonEntities = contactLivraisonRepository.findAll();
		return contactLivraisonEntities.stream().map(contactLivraisonEntity -> modelMapper.map(contactLivraisonEntity, ContactLivraison.class))
				.collect(Collectors.toList());
	}

	@Override
	public ContactLivraison saveContactLivraison(ContactLivraison contactLivraison) {
		ContactLivraisonEntity contactLivraisonEntity = modelMapper.map(contactLivraison, ContactLivraisonEntity.class);
		ContactLivraisonEntity savedEntity = contactLivraisonRepository.save(contactLivraisonEntity);
		return modelMapper.map(savedEntity, ContactLivraison.class);
	}

	@Override
	public ContactLivraison updateContactLivraison(ContactLivraison contactLivraison) {
		ContactLivraisonEntity existingEntity = contactLivraisonRepository.findById(contactLivraison.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from ContactLivraison to existingEntity
		modelMapper.map(contactLivraison, existingEntity);

		ContactLivraisonEntity updatedEntity = contactLivraisonRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, ContactLivraison.class);
	}

	@Override
	public void deleteContactLivraison(Integer id) {
		// Check if ContactLivraison with the given ID exists
		ContactLivraisonEntity contactLivraisonEntity = contactLivraisonRepository.findById(id).orElseThrow();

		// Delete the entity
		contactLivraisonRepository.delete(contactLivraisonEntity);
	}

}
