package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.ReceptionEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.ReceptionRepository;
import com.amenal.amenalbackend.achat.application.domain.Reception;
import com.amenal.amenalbackend.achat.application.port.out.ReceptionDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ReceptionDaoAdapter implements ReceptionDao {

	@Autowired
	private ReceptionRepository receptionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Reception findReceptionById(Integer id) {
		ReceptionEntity receptionEntity = receptionRepository.findById(id).get();
		Reception reception = modelMapper.map(receptionEntity, Reception.class);
		return reception;
	}

	@Override
	public List<Reception> findAllReceptions() {
		List<ReceptionEntity> receptionEntities = receptionRepository.findAll();
		return receptionEntities.stream().map(receptionEntity -> modelMapper.map(receptionEntity, Reception.class))
				.collect(Collectors.toList());
	}

	@Override
	public Reception saveReception(Reception reception) {
		ReceptionEntity receptionEntity = modelMapper.map(reception, ReceptionEntity.class);
		ReceptionEntity savedEntity = receptionRepository.save(receptionEntity);
		return modelMapper.map(savedEntity, Reception.class);
	}

	@Override
	public Reception updateReception(Reception reception) {
		ReceptionEntity existingEntity = receptionRepository.findById(reception.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Reception to existingEntity
		modelMapper.map(reception, existingEntity);

		ReceptionEntity updatedEntity = receptionRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Reception.class);
	}

	@Override
	public void deleteReception(Integer id) {
		// Check if Reception with the given ID exists
		ReceptionEntity receptionEntity = receptionRepository.findById(id).orElseThrow();

		// Delete the entity
		receptionRepository.delete(receptionEntity);
	}

}