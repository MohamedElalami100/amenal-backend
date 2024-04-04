package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.ContratPlafondEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.ContratPlafondRepository;
import com.amenal.amenalbackend.achat.application.domain.ContratPlafond;
import com.amenal.amenalbackend.achat.application.port.out.ContratPlafondDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ContratPlafondDaoAdapter implements ContratPlafondDao {

	@Autowired
	private ContratPlafondRepository contratPlafondRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ContratPlafond findContratPlafondById(Integer id) {
		ContratPlafondEntity contratPlafondEntity = contratPlafondRepository.findById(id).get();
		ContratPlafond contratPlafond = modelMapper.map(contratPlafondEntity, ContratPlafond.class);
		return contratPlafond;
	}

	@Override
	public List<ContratPlafond> findAllContratPlafonds() {
		List<ContratPlafondEntity> contratPlafondEntities = contratPlafondRepository.findAll();
		return contratPlafondEntities.stream().map(contratPlafondEntity -> modelMapper.map(contratPlafondEntity, ContratPlafond.class))
				.collect(Collectors.toList());
	}

	@Override
	public ContratPlafond saveContratPlafond(ContratPlafond contratPlafond) {
		ContratPlafondEntity contratPlafondEntity = modelMapper.map(contratPlafond, ContratPlafondEntity.class);
		ContratPlafondEntity savedEntity = contratPlafondRepository.save(contratPlafondEntity);
		return modelMapper.map(savedEntity, ContratPlafond.class);
	}

	@Override
	public ContratPlafond updateContratPlafond(ContratPlafond contratPlafond) {
		ContratPlafondEntity existingEntity = contratPlafondRepository.findById(contratPlafond.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from ContratPlafond to existingEntity
		modelMapper.map(contratPlafond, existingEntity);

		ContratPlafondEntity updatedEntity = contratPlafondRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, ContratPlafond.class);
	}

	@Override
	public void deleteContratPlafond(Integer id) {
		// Check if ContratPlafond with the given ID exists
		ContratPlafondEntity contratPlafondEntity = contratPlafondRepository.findById(id).orElseThrow();

		// Delete the entity
		contratPlafondRepository.delete(contratPlafondEntity);
	}

}
