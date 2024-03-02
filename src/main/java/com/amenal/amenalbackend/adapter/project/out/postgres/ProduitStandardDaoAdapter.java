package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.ProduitStandardEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.ProduitStandardRepository;
import com.amenal.amenalbackend.application.project.domain.ProduitStandard;
import com.amenal.amenalbackend.application.project.port.out.ProduitStandardDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProduitStandardDaoAdapter implements ProduitStandardDao {

	@Autowired
	private ProduitStandardRepository produitStandardRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProduitStandard findProduitStandardById(Integer id) {
		ProduitStandardEntity produitStandardEntity = produitStandardRepository.findById(id).get();
		ProduitStandard produitStandard = modelMapper.map(produitStandardEntity, ProduitStandard.class);
		return produitStandard;
	}

	@Override
	public List<ProduitStandard> findAllProduitStandards() {
		List<ProduitStandardEntity> produitStandardEntities = produitStandardRepository.findAll();
		return produitStandardEntities.stream().map(produitStandardEntity -> modelMapper.map(produitStandardEntity, ProduitStandard.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProduitStandard saveProduitStandard(ProduitStandard produitStandard) {
		ProduitStandardEntity produitStandardEntity = modelMapper.map(produitStandard, ProduitStandardEntity.class);
		ProduitStandardEntity savedEntity = produitStandardRepository.save(produitStandardEntity);
		return modelMapper.map(savedEntity, ProduitStandard.class);
	}

	@Override
	public ProduitStandard updateProduitStandard(ProduitStandard produitStandard) {
		ProduitStandardEntity existingEntity = produitStandardRepository.findById(produitStandard.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from ProduitStandard to existingEntity
		modelMapper.map(produitStandard, existingEntity);

		ProduitStandardEntity updatedEntity = produitStandardRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, ProduitStandard.class);
	}

	@Override
	public void deleteProduitStandard(Integer id) {
		// Check if ProduitStandard with the given ID exists
		ProduitStandardEntity produitStandardEntity = produitStandardRepository.findById(id).orElseThrow();

		// Delete the entity
		produitStandardRepository.delete(produitStandardEntity);
	}

}
