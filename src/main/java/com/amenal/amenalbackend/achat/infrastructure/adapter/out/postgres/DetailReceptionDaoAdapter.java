package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DetailReceptionEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DetailReceptionRepository;
import com.amenal.amenalbackend.achat.application.domain.DetailReception;
import com.amenal.amenalbackend.achat.application.dto.DetailReceptionDto;
import com.amenal.amenalbackend.achat.application.port.out.DetailReceptionDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailReceptionDaoAdapter implements DetailReceptionDao {

	@Autowired
	private DetailReceptionRepository detailReceptionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailReceptionDto findDetailReceptionById(Integer id) {
		DetailReceptionEntity detailReceptionEntity = detailReceptionRepository.findById(id).get();
		
		DetailReceptionDto detailReceptionDto = modelMapper.map(detailReceptionEntity, DetailReceptionDto.class);
		DetailReception detailReception = modelMapper.map(detailReceptionEntity, DetailReception.class);

		// Set attributes for the DetailReceptionDto object
		detailReceptionDto.setMntHt(detailReception.getMntHt());
		detailReceptionDto.setMntTtc(detailReception.getMntTtc());
		detailReceptionDto.setMntTva(detailReception.getMntTva());

		return detailReceptionDto;
	}

	@Override
	public List<DetailReceptionDto> findAllDetailReceptions() {
	    List<DetailReceptionEntity> detailReceptionEntities = detailReceptionRepository.findAll();
	    List<DetailReceptionDto> detailReceptionDtos = new ArrayList<>();

	    for (DetailReceptionEntity detailReceptionEntity : detailReceptionEntities) {
	        DetailReceptionDto detailReceptionDto = modelMapper.map(detailReceptionEntity, DetailReceptionDto.class);
	        DetailReception detailReception = modelMapper.map(detailReceptionEntity, DetailReception.class);

	        // Set attributes for the DetailReceptionDto object
	        detailReceptionDto.setMntHt(detailReception.getMntHt());
	        detailReceptionDto.setMntTtc(detailReception.getMntTtc());
	        detailReceptionDto.setMntTva(detailReception.getMntTva());

	        detailReceptionDtos.add(detailReceptionDto);
	    }

	    return detailReceptionDtos;
	}

	@Override
	public DetailReception saveDetailReception(DetailReception detailReception) {
		DetailReceptionEntity detailReceptionEntity = modelMapper.map(detailReception, DetailReceptionEntity.class);
		DetailReceptionEntity savedEntity = detailReceptionRepository.save(detailReceptionEntity);
		return modelMapper.map(savedEntity, DetailReception.class);
	}

	@Override
	public DetailReception updateDetailReception(DetailReception detailReception) {
		DetailReceptionEntity existingEntity = detailReceptionRepository.findById(detailReception.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailReception to existingEntity
		modelMapper.map(detailReception, existingEntity);

		DetailReceptionEntity updatedEntity = detailReceptionRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailReception.class);
	}

	@Override
	public void deleteDetailReception(Integer id) {
		// Check if DetailReception with the given ID exists
		DetailReceptionEntity detailReceptionEntity = detailReceptionRepository.findById(id).orElseThrow();

		// Delete the entity
		detailReceptionRepository.delete(detailReceptionEntity);
	}

}
