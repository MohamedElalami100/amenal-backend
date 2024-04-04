package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DetailFactureEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DetailFactureRepository;
import com.amenal.amenalbackend.achat.application.domain.DetailFacture;
import com.amenal.amenalbackend.achat.application.port.out.DetailFactureDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailFactureDaoAdapter implements DetailFactureDao {

	@Autowired
	private DetailFactureRepository detailFactureRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailFacture findDetailFactureById(Integer id) {
		DetailFactureEntity detailFactureEntity = detailFactureRepository.findById(id).get();
		DetailFacture detailFacture = modelMapper.map(detailFactureEntity, DetailFacture.class);
		return detailFacture;
	}

	@Override
	public List<DetailFacture> findAllDetailFactures() {
		List<DetailFactureEntity> detailFactureEntities = detailFactureRepository.findAll();
		return detailFactureEntities.stream().map(detailFactureEntity -> modelMapper.map(detailFactureEntity, DetailFacture.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailFacture saveDetailFacture(DetailFacture detailFacture) {
		DetailFactureEntity detailFactureEntity = modelMapper.map(detailFacture, DetailFactureEntity.class);
		DetailFactureEntity savedEntity = detailFactureRepository.save(detailFactureEntity);
		return modelMapper.map(savedEntity, DetailFacture.class);
	}

	@Override
	public DetailFacture updateDetailFacture(DetailFacture detailFacture) {
		DetailFactureEntity existingEntity = detailFactureRepository.findById(detailFacture.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailFacture to existingEntity
		modelMapper.map(detailFacture, existingEntity);

		DetailFactureEntity updatedEntity = detailFactureRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailFacture.class);
	}

	@Override
	public void deleteDetailFacture(Integer id) {
		// Check if DetailFacture with the given ID exists
		DetailFactureEntity detailFactureEntity = detailFactureRepository.findById(id).orElseThrow();

		// Delete the entity
		detailFactureRepository.delete(detailFactureEntity);
	}

}
