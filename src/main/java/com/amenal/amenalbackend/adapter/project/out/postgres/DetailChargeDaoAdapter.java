package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeDao;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailChargeDaoAdapter implements DetailChargeDao {

	@Autowired
	private DetailChargeRepository detailChargeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailCharge findDetailChargeById(Integer id) {
		DetailChargeEntity detailChargeEntity = detailChargeRepository.findById(id).get();
		DetailCharge detailCharge = modelMapper.map(detailChargeEntity, DetailCharge.class);
		return detailCharge;
	}

	@Override
	public List<DetailCharge> findAllDetailCharges() {
		List<DetailChargeEntity> detailChargeEntities = detailChargeRepository.findAll();
		return detailChargeEntities.stream().map(detailChargeEntity -> modelMapper.map(detailChargeEntity, DetailCharge.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailCharge saveDetailCharge(DetailCharge detailCharge) throws DuplicateElementException {
		try {
			// if there is a detailCharge with the same designation in the same avenant:
			List<DetailChargeEntity> sameDetailChargeEntities = detailChargeRepository.getDetailChargesByTacheIdAndDesignation(
					detailCharge.getTache().getId(), detailCharge.getDesignation());
			List<DetailCharge> sameDetailCharges = sameDetailChargeEntities.stream()
					.map(detailChargeEntity -> modelMapper.map(detailChargeEntity, DetailCharge.class)).collect(Collectors.toList());
			if (!sameDetailCharges.isEmpty()) {
				throw new DuplicateElementException("Charge existe deja");
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		DetailChargeEntity detailChargeEntity = modelMapper.map(detailCharge, DetailChargeEntity.class);
		DetailChargeEntity savedEntity = detailChargeRepository.save(detailChargeEntity);
		return modelMapper.map(savedEntity, DetailCharge.class);
	}

	@Override
	public DetailCharge updateDetailCharge(DetailCharge detailCharge) throws DuplicateElementException {
		DetailChargeEntity existingEntity = detailChargeRepository.findById(detailCharge.getId()).orElseThrow();

		try {
			// if there is a detailCharge with the same designation in the same avenant:
			if(!detailCharge.getDesignation().equals(existingEntity.getDesignation())) {
				List<DetailChargeEntity> sameDetailChargeEntities = detailChargeRepository.getDetailChargesByTacheIdAndDesignation(
						detailCharge.getTache().getId(), detailCharge.getDesignation());
				List<DetailCharge> sameDetailCharges = sameDetailChargeEntities.stream()
						.map(detailChargeEntity -> modelMapper.map(detailChargeEntity, DetailCharge.class)).collect(Collectors.toList());
				if (!sameDetailCharges.isEmpty()) {
					throw new DuplicateElementException("Charge existe deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		// Use ModelMapper to map non-null properties from DetailCharge to existingEntity
		modelMapper.map(detailCharge, existingEntity);

		DetailChargeEntity updatedEntity = detailChargeRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailCharge.class);
	}

	@Override
	public void deleteDetailCharge(Integer id) {
		// Check if DetailCharge with the given ID exists
		DetailChargeEntity detailChargeEntity = detailChargeRepository.findById(id).orElseThrow();

		// Delete the entity
		detailChargeRepository.delete(detailChargeEntity);
	}

}
