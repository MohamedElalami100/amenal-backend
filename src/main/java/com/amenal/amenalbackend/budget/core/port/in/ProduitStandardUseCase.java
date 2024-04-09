package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.ProduitStandard;
import com.amenal.amenalbackend.budget.core.port.out.ProduitStandardDao;

public class ProduitStandardUseCase {
	
	private ProduitStandardDao produitStandardDao;
	
	public ProduitStandardUseCase(ProduitStandardDao produitStandardDao) {
		this.produitStandardDao = produitStandardDao;
	}

	public ProduitStandard findProduitStandardById(Integer id) {
	    return produitStandardDao.findProduitStandardById(id);
	}

	public List<ProduitStandard> findAllProduitStandards() {
		return produitStandardDao.findAllProduitStandards();
	}
	
	public ProduitStandard saveProduitStandard(ProduitStandard produitStandard) {
		return produitStandardDao.saveProduitStandard(produitStandard);
	}
	
	public ProduitStandard updateProduitStandard(ProduitStandard produitStandard) {
		return produitStandardDao.updateProduitStandard(produitStandard);
	}
	
	public void deleteProduitStandard(Integer id) {
		produitStandardDao.deleteProduitStandard(id);
	}
	

}
