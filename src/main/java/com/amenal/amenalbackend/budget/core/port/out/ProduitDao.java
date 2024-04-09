package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface ProduitDao {
	Produit findProduitById(Integer id);
	
	List<Produit> findAllProduits();
	
	Produit saveProduit(Produit produit);
	
	Produit updateProduit(Produit produit) throws DuplicateElementException;
	
	void deleteProduit(Integer id);

	List<Produit> getProduitsByAvenantId(Integer id);
	
}
