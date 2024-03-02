package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Produit;

public interface ProduitDao {
	Produit findProduitById(Integer id);
	
	List<Produit> findAllProduits();
	
	Produit saveProduit(Produit produit);
	
	Produit updateProduit(Produit produit);
	
	void deleteProduit(Integer id);

	List<Produit> getProduitsByAvenantId(Integer id);
	
}
