package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.port.out.ProduitDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public class ProduitUseCase {

	private ProduitDao produitDao;

	public ProduitUseCase(ProduitDao produitDao) {
		this.produitDao = produitDao;
	}

	public Produit findProduitById(Integer id) {
		return produitDao.findProduitById(id);
	}

	public List<Produit> findAllProduits() {
		return produitDao.findAllProduits();
	}

	public List<Produit> getProduitsByAvenantId(Integer id) {
		return produitDao.getProduitsByAvenantId(id);
	}

	public Produit saveProduit(Produit produit) throws DuplicateElementException {
		// if produit existes:
		List<Produit> currentProduits = null;
		try {
			currentProduits = produitDao.getProduitsByAvenantId(produit.getMetre().getBudget().getAvenant().getId());
		} catch (Exception e) {
			System.out.println(e);
		}
		Produit savedProduit = produitDao.saveProduit(produit);
		if (currentProduits != null && currentProduits.stream().anyMatch(obj -> obj.getId() == savedProduit.getId()))
			throw new DuplicateElementException("Produit Existe Deja");

		// else:
		return savedProduit;
	}

	public Produit updateProduit(Produit produit) throws DuplicateElementException {
		return produitDao.updateProduit(produit);
	}

	public void deleteProduit(Integer id) {
		produitDao.deleteProduit(id);
	}

	public List<Produit> saveProduits(List<Produit> produits) {
		List<Produit> savedProduits = new ArrayList<Produit>();
		for (Produit produit : produits)
			savedProduits.add(produitDao.saveOrUpdateIfExists(produit));
		return savedProduits;
	}

}
