package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.DetailChargeAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailChargeAttenteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailChargeAttenteDaoAdapter implements DetailChargeAttenteDao {

	@Autowired
	private DetailChargeAttenteRepository detailChargeAttenteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailChargeAttente findDetailChargeAttenteById(Integer id) {
		DetailChargeAttenteEntity detailChargeAttenteEntity = detailChargeAttenteRepository.findById(id).get();
		DetailChargeAttente detailChargeAttente = modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class);
		return detailChargeAttente;
	}

	@Override
	public List<DetailChargeAttente> findAllDetailChargeAttentes() {
		List<DetailChargeAttenteEntity> detailChargeAttenteEntities = detailChargeAttenteRepository.findAll();
		return detailChargeAttenteEntities.stream()
				.map(detailChargeAttenteEntity -> modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailChargeAttente saveDetailChargeAttente(DetailChargeAttente detailChargeAttente) {
		DetailChargeAttenteEntity detailChargeAttenteEntity = modelMapper.map(detailChargeAttente,
				DetailChargeAttenteEntity.class);
		DetailChargeAttenteEntity savedEntity = detailChargeAttenteRepository.save(detailChargeAttenteEntity);
		return modelMapper.map(savedEntity, DetailChargeAttente.class);
	}

	@Override
	public DetailChargeAttente saveDetailChargeAttenteWithErreur(DetailChargeAttente detailChargeAttente) {
		DetailChargeAttenteEntity detailChargeAttenteEntity = modelMapper.map(detailChargeAttente,
				DetailChargeAttenteEntity.class);
		DetailChargeAttenteEntity savedEntity = detailChargeAttenteRepository.save(detailChargeAttenteEntity);
		return modelMapper.map(savedEntity, DetailChargeAttente.class);
	}

	@Override
	public DetailChargeAttente updateDetailChargeAttente(DetailChargeAttente detailChargeAttente) {
		detailChargeAttenteRepository.findById(detailChargeAttente.getId())
				.orElseThrow();
		// Use ModelMapper to map non-null properties from DetailChargeAttente to
		// existingEntity
		DetailChargeAttenteEntity newEntity = modelMapper.map(detailChargeAttente, DetailChargeAttenteEntity.class);

		DetailChargeAttenteEntity updatedEntity = detailChargeAttenteRepository.save(newEntity);
		return modelMapper.map(updatedEntity, DetailChargeAttente.class);
	}

	@Override
	public void deleteDetailChargeAttente(Integer id) {
		// Check if DetailChargeAttente with the given ID exists
		DetailChargeAttenteEntity detailChargeAttenteEntity = detailChargeAttenteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailChargeAttenteRepository.delete(detailChargeAttenteEntity);
	}

	@Override
	public List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id) {
		List<DetailChargeAttenteEntity> detailChargeAttenteEntities = detailChargeAttenteRepository
				.getDetailChargeAttentesByAvenantId(id);
		return detailChargeAttenteEntities.stream()
				.map(detailChargeAttenteEntity -> modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class))
				.collect(Collectors.toList());
	}
}
