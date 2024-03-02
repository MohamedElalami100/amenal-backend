package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.application.project.domain.DetailProduit;
import com.amenal.amenalbackend.application.project.port.out.DetailProduitDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailProduitDaoAdapter implements DetailProduitDao {

	@Autowired
	private DetailProduitRepository detailProduitRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailProduit findDetailProduitById(Integer id) {
		DetailProduitEntity detailProduitEntity = detailProduitRepository.findById(id).get();
		DetailProduit detailProduit = modelMapper.map(detailProduitEntity, DetailProduit.class);
		return detailProduit;
	}

	@Override
	public List<DetailProduit> findAllDetailProduits() {
		List<DetailProduitEntity> detailProduitEntities = detailProduitRepository.findAll();
		return detailProduitEntities.stream().map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailProduit saveDetailProduit(DetailProduit detailProduit) {
		DetailProduitEntity detailProduitEntity = modelMapper.map(detailProduit, DetailProduitEntity.class);
		DetailProduitEntity savedEntity = detailProduitRepository.save(detailProduitEntity);
		return modelMapper.map(savedEntity, DetailProduit.class);
	}

	@Override
	public DetailProduit updateDetailProduit(DetailProduit detailProduit) {
		DetailProduitEntity existingEntity = detailProduitRepository.findById(detailProduit.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailProduit to existingEntity
		modelMapper.map(detailProduit, existingEntity);

		DetailProduitEntity updatedEntity = detailProduitRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailProduit.class);
	}

	@Override
	public void deleteDetailProduit(Integer id) {
		// Check if DetailProduit with the given ID exists
		DetailProduitEntity detailProduitEntity = detailProduitRepository.findById(id).orElseThrow();

		// Delete the entity
		detailProduitRepository.delete(detailProduitEntity);
	}

}
