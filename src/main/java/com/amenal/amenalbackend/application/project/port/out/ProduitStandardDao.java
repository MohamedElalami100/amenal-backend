package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.ProduitStandard;

public interface ProduitStandardDao {
	ProduitStandard findProduitStandardById(Integer id);
	
	List<ProduitStandard> findAllProduitStandards();
	
	ProduitStandard saveProduitStandard(ProduitStandard produitStandard);
	
	ProduitStandard updateProduitStandard(ProduitStandard produitStandard);
	
	void deleteProduitStandard(Integer id);
	
}
