package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Dossier;
import com.amenal.amenalbackend.budget.core.port.out.DossierDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DossierEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DossierRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DossierDaoAdapter implements DossierDao {

	@Autowired
	private DossierRepository dossierRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Dossier findDossierById(Integer id) {
		DossierEntity dossierEntity = dossierRepository.findById(id).get();
		Dossier dossier = modelMapper.map(dossierEntity, Dossier.class);
		return dossier;
	}

	@Override
	public List<Dossier> findAllDossiers() {
		List<DossierEntity> dossierEntities = dossierRepository.findAll();
		return dossierEntities.stream().map(dossierEntity -> modelMapper.map(dossierEntity, Dossier.class))
				.collect(Collectors.toList());
	}

	@Override
	public Dossier saveDossier(Dossier dossier) {
		DossierEntity dossierEntity = modelMapper.map(dossier, DossierEntity.class);
		DossierEntity savedEntity = dossierRepository.save(dossierEntity);
		return modelMapper.map(savedEntity, Dossier.class);
	}

	@Override
	public Dossier updateDossier(Dossier dossier) {
		DossierEntity existingEntity = dossierRepository.findById(dossier.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Dossier to existingEntity
		modelMapper.map(dossier, existingEntity);

		DossierEntity updatedEntity = dossierRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Dossier.class);
	}

	@Override
	public void deleteDossier(Integer id) {
		// Check if Dossier with the given ID exists
		DossierEntity dossierEntity = dossierRepository.findById(id).orElseThrow();

		// Delete the entity
		dossierRepository.delete(dossierEntity);
	}

}
