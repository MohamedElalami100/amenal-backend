package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailDelaiAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailDelaiAttenteRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailDelaiAttenteDaoAdapter implements DetailDelaiAttenteDao {

	@Autowired
	private DetailDelaiAttenteRepository detailDelaiAttenteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailDelaiAttente findDetailDelaiAttenteById(Integer id) {
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = detailDelaiAttenteRepository.findById(id).get();
		DetailDelaiAttente detailDelaiAttente = modelMapper.map(detailDelaiAttenteEntity, DetailDelaiAttente.class);
		return detailDelaiAttente;
	}

	@Override
	public List<DetailDelaiAttente> findAllDetailDelaiAttentes() {
		List<DetailDelaiAttenteEntity> detailDelaiAttenteEntities = detailDelaiAttenteRepository.findAll();
		return detailDelaiAttenteEntities.stream()
				.map(detailDelaiAttenteEntity -> modelMapper.map(detailDelaiAttenteEntity, DetailDelaiAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailDelaiAttente saveDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente) {
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = modelMapper.map(detailDelaiAttente,
				DetailDelaiAttenteEntity.class);
		DetailDelaiAttenteEntity savedEntity = detailDelaiAttenteRepository.save(detailDelaiAttenteEntity);
		return modelMapper.map(savedEntity, DetailDelaiAttente.class);
	}

	@Override
	public DetailDelaiAttente saveDetailDelaiAttenteWithErreur(DetailDelaiAttente detailDelaiAttente) {
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = modelMapper.map(detailDelaiAttente,
				DetailDelaiAttenteEntity.class);
		DetailDelaiAttenteEntity savedEntity = detailDelaiAttenteRepository.save(detailDelaiAttenteEntity);
		return modelMapper.map(savedEntity, DetailDelaiAttente.class);
	}

	@Override
	public DetailDelaiAttente updateDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente) {
		DetailDelaiAttenteEntity existingEntity = detailDelaiAttenteRepository.findById(detailDelaiAttente.getId())
				.orElseThrow();
		// Use ModelMapper to map non-null properties from DetailDelaiAttente to
		// existingEntity
		DetailDelaiAttenteEntity newEntity = modelMapper.map(detailDelaiAttente, DetailDelaiAttenteEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		DetailDelaiAttenteEntity updatedEntity = detailDelaiAttenteRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailDelaiAttente.class);
	}

	@Override
	public void deleteDetailDelaiAttente(Integer id) {
		// Check if DetailDelaiAttente with the given ID exists
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = detailDelaiAttenteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailDelaiAttenteRepository.delete(detailDelaiAttenteEntity);
	}

	@Override
	public List<DetailDelaiAttente> getDetailDelaiAttentesByAvenantId(Integer id) {
		List<DetailDelaiAttenteEntity> detailDelaiAttenteEntities = detailDelaiAttenteRepository
				.getDetailDelaiAttentesByAvenantId(id);
		return detailDelaiAttenteEntities.stream()
				.map(detailDelaiAttenteEntity -> modelMapper.map(detailDelaiAttenteEntity, DetailDelaiAttente.class))
				.collect(Collectors.toList());
	}
}
