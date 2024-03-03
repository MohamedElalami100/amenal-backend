package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.TacheDao;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class TacheDaoAdapter implements TacheDao {

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Tache findTacheById(Integer id) {
		TacheEntity tacheEntity = tacheRepository.findById(id).get();
		Tache tache = modelMapper.map(tacheEntity, Tache.class);
		return tache;
	}

	@Override
	public List<Tache> findAllTaches() {
		List<TacheEntity> tacheEntities = tacheRepository.findAll();
		return tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());
	}

	@Override
	public Tache saveTache(Tache tache) {
		try {
			// TACHE EXISTE DANS UN AUTRE AVENANT
			List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
					.getTachesInOtherAvenants(tache.getProduit().getMetre().getBudget().getAvenant().getId());
			List<Tache> tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

			for (Tache curTache : tachesInOtherAvenants) {
				if (curTache.getTitreActivite().equalsIgnoreCase(tache.getTitreActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(tache.getLot().getDesignation())) {
					return curTache;
				}
			}

			// if there is a Tache with the same activite and lot in the same avenant:
			List<TacheEntity> sameTacheEntities = tacheRepository.getTachesByAvenantIdAndLotAndActivite(
					tache.getProduit().getMetre().getBudget().getAvenant().getId(), tache.getLot().getDesignation(),
					tache.getTitreActivite());
			List<Tache> sameTaches = sameTacheEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
			if (!sameTaches.isEmpty()) {
				return sameTaches.get(0);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		// if not:
		TacheEntity tacheEntity = modelMapper.map(tache, TacheEntity.class);
		TacheEntity savedEntity = tacheRepository.save(tacheEntity);
		return modelMapper.map(savedEntity, Tache.class);
	}

	@Override
	public Tache updateTache(Tache tache) throws DuplicateElementException {
		TacheEntity existingEntity = tacheRepository.findById(tache.getId()).orElseThrow();

		try {
			if (tache.getProduit().getId() != existingEntity.getProduit().getId()
					|| tache.getLot().getId() != existingEntity.getLot().getId()
					|| tache.getTitreActivite() != existingEntity.getTitreActivite()) {
				// TACHE EXISTE DANS UN AUTRE AVENANT
				List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
						.getTachesInOtherAvenants(tache.getProduit().getMetre().getBudget().getAvenant().getId());
				List<Tache> tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
						.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

				for (Tache curTache : tachesInOtherAvenants) {
					if (curTache.getTitreActivite().equalsIgnoreCase(tache.getTitreActivite())
							&& tache.getLot().getDesignation().equalsIgnoreCase(tache.getLot().getDesignation())) {
						throw new DuplicateElementException("Tache Existe Deja");
					}
				}

				// if there is a Tache with the same activite and lot in the same avenant:
				List<TacheEntity> sameTacheEntities = tacheRepository.getTachesByAvenantIdAndLotAndActivite(
						tache.getProduit().getMetre().getBudget().getAvenant().getId(), tache.getLot().getDesignation(),
						tache.getTitreActivite());
				List<Tache> sameTaches = sameTacheEntities.stream()
						.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
				if (!sameTaches.isEmpty()) {
					throw new DuplicateElementException("Tache Existe Deja");
				}
			}

		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		// Use ModelMapper to map non-null properties from Tache to existingEntity
		modelMapper.map(tache, existingEntity);

		TacheEntity updatedEntity = tacheRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Tache.class);
	}

	@Override
	public void deleteTache(Integer id) {
		// Check if Tache with the given ID exists
		TacheEntity tacheEntity = tacheRepository.findById(id).orElseThrow();

		// Delete the entity
		tacheRepository.delete(tacheEntity);
	}

	@Override
	public List<Tache> getTachesByAvenantId(Integer id) {
		List<TacheEntity> tacheEntities = tacheRepository.getTachesByAvenantId(id);
		return tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());
	}
}
