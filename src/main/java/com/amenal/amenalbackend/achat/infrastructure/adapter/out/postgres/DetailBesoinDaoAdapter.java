package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.DetailBesoin;
import com.amenal.amenalbackend.achat.core.port.out.DetailBesoinDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailBesoinEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DetailBesoinRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailBesoinDaoAdapter implements DetailBesoinDao {

	@Autowired
	private DetailBesoinRepository detailBesoinRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailBesoin findDetailBesoinById(Integer id) {
		DetailBesoinEntity detailBesoinEntity = detailBesoinRepository.findById(id).get();
		DetailBesoin detailBesoin = modelMapper.map(detailBesoinEntity, DetailBesoin.class);
		return detailBesoin;
	}

	@Override
	public List<DetailBesoin> findAllDetailBesoins() {
		List<DetailBesoinEntity> detailBesoinEntities = detailBesoinRepository.findAll();
		return detailBesoinEntities.stream().map(detailBesoinEntity -> modelMapper.map(detailBesoinEntity, DetailBesoin.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailBesoin saveDetailBesoin(DetailBesoin detailBesoin) {
		DetailBesoinEntity detailBesoinEntity = modelMapper.map(detailBesoin, DetailBesoinEntity.class);
		DetailBesoinEntity savedEntity = detailBesoinRepository.save(detailBesoinEntity);
		return modelMapper.map(savedEntity, DetailBesoin.class);
	}

	@Override
	public DetailBesoin updateDetailBesoin(DetailBesoin detailBesoin) {
		DetailBesoinEntity existingEntity = detailBesoinRepository.findById(detailBesoin.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailBesoin to existingEntity
		DetailBesoinEntity newEntity = modelMapper.map(detailBesoin, DetailBesoinEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		DetailBesoinEntity updatedEntity = detailBesoinRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailBesoin.class);
	}

	@Override
	public void deleteDetailBesoin(Integer id) {
		// Check if DetailBesoin with the given ID exists
		DetailBesoinEntity detailBesoinEntity = detailBesoinRepository.findById(id).orElseThrow();

		// Delete the entity
		detailBesoinRepository.delete(detailBesoinEntity);
	}

}
