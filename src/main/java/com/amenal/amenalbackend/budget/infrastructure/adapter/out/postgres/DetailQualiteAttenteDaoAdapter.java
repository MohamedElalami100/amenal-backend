package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteAttenteRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailQualiteAttenteDaoAdapter implements DetailQualiteAttenteDao {

	@Autowired
	private DetailQualiteAttenteRepository detailQualiteAttenteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailQualiteAttente findDetailQualiteAttenteById(Integer id) {
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = detailQualiteAttenteRepository.findById(id).get();
		DetailQualiteAttente detailQualiteAttente = modelMapper.map(detailQualiteAttenteEntity, DetailQualiteAttente.class);
		return detailQualiteAttente;
	}

	@Override
	public List<DetailQualiteAttente> findAllDetailQualiteAttentes() {
		List<DetailQualiteAttenteEntity> detailQualiteAttenteEntities = detailQualiteAttenteRepository.findAll();
		return detailQualiteAttenteEntities.stream()
				.map(detailQualiteAttenteEntity -> modelMapper.map(detailQualiteAttenteEntity, DetailQualiteAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailQualiteAttente saveDetailQualiteAttente(DetailQualiteAttente detailQualiteAttente) {
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = modelMapper.map(detailQualiteAttente,
				DetailQualiteAttenteEntity.class);
		DetailQualiteAttenteEntity savedEntity = detailQualiteAttenteRepository.save(detailQualiteAttenteEntity);
		return modelMapper.map(savedEntity, DetailQualiteAttente.class);
	}

	@Override
	public DetailQualiteAttente saveDetailQualiteAttenteWithErreur(DetailQualiteAttente detailQualiteAttente) {
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = modelMapper.map(detailQualiteAttente,
				DetailQualiteAttenteEntity.class);
		DetailQualiteAttenteEntity savedEntity = detailQualiteAttenteRepository.save(detailQualiteAttenteEntity);
		return modelMapper.map(savedEntity, DetailQualiteAttente.class);
	}

	@Override
	public DetailQualiteAttente updateDetailQualiteAttente(DetailQualiteAttente detailQualiteAttente) {
		DetailQualiteAttenteEntity existingEntity = detailQualiteAttenteRepository.findById(detailQualiteAttente.getId())
				.orElseThrow();
		// Use ModelMapper to map non-null properties from DetailQualiteAttente to
		// existingEntity
		DetailQualiteAttenteEntity newEntity = modelMapper.map(detailQualiteAttente, DetailQualiteAttenteEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		DetailQualiteAttenteEntity updatedEntity = detailQualiteAttenteRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailQualiteAttente.class);
	}

	@Override
	public void deleteDetailQualiteAttente(Integer id) {
		// Check if DetailQualiteAttente with the given ID exists
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = detailQualiteAttenteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailQualiteAttenteRepository.delete(detailQualiteAttenteEntity);
	}

	@Override
	public List<DetailQualiteAttente> getDetailQualiteAttentesByAvenantId(Integer id) {
		List<DetailQualiteAttenteEntity> detailQualiteAttenteEntities = detailQualiteAttenteRepository
				.getDetailQualiteAttentesByAvenantId(id);
		return detailQualiteAttenteEntities.stream()
				.map(detailQualiteAttenteEntity -> modelMapper.map(detailQualiteAttenteEntity, DetailQualiteAttente.class))
				.collect(Collectors.toList());
	}
}
