package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Remise;
import com.amenal.amenalbackend.achat.core.port.out.RemiseDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.RemiseEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.RemiseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RemiseDaoAdapter implements RemiseDao {

	@Autowired
	private RemiseRepository remiseRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Remise findRemiseById(Integer id) {
		RemiseEntity remiseEntity = remiseRepository.findById(id).get();
		Remise remise = modelMapper.map(remiseEntity, Remise.class);
		return remise;
	}

	@Override
	public List<Remise> findAllRemises() {
		List<RemiseEntity> remiseEntities = remiseRepository.findAll();
		return remiseEntities.stream().map(remiseEntity -> modelMapper.map(remiseEntity, Remise.class))
				.collect(Collectors.toList());
	}

	@Override
	public Remise saveRemise(Remise remise) {
		RemiseEntity remiseEntity = modelMapper.map(remise, RemiseEntity.class);
		RemiseEntity savedEntity = remiseRepository.save(remiseEntity);
		return modelMapper.map(savedEntity, Remise.class);
	}

	@Override
	public Remise updateRemise(Remise remise) {
		RemiseEntity existingEntity = remiseRepository.findById(remise.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Remise to existingEntity
		modelMapper.map(remise, existingEntity);

		RemiseEntity updatedEntity = remiseRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Remise.class);
	}

	@Override
	public void deleteRemise(Integer id) {
		// Check if Remise with the given ID exists
		RemiseEntity remiseEntity = remiseRepository.findById(id).orElseThrow();

		// Delete the entity
		remiseRepository.delete(remiseEntity);
	}

}
