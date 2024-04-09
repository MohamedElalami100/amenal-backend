package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.budget.application.domain.Produit;
import com.amenal.amenalbackend.budget.application.port.out.ProduitDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProduitDaoAdapter implements ProduitDao {

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Produit findProduitById(Integer id) {
		ProduitEntity produitEntity = produitRepository.findById(id).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);
		return produit;
	}

	@Override
	public List<Produit> findAllProduits() {
		List<ProduitEntity> produitEntities = produitRepository.findAll();
		return produitEntities.stream().map(produitEntity -> modelMapper.map(produitEntity, Produit.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Produit> getProduitsByAvenantId(Integer id) {
		List<ProduitEntity> produitEntities = produitRepository.getProduitsByAvenantId(id);
		return produitEntities.stream().map(produitEntity -> modelMapper.map(produitEntity, Produit.class))
				.collect(Collectors.toList());
	}

	@Override
	public Produit saveProduit(Produit produit) {
		try {
			// if there is a produit with the same designation in the same avenant:
			List<ProduitEntity> sameProduitEntities = produitRepository.getProduitsByAvenantIdAndDesignation(
					produit.getMetre().getBudget().getAvenant().getId(), produit.getDesignation());
			List<Produit> sameProduits = sameProduitEntities.stream()
					.map(produitEntity -> modelMapper.map(produitEntity, Produit.class)).collect(Collectors.toList());
			if (!sameProduits.isEmpty()) {
				return sameProduits.get(0);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		// if not:
		ProduitEntity produitEntity = modelMapper.map(produit, ProduitEntity.class);
		ProduitEntity savedEntity = produitRepository.save(produitEntity);
		return modelMapper.map(savedEntity, Produit.class);
	}

	@Override
	public Produit updateProduit(Produit produit) throws DuplicateElementException {
		ProduitEntity existingEntity = produitRepository.findById(produit.getId()).orElseThrow();

		try {
			// if there is a produit with the same designation in the same avenant:
			if (!produit.getDesignation().equals(existingEntity.getDesignation())) {
				List<ProduitEntity> sameProduitEntities = produitRepository.getProduitsByAvenantIdAndDesignation(
						produit.getMetre().getBudget().getAvenant().getId(), produit.getDesignation());
				List<Produit> sameProduits = sameProduitEntities.stream()
						.map(produitEntity -> modelMapper.map(produitEntity, Produit.class))
						.collect(Collectors.toList());
				if (!sameProduits.isEmpty()) {
					throw new DuplicateElementException("Produit Existe Deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}

		// if not:
		// Use ModelMapper to map non-null properties from Produit to existingEntity
		modelMapper.map(produit, existingEntity);

		ProduitEntity updatedEntity = produitRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Produit.class);
	}

	@Override
	public void deleteProduit(Integer id) {
		// Check if Produit with the given ID exists
		ProduitEntity produitEntity = produitRepository.findById(id).orElseThrow();

		// Delete the entity
		produitRepository.delete(produitEntity);
	}

}
