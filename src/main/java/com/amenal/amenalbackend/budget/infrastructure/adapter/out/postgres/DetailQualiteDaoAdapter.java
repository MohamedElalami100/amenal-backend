package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteRepository;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailQualiteDaoAdapter implements DetailQualiteDao {

	@Autowired
	private DetailQualiteRepository detailQualiteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailQualite findDetailQualiteById(Integer id) {
		DetailQualiteEntity detailQualiteEntity = detailQualiteRepository.findById(id).get();
		DetailQualite detailQualite = modelMapper.map(detailQualiteEntity, DetailQualite.class);
		return detailQualite;
	}

	@Override
	public List<DetailQualite> findAllDetailQualites() {
		List<DetailQualiteEntity> detailQualiteEntities = detailQualiteRepository.findAll();
		return detailQualiteEntities.stream().map(detailQualiteEntity -> modelMapper.map(detailQualiteEntity, DetailQualite.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailQualite saveDetailQualite(DetailQualite detailQualite) throws DuplicateElementException {
		try {
			// if there is a detailQualite with the same designation in the same grpQualite:
			List<DetailQualiteEntity> sameDetailQualiteEntities = detailQualiteRepository.getDetailQualitesByGrpQualiteIdAndAffaire(
					detailQualite.getGroupe().getId(), detailQualite.getPointDeControle());
			List<DetailQualite> sameDetailQualites = sameDetailQualiteEntities.stream()
					.map(detailQualiteEntity -> modelMapper.map(detailQualiteEntity, DetailQualite.class)).collect(Collectors.toList());
			if (!sameDetailQualites.isEmpty()) {
				throw new DuplicateElementException("Qualite existe deja");
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		DetailQualiteEntity newEntity = modelMapper.map(detailQualite, DetailQualiteEntity.class);

		DetailQualiteEntity updatedEntity = detailQualiteRepository.save(newEntity);
		return modelMapper.map(updatedEntity, DetailQualite.class);
	}

	@Override
	public DetailQualite updateDetailQualite(DetailQualite detailQualite) throws DuplicateElementException {
		DetailQualiteEntity existingEntity = detailQualiteRepository.findById(detailQualite.getId()).orElseThrow();

		try {
			// if there is a detailQualite with the same designation in the same avenant:
			if(!detailQualite.getPointDeControle().equals(existingEntity.getPointDeControle())) {
				List<DetailQualiteEntity> sameDetailQualiteEntities = detailQualiteRepository.getDetailQualitesByGrpQualiteIdAndAffaire(
						detailQualite.getGroupe().getId(), detailQualite.getPointDeControle());
				List<DetailQualite> sameDetailQualites = sameDetailQualiteEntities.stream()
						.map(detailQualiteEntity -> modelMapper.map(detailQualiteEntity, DetailQualite.class)).collect(Collectors.toList());
				if (!sameDetailQualites.isEmpty()) {
					throw new DuplicateElementException("Qualite existe deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		// Use ModelMapper to map non-null properties from DetailQualite to existingEntity
		modelMapper.map(detailQualite, existingEntity);

		DetailQualiteEntity updatedEntity = detailQualiteRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailQualite.class);
	}


	@Override
	public void deleteDetailQualite(Integer id) {
		// Check if DetailQualite with the given ID exists
		DetailQualiteEntity detailQualiteEntity = detailQualiteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailQualiteRepository.delete(detailQualiteEntity);
	}

	@Override
	public List<DetailQualite> getDetailQualitesByAvenantId(Integer id) {
		List<DetailQualiteEntity> detailQualiteEntities = detailQualiteRepository.getDetailQualitesByAvenantId(id);
		return detailQualiteEntities.stream().map(detailQualiteEntity -> modelMapper.map(detailQualiteEntity, DetailQualite.class))
				.collect(Collectors.toList());
	}

}
