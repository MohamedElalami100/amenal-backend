package com.amenal.amenalbackend.application.project.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.port.out.ProduitDao;

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
	
	public Produit saveProduit(Produit produit) {
		return produitDao.saveProduit(produit);
	}
	
	public Produit updateProduit(Produit produit) {
		return produitDao.updateProduit(produit);
	}
	
	public void deleteProduit(Integer id) {
		produitDao.deleteProduit(id);
	}
	
	public List<Produit> saveProduits(List<Produit> produits) {
		List<Produit> savedProduits = new ArrayList<Produit>();
		for(Produit produit: produits )
			savedProduits.add(produitDao.saveProduit(produit));
		return savedProduits;
	} 

}
