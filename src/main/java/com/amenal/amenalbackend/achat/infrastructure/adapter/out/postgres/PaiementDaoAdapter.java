package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Paiement;
import com.amenal.amenalbackend.achat.core.port.out.PaiementDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.PaiementEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.PaiementRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PaiementDaoAdapter implements PaiementDao {

	@Autowired
	private PaiementRepository paiementRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Paiement findPaiementById(Integer id) {
		PaiementEntity paiementEntity = paiementRepository.findById(id).get();
		Paiement paiement = modelMapper.map(paiementEntity, Paiement.class);
		return paiement;
	}

	@Override
	public List<Paiement> findAllPaiements() {
		List<PaiementEntity> paiementEntities = paiementRepository.findAll();
		return paiementEntities.stream().map(paiementEntity -> modelMapper.map(paiementEntity, Paiement.class))
				.collect(Collectors.toList());
	}

	@Override
	public Paiement savePaiement(Paiement paiement) {
		PaiementEntity paiementEntity = modelMapper.map(paiement, PaiementEntity.class);
		PaiementEntity savedEntity = paiementRepository.save(paiementEntity);
		return modelMapper.map(savedEntity, Paiement.class);
	}

	@Override
	public Paiement updatePaiement(Paiement paiement) {
		PaiementEntity existingEntity = paiementRepository.findById(paiement.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Paiement to existingEntity
		modelMapper.map(paiement, existingEntity);

		PaiementEntity updatedEntity = paiementRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Paiement.class);
	}

	@Override
	public void deletePaiement(Integer id) {
		// Check if Paiement with the given ID exists
		PaiementEntity paiementEntity = paiementRepository.findById(id).orElseThrow();

		// Delete the entity
		paiementRepository.delete(paiementEntity);
	}

}
