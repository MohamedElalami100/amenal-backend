package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.GrpQualiteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.port.out.GrpQualiteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.GrpQualiteRepository;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class GrpQualiteDaoAdapter implements GrpQualiteDao {

	@Autowired
	private GrpQualiteRepository grpQualiteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public GrpQualite findGrpQualiteById(Integer id) {
		GrpQualiteEntity grpQualiteEntity = grpQualiteRepository.findById(id).get();
		GrpQualite grpQualite = modelMapper.map(grpQualiteEntity, GrpQualite.class);
		return grpQualite;
	}

	@Override
	public List<GrpQualite> findAllGrpQualites() {
		List<GrpQualiteEntity> grpQualiteEntities = grpQualiteRepository.findAll();
		return grpQualiteEntities.stream().map(grpQualiteEntity -> modelMapper.map(grpQualiteEntity, GrpQualite.class))
				.collect(Collectors.toList());
	}

	@Override
	public GrpQualite saveGrpQualite(GrpQualite grpQualite) throws DuplicateElementException {
		try {
			// if there is a grpQualite with the same designation in the same avenant:
			List<GrpQualiteEntity> sameGrpQualiteEntities = grpQualiteRepository.getGrpQualitesByTacheIdAndTitre(
					grpQualite.getTache().getId(), grpQualite.getTitre());
			List<GrpQualite> sameGrpQualites = sameGrpQualiteEntities.stream()
					.map(grpQualiteEntity -> modelMapper.map(grpQualiteEntity, GrpQualite.class))
					.collect(Collectors.toList());
			if (!sameGrpQualites.isEmpty()) {
				throw new DuplicateElementException("Charge existe deja");
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		GrpQualiteEntity grpQualiteEntity = modelMapper.map(grpQualite, GrpQualiteEntity.class);
		GrpQualiteEntity savedEntity = grpQualiteRepository.save(grpQualiteEntity);
		return modelMapper.map(savedEntity, GrpQualite.class);
	}

	@Override
	public GrpQualite updateGrpQualite(GrpQualite grpQualite) throws DuplicateElementException {
		GrpQualiteEntity existingEntity = grpQualiteRepository.findById(grpQualite.getId()).orElseThrow();

		try {
			// if there is a grpQualite with the same designation in the same avenant:
			if (!grpQualite.getTitre().equals(existingEntity.getTitre())) {
				List<GrpQualiteEntity> sameGrpQualiteEntities = grpQualiteRepository.getGrpQualitesByTacheIdAndTitre(
						grpQualite.getTache().getId(), grpQualite.getTitre());
				List<GrpQualite> sameGrpQualites = sameGrpQualiteEntities.stream()
						.map(grpQualiteEntity -> modelMapper.map(grpQualiteEntity, GrpQualite.class))
						.collect(Collectors.toList());
				if (!sameGrpQualites.isEmpty()) {
					throw new DuplicateElementException("Charge existe deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		// Use ModelMapper to map non-null properties from GrpQualite to existingEntity
		GrpQualiteEntity newEntity = modelMapper.map(grpQualite, GrpQualiteEntity.class);

		GrpQualiteEntity updatedEntity = grpQualiteRepository.save(newEntity);
		return modelMapper.map(updatedEntity, GrpQualite.class);
	}

	@Override
	public void deleteGrpQualite(Integer id) {
		// Check if GrpQualite with the given ID exists
		GrpQualiteEntity grpQualiteEntity = grpQualiteRepository.findById(id).orElseThrow();

		// Delete the entity
		grpQualiteRepository.delete(grpQualiteEntity);
	}

	@Override
	public GrpQualite saveGrpQualiteAndReturnItIfExists(GrpQualite grpQualite) {
		try {
			// if there is a grpQualite with the same designation in the same avenant:
			List<GrpQualiteEntity> sameGrpQualiteEntities = grpQualiteRepository.getGrpQualitesByTacheIdAndTitre(
					grpQualite.getTache().getId(), grpQualite.getTitre());
			List<GrpQualite> sameGrpQualites = sameGrpQualiteEntities.stream()
					.map(grpQualiteEntity -> modelMapper.map(grpQualiteEntity, GrpQualite.class))
					.collect(Collectors.toList());
			if (!sameGrpQualites.isEmpty()) {
				return sameGrpQualites.get(0);
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		GrpQualiteEntity grpQualiteEntity = modelMapper.map(grpQualite, GrpQualiteEntity.class);
		GrpQualiteEntity savedEntity = grpQualiteRepository.save(grpQualiteEntity);
		return modelMapper.map(savedEntity, GrpQualite.class);
	}

}
