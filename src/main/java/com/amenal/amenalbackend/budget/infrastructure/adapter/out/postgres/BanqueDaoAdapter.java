package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Banque;
import com.amenal.amenalbackend.budget.core.port.out.BanqueDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.BanqueEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.BanqueRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class BanqueDaoAdapter implements BanqueDao {

	@Autowired
	private BanqueRepository banqueRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Banque findBanqueById(Integer id) {
		BanqueEntity banqueEntity = banqueRepository.findById(id).get();
		Banque banque = modelMapper.map(banqueEntity, Banque.class);
		return banque;
	}

	@Override
	public List<Banque> findAllBanques() {
		List<BanqueEntity> banqueEntities = banqueRepository.findAll();
		return banqueEntities.stream().map(banqueEntity -> modelMapper.map(banqueEntity, Banque.class))
				.collect(Collectors.toList());
	}

	@Override
	public Banque saveBanque(Banque banque) {
		BanqueEntity banqueEntity = modelMapper.map(banque, BanqueEntity.class);
		BanqueEntity savedEntity = banqueRepository.save(banqueEntity);
		return modelMapper.map(savedEntity, Banque.class);
	}

	@Override
	public Banque updateBanque(Banque banque) {
		BanqueEntity existingEntity = banqueRepository.findById(banque.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Banque to existingEntity
		modelMapper.map(banque, existingEntity);

		BanqueEntity updatedEntity = banqueRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Banque.class);
	}

	@Override
	public void deleteBanque(Integer id) {
		// Check if Banque with the given ID exists
		BanqueEntity banqueEntity = banqueRepository.findById(id).orElseThrow();

		// Delete the entity
		banqueRepository.delete(banqueEntity);
	}

}
