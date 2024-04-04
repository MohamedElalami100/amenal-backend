package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.BesoinEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.BesoinRepository;
import com.amenal.amenalbackend.achat.application.domain.Besoin;
import com.amenal.amenalbackend.achat.application.port.out.BesoinDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class BesoinDaoAdapter implements BesoinDao {

	@Autowired
	private BesoinRepository besoinRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Besoin findBesoinById(Integer id) {
		BesoinEntity besoinEntity = besoinRepository.findById(id).get();
		Besoin besoin = modelMapper.map(besoinEntity, Besoin.class);
		return besoin;
	}

	@Override
	public List<Besoin> findAllBesoins() {
		List<BesoinEntity> besoinEntities = besoinRepository.findAll();
		return besoinEntities.stream().map(besoinEntity -> modelMapper.map(besoinEntity, Besoin.class))
				.collect(Collectors.toList());
	}

	@Override
	public Besoin saveBesoin(Besoin besoin) {
		BesoinEntity besoinEntity = modelMapper.map(besoin, BesoinEntity.class);
		BesoinEntity savedEntity = besoinRepository.save(besoinEntity);
		return modelMapper.map(savedEntity, Besoin.class);
	}

	@Override
	public Besoin updateBesoin(Besoin besoin) {
		BesoinEntity existingEntity = besoinRepository.findById(besoin.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Besoin to existingEntity
		modelMapper.map(besoin, existingEntity);

		BesoinEntity updatedEntity = besoinRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Besoin.class);
	}

	@Override
	public void deleteBesoin(Integer id) {
		// Check if Besoin with the given ID exists
		BesoinEntity besoinEntity = besoinRepository.findById(id).orElseThrow();

		// Delete the entity
		besoinRepository.delete(besoinEntity);
	}

}
