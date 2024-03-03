package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.GrpQualiteEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.GrpQualiteRepository;
import com.amenal.amenalbackend.application.project.domain.GrpQualite;
import com.amenal.amenalbackend.application.project.port.out.GrpQualiteDao;

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
	public GrpQualite saveGrpQualite(GrpQualite grpQualite) {
		// if there is a grpQualite with the same designation in the same avenant:
		List<GrpQualiteEntity> sameGrpQualiteEntities = grpQualiteRepository.getGrpQualitesByAvenantIdAndTitre(
				grpQualite.getTache().getProduit().getMetre().getBudget().getAvenant().getId(),
				grpQualite.getTitre());
		List<GrpQualite> sameGrpQualites = sameGrpQualiteEntities.stream()
				.map(grpQualiteEntity -> modelMapper.map(grpQualiteEntity, GrpQualite.class))
				.collect(Collectors.toList());

		if (!sameGrpQualites.isEmpty()) {
			return sameGrpQualites.get(0);
		}
		// if not:
		GrpQualiteEntity grpQualiteEntity = modelMapper.map(grpQualite, GrpQualiteEntity.class);
		GrpQualiteEntity savedEntity = grpQualiteRepository.save(grpQualiteEntity);
		return modelMapper.map(savedEntity, GrpQualite.class);
	}

	@Override
	public GrpQualite updateGrpQualite(GrpQualite grpQualite) {
		GrpQualiteEntity existingEntity = grpQualiteRepository.findById(grpQualite.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from GrpQualite to existingEntity
		modelMapper.map(grpQualite, existingEntity);

		GrpQualiteEntity updatedEntity = grpQualiteRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, GrpQualite.class);
	}

	@Override
	public void deleteGrpQualite(Integer id) {
		// Check if GrpQualite with the given ID exists
		GrpQualiteEntity grpQualiteEntity = grpQualiteRepository.findById(id).orElseThrow();

		// Delete the entity
		grpQualiteRepository.delete(grpQualiteEntity);
	}

}
