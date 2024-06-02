package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.port.out.DetailProduitDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

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
	public DetailProduit saveDetailProduit(DetailProduit detailProduit) throws DuplicateElementException {
		try {
			// if there is a detailProduit with the same designation in the same avenant:
			List<DetailProduitEntity> sameDetailProduitEntities = detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(
					detailProduit.getTache().getId(), detailProduit.getReference());
			List<DetailProduit> sameDetailProduits = sameDetailProduitEntities.stream()
					.map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class)).collect(Collectors.toList());
			if (!sameDetailProduits.isEmpty()) {
				throw new DuplicateElementException("Charge existe deja");
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		DetailProduitEntity newEntity = modelMapper.map(detailProduit, DetailProduitEntity.class);

		DetailProduitEntity updatedEntity = detailProduitRepository.save(newEntity);
		return modelMapper.map(updatedEntity, DetailProduit.class);
	}

	@Override
	public DetailProduit updateDetailProduit(DetailProduit detailProduit) throws DuplicateElementException {
		DetailProduitEntity existingEntity = detailProduitRepository.findById(detailProduit.getId()).orElseThrow();

		try {
			// if there is a detailProduit with the same designation in the same avenant:
			if(!detailProduit.getReference().equals(existingEntity.getReference())) {
				List<DetailProduitEntity> sameDetailProduitEntities = detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(
						detailProduit.getTache().getId(), detailProduit.getReference());
				List<DetailProduit> sameDetailProduits = sameDetailProduitEntities.stream()
						.map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class)).collect(Collectors.toList());
				if (!sameDetailProduits.isEmpty()) {
					throw new DuplicateElementException("Charge existe deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		// Use ModelMapper to map non-null properties from DetailProduit to existingEntity
		DetailProduitEntity newEntity = modelMapper.map(detailProduit, DetailProduitEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

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

	@Override
	public List<DetailProduit> getDetailProduitsByAvenantId(Integer id) {
		List<DetailProduitEntity> detailProduitEntities = detailProduitRepository.getDetailProduitsByAvenantId(id);
		return detailProduitEntities.stream().map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<DetailProduit> getDetailProduitsByTacheId(Integer id) {
		List<DetailProduitEntity> detailProduitEntities = detailProduitRepository.getDetailProduitByTacheId(id);
		return detailProduitEntities.stream().map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class))
				.collect(Collectors.toList());
	}

}
