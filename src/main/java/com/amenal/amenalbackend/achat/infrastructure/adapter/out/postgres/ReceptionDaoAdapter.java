package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Reception;
import com.amenal.amenalbackend.achat.core.port.out.ReceptionDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ReceptionEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ReceptionRepository;
import com.amenal.amenalbackend.achat.infrastructure.dto.ReceptionDto;

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
	public ReceptionDto findReceptionById(Integer id) {
		ReceptionEntity receptionEntity = receptionRepository.findById(id).get();
		
		ReceptionDto receptionDto = modelMapper.map(receptionEntity, ReceptionDto.class);
		Reception reception = modelMapper.map(receptionEntity, Reception.class);

		// Set attributes for the ReceptionDto object
		receptionDto.setMntHt(reception.getMntHt());
		receptionDto.setMntTtc(reception.getMntTtc());
		receptionDto.setMntTva(reception.getMntTva());

		return receptionDto;
	}

	@Override
	public List<ReceptionDto> findAllReceptions() {
	    List<ReceptionEntity> receptionEntities = receptionRepository.findAll();
	    List<ReceptionDto> receptionDtos = new ArrayList<>();

	    for (ReceptionEntity receptionEntity : receptionEntities) {
	        ReceptionDto receptionDto = modelMapper.map(receptionEntity, ReceptionDto.class);
	        Reception reception = modelMapper.map(receptionEntity, Reception.class);

	        // Set attributes for the ReceptionDto object
	        receptionDto.setMntHt(reception.getMntHt());
	        receptionDto.setMntTtc(reception.getMntTtc());
	        receptionDto.setMntTva(reception.getMntTva());

	        receptionDtos.add(receptionDto);
	    }

	    return receptionDtos;
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
