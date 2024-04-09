package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.CompteBanquaireEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.CompteBanquaireRepository;
import com.amenal.amenalbackend.achat.application.domain.CompteBanquaire;
import com.amenal.amenalbackend.achat.application.port.out.CompteBanquaireDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CompteBanquaireDaoAdapter implements CompteBanquaireDao {

	@Autowired
	private CompteBanquaireRepository compteBanquaireRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CompteBanquaire findCompteBanquaireById(Integer id) {
		CompteBanquaireEntity compteBanquaireEntity = compteBanquaireRepository.findById(id).get();
		CompteBanquaire compteBanquaire = modelMapper.map(compteBanquaireEntity, CompteBanquaire.class);
		return compteBanquaire;
	}

	@Override
	public List<CompteBanquaire> findAllCompteBanquaires() {
		List<CompteBanquaireEntity> compteBanquaireEntities = compteBanquaireRepository.findAll();
		return compteBanquaireEntities.stream().map(compteBanquaireEntity -> modelMapper.map(compteBanquaireEntity, CompteBanquaire.class))
				.collect(Collectors.toList());
	}

	@Override
	public CompteBanquaire saveCompteBanquaire(CompteBanquaire compteBanquaire) {
		CompteBanquaireEntity compteBanquaireEntity = modelMapper.map(compteBanquaire, CompteBanquaireEntity.class);
		CompteBanquaireEntity savedEntity = compteBanquaireRepository.save(compteBanquaireEntity);
		return modelMapper.map(savedEntity, CompteBanquaire.class);
	}

	@Override
	public CompteBanquaire updateCompteBanquaire(CompteBanquaire compteBanquaire) {
		CompteBanquaireEntity existingEntity = compteBanquaireRepository.findById(compteBanquaire.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from CompteBanquaire to existingEntity
		modelMapper.map(compteBanquaire, existingEntity);

		CompteBanquaireEntity updatedEntity = compteBanquaireRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, CompteBanquaire.class);
	}

	@Override
	public void deleteCompteBanquaire(Integer id) {
		// Check if CompteBanquaire with the given ID exists
		CompteBanquaireEntity compteBanquaireEntity = compteBanquaireRepository.findById(id).orElseThrow();

		// Delete the entity
		compteBanquaireRepository.delete(compteBanquaireEntity);
	}

}
