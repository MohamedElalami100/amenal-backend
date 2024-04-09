package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.DemandeDevis;
import com.amenal.amenalbackend.achat.core.port.out.DemandeDevisDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DemandeDevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DemandeDevisRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DemandeDevisDaoAdapter implements DemandeDevisDao {

	@Autowired
	private DemandeDevisRepository demandeDevisRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DemandeDevis findDemandeDevisById(Integer id) {
		DemandeDevisEntity demandeDevisEntity = demandeDevisRepository.findById(id).get();
		DemandeDevis demandeDevis = modelMapper.map(demandeDevisEntity, DemandeDevis.class);
		return demandeDevis;
	}

	@Override
	public List<DemandeDevis> findAllDemandeDeviss() {
		List<DemandeDevisEntity> demandeDevisEntities = demandeDevisRepository.findAll();
		return demandeDevisEntities.stream().map(demandeDevisEntity -> modelMapper.map(demandeDevisEntity, DemandeDevis.class))
				.collect(Collectors.toList());
	}

	@Override
	public DemandeDevis saveDemandeDevis(DemandeDevis demandeDevis) {
		DemandeDevisEntity demandeDevisEntity = modelMapper.map(demandeDevis, DemandeDevisEntity.class);
		DemandeDevisEntity savedEntity = demandeDevisRepository.save(demandeDevisEntity);
		return modelMapper.map(savedEntity, DemandeDevis.class);
	}

	@Override
	public DemandeDevis updateDemandeDevis(DemandeDevis demandeDevis) {
		DemandeDevisEntity existingEntity = demandeDevisRepository.findById(demandeDevis.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DemandeDevis to existingEntity
		modelMapper.map(demandeDevis, existingEntity);

		DemandeDevisEntity updatedEntity = demandeDevisRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DemandeDevis.class);
	}

	@Override
	public void deleteDemandeDevis(Integer id) {
		// Check if DemandeDevis with the given ID exists
		DemandeDevisEntity demandeDevisEntity = demandeDevisRepository.findById(id).orElseThrow();

		// Delete the entity
		demandeDevisRepository.delete(demandeDevisEntity);
	}

}
