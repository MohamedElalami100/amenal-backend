package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.ProduitStandard;

public interface ProduitStandardDao {
	ProduitStandard findProduitStandardById(Integer id);
	
	List<ProduitStandard> findAllProduitStandards();
	
	ProduitStandard saveProduitStandard(ProduitStandard produitStandard);
	
	ProduitStandard updateProduitStandard(ProduitStandard produitStandard);
	
	void deleteProduitStandard(Integer id);
	
}
