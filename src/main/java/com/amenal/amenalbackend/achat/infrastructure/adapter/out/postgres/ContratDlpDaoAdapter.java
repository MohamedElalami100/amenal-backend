package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.ContratDlpEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.ContratDlpRepository;
import com.amenal.amenalbackend.achat.application.domain.ContratDlp;
import com.amenal.amenalbackend.achat.application.port.out.ContratDlpDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ContratDlpDaoAdapter implements ContratDlpDao {

	@Autowired
	private ContratDlpRepository contratDlpRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ContratDlp findContratDlpById(Integer id) {
		ContratDlpEntity contratDlpEntity = contratDlpRepository.findById(id).get();
		ContratDlp contratDlp = modelMapper.map(contratDlpEntity, ContratDlp.class);
		return contratDlp;
	}

	@Override
	public List<ContratDlp> findAllContratDlps() {
		List<ContratDlpEntity> contratDlpEntities = contratDlpRepository.findAll();
		return contratDlpEntities.stream().map(contratDlpEntity -> modelMapper.map(contratDlpEntity, ContratDlp.class))
				.collect(Collectors.toList());
	}

	@Override
	public ContratDlp saveContratDlp(ContratDlp contratDlp) {
		ContratDlpEntity contratDlpEntity = modelMapper.map(contratDlp, ContratDlpEntity.class);
		ContratDlpEntity savedEntity = contratDlpRepository.save(contratDlpEntity);
		return modelMapper.map(savedEntity, ContratDlp.class);
	}

	@Override
	public ContratDlp updateContratDlp(ContratDlp contratDlp) {
		ContratDlpEntity existingEntity = contratDlpRepository.findById(contratDlp.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from ContratDlp to existingEntity
		modelMapper.map(contratDlp, existingEntity);

		ContratDlpEntity updatedEntity = contratDlpRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, ContratDlp.class);
	}

	@Override
	public void deleteContratDlp(Integer id) {
		// Check if ContratDlp with the given ID exists
		ContratDlpEntity contratDlpEntity = contratDlpRepository.findById(id).orElseThrow();

		// Delete the entity
		contratDlpRepository.delete(contratDlpEntity);
	}

}
