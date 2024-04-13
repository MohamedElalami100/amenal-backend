package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Fournisseur;
import com.amenal.amenalbackend.achat.core.port.out.FournisseurDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.FournisseurEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.FournisseurRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class FournisseurDaoAdapter implements FournisseurDao {

	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Fournisseur findFournisseurById(Integer id) {
		FournisseurEntity fournisseurEntity = fournisseurRepository.findById(id).get();
		Fournisseur fournisseur = modelMapper.map(fournisseurEntity, Fournisseur.class);
		return fournisseur;
	}

	@Override
	public List<Fournisseur> findAllFournisseurs() {
		List<FournisseurEntity> fournisseurEntities = fournisseurRepository.findAll();
		return fournisseurEntities.stream().map(fournisseurEntity -> modelMapper.map(fournisseurEntity, Fournisseur.class))
				.collect(Collectors.toList());
	}

	@Override
	public Fournisseur saveFournisseur(Fournisseur fournisseur) {
		FournisseurEntity fournisseurEntity = modelMapper.map(fournisseur, FournisseurEntity.class);
		FournisseurEntity savedEntity = fournisseurRepository.save(fournisseurEntity);
		return modelMapper.map(savedEntity, Fournisseur.class);
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur fournisseur) {
		FournisseurEntity existingEntity = fournisseurRepository.findById(fournisseur.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Fournisseur to existingEntity
		FournisseurEntity newEntity = modelMapper.map(fournisseur, FournisseurEntity.class);

		FournisseurEntity updatedEntity = fournisseurRepository.save(newEntity);
		return modelMapper.map(updatedEntity, Fournisseur.class);
	}

	@Override
	public void deleteFournisseur(Integer id) {
		// Check if Fournisseur with the given ID exists
		FournisseurEntity fournisseurEntity = fournisseurRepository.findById(id).orElseThrow();

		// Delete the entity
		fournisseurRepository.delete(fournisseurEntity);
	}

}
