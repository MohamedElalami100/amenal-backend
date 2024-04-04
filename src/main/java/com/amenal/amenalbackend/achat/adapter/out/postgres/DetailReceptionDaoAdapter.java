package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DetailReceptionEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DetailReceptionRepository;
import com.amenal.amenalbackend.achat.application.domain.DetailReception;
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
	public DetailReception findDetailReceptionById(Integer id) {
		DetailReceptionEntity detailReceptionEntity = detailReceptionRepository.findById(id).get();
		DetailReception detailReception = modelMapper.map(detailReceptionEntity, DetailReception.class);
		return detailReception;
	}

	@Override
	public List<DetailReception> findAllDetailReceptions() {
		List<DetailReceptionEntity> detailReceptionEntities = detailReceptionRepository.findAll();
		return detailReceptionEntities.stream().map(detailReceptionEntity -> modelMapper.map(detailReceptionEntity, DetailReception.class))
				.collect(Collectors.toList());
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
