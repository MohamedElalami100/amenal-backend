package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DevisEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DevisRepository;
import com.amenal.amenalbackend.achat.application.domain.Devis;
import com.amenal.amenalbackend.achat.application.port.out.DevisDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DevisDaoAdapter implements DevisDao {

	@Autowired
	private DevisRepository devisRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Devis findDevisById(Integer id) {
		DevisEntity devisEntity = devisRepository.findById(id).get();
		Devis devis = modelMapper.map(devisEntity, Devis.class);
		return devis;
	}

	@Override
	public List<Devis> findAllDeviss() {
		List<DevisEntity> devisEntities = devisRepository.findAll();
		return devisEntities.stream().map(devisEntity -> modelMapper.map(devisEntity, Devis.class))
				.collect(Collectors.toList());
	}

	@Override
	public Devis saveDevis(Devis devis) {
		DevisEntity devisEntity = modelMapper.map(devis, DevisEntity.class);
		DevisEntity savedEntity = devisRepository.save(devisEntity);
		return modelMapper.map(savedEntity, Devis.class);
	}

	@Override
	public Devis updateDevis(Devis devis) {
		DevisEntity existingEntity = devisRepository.findById(devis.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Devis to existingEntity
		modelMapper.map(devis, existingEntity);

		DevisEntity updatedEntity = devisRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Devis.class);
	}

	@Override
	public void deleteDevis(Integer id) {
		// Check if Devis with the given ID exists
		DevisEntity devisEntity = devisRepository.findById(id).orElseThrow();

		// Delete the entity
		devisRepository.delete(devisEntity);
	}

}
