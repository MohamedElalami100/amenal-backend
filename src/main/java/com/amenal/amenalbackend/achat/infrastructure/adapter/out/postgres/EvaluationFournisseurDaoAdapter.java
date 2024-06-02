package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.EvaluationFournisseur;
import com.amenal.amenalbackend.achat.core.port.out.EvaluationFournisseurDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.EvaluationFournisseurEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.EvaluationFournisseurRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class EvaluationFournisseurDaoAdapter implements EvaluationFournisseurDao {

	@Autowired
	private EvaluationFournisseurRepository evaluationFournisseurRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EvaluationFournisseur findEvaluationFournisseurById(Integer id) {
		EvaluationFournisseurEntity evaluationFournisseurEntity = evaluationFournisseurRepository.findById(id).get();
		EvaluationFournisseur evaluationFournisseur = modelMapper.map(evaluationFournisseurEntity, EvaluationFournisseur.class);
		return evaluationFournisseur;
	}

	@Override
	public List<EvaluationFournisseur> findAllEvaluationFournisseurs() {
		List<EvaluationFournisseurEntity> evaluationFournisseurEntities = evaluationFournisseurRepository.findAll();
		return evaluationFournisseurEntities.stream().map(evaluationFournisseurEntity -> modelMapper.map(evaluationFournisseurEntity, EvaluationFournisseur.class))
				.collect(Collectors.toList());
	}

	@Override
	public EvaluationFournisseur saveEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur) {
		EvaluationFournisseurEntity evaluationFournisseurEntity = modelMapper.map(evaluationFournisseur, EvaluationFournisseurEntity.class);
		EvaluationFournisseurEntity savedEntity = evaluationFournisseurRepository.save(evaluationFournisseurEntity);
		return modelMapper.map(savedEntity, EvaluationFournisseur.class);
	}

	@Override
	public EvaluationFournisseur updateEvaluationFournisseur(EvaluationFournisseur evaluationFournisseur) {
		EvaluationFournisseurEntity existingEntity = evaluationFournisseurRepository.findById(evaluationFournisseur.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from EvaluationFournisseur to existingEntity
		EvaluationFournisseurEntity newEntity = modelMapper.map(evaluationFournisseur, EvaluationFournisseurEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		EvaluationFournisseurEntity updatedEntity = evaluationFournisseurRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, EvaluationFournisseur.class);
	}

	@Override
	public void deleteEvaluationFournisseur(Integer id) {
		// Check if EvaluationFournisseur with the given ID exists
		EvaluationFournisseurEntity evaluationFournisseurEntity = evaluationFournisseurRepository.findById(id).orElseThrow();

		// Delete the entity
		evaluationFournisseurRepository.delete(evaluationFournisseurEntity);
	}

}
