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
			// if there is a Tache with the same activite and tache in the same avenant:
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
	public Tache updateTache(Tache tache) {
		TacheEntity existingEntity = tacheRepository.findById(tache.getId()).orElseThrow();

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

}
