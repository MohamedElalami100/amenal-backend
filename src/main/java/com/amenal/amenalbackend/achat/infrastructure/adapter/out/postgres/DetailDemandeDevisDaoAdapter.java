package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DetailDemandeDevisEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DetailDemandeDevisRepository;
import com.amenal.amenalbackend.achat.application.domain.DetailDemandeDevis;
import com.amenal.amenalbackend.achat.application.port.out.DetailDemandeDevisDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailDemandeDevisDaoAdapter implements DetailDemandeDevisDao {

	@Autowired
	private DetailDemandeDevisRepository detailDemandeDevisRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailDemandeDevis findDetailDemandeDevisById(Integer id) {
		DetailDemandeDevisEntity detailDemandeDevisEntity = detailDemandeDevisRepository.findById(id).get();
		DetailDemandeDevis detailDemandeDevis = modelMapper.map(detailDemandeDevisEntity, DetailDemandeDevis.class);
		return detailDemandeDevis;
	}

	@Override
	public List<DetailDemandeDevis> findAllDetailDemandeDeviss() {
		List<DetailDemandeDevisEntity> detailDemandeDevisEntities = detailDemandeDevisRepository.findAll();
		return detailDemandeDevisEntities.stream().map(detailDemandeDevisEntity -> modelMapper.map(detailDemandeDevisEntity, DetailDemandeDevis.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailDemandeDevis saveDetailDemandeDevis(DetailDemandeDevis detailDemandeDevis) {
		DetailDemandeDevisEntity detailDemandeDevisEntity = modelMapper.map(detailDemandeDevis, DetailDemandeDevisEntity.class);
		DetailDemandeDevisEntity savedEntity = detailDemandeDevisRepository.save(detailDemandeDevisEntity);
		return modelMapper.map(savedEntity, DetailDemandeDevis.class);
	}

	@Override
	public DetailDemandeDevis updateDetailDemandeDevis(DetailDemandeDevis detailDemandeDevis) {
		DetailDemandeDevisEntity existingEntity = detailDemandeDevisRepository.findById(detailDemandeDevis.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailDemandeDevis to existingEntity
		modelMapper.map(detailDemandeDevis, existingEntity);

		DetailDemandeDevisEntity updatedEntity = detailDemandeDevisRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailDemandeDevis.class);
	}

	@Override
	public void deleteDetailDemandeDevis(Integer id) {
		// Check if DetailDemandeDevis with the given ID exists
		DetailDemandeDevisEntity detailDemandeDevisEntity = detailDemandeDevisRepository.findById(id).orElseThrow();

		// Delete the entity
		detailDemandeDevisRepository.delete(detailDemandeDevisEntity);
	}

}
