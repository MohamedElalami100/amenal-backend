package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.MetreAv;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.MetreAvEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.MetreAv;
import com.amenal.amenalbackend.budget.core.port.out.MetreAvDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.MetreAvEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.MetreAvRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class MetreAvDaoAdapter implements MetreAvDao {

	@Autowired
	private MetreAvRepository metreAvRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MetreAv findMetreAvById(Integer id) {
		MetreAvEntity metreAvEntity = metreAvRepository.findById(id).get();
		MetreAv metreAv = modelMapper.map(metreAvEntity, MetreAv.class);
		return metreAv;
	}

	@Override
	public List<MetreAv> findAllMetreAvs() {
		List<MetreAvEntity> metreAvEntities = metreAvRepository.findAll();
		return metreAvEntities.stream().map(metreAvEntity -> modelMapper.map(metreAvEntity, MetreAv.class))
				.collect(Collectors.toList());
	}

	@Override
	public MetreAv saveMetreAv(MetreAv metreAv) {
		MetreAvEntity metreAvEntity = modelMapper.map(metreAv, MetreAvEntity.class);
		MetreAvEntity savedEntity = metreAvRepository.save(metreAvEntity);
		return modelMapper.map(savedEntity, MetreAv.class);
	}

	@Override
	public MetreAv updateMetreAv(MetreAv metreAv) {
		MetreAvEntity existingEntity = metreAvRepository.findById(metreAv.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from MetreAv to existingEntity
		MetreAvEntity newEntity = modelMapper.map(metreAv, MetreAvEntity.class);

		MetreAvEntity updatedEntity = metreAvRepository.save(newEntity);
		return modelMapper.map(updatedEntity, MetreAv.class);
	}

	@Override
	public void deleteMetreAv(Integer id) {
		// Check if MetreAv with the given ID exists
		MetreAvEntity metreAvEntity = metreAvRepository.findById(id).orElseThrow();

		// Delete the entity
		metreAvRepository.delete(metreAvEntity);
	}

	@Override
	public MetreAv getOneMetreByAvenantId(Integer id) {
		List<MetreAvEntity> metreAvEntities = metreAvRepository.getMetresByAvenantId(id);
		List<MetreAv> metreAvs = metreAvEntities.stream().map(metreAvEntity -> modelMapper.map(metreAvEntity, MetreAv.class))
				.collect(Collectors.toList());
		
		if(!metreAvs.isEmpty()) {
			return metreAvs.get(0);
		}
		
		return null;
	}
	
	@Override
	public List<MetreAv> getMetresByAvenantId(Integer id) {
		List<MetreAvEntity> metreAvEntities = metreAvRepository.getMetresByAvenantId(id);
		List<MetreAv> metreAvs = metreAvEntities.stream().map(metreAvEntity -> modelMapper.map(metreAvEntity, MetreAv.class))
				.collect(Collectors.toList());
		return metreAvs;
	}


}
