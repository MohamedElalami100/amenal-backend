package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.ProduitTableDto;
import com.amenal.amenalbackend.budget.application.port.out.ProduitTableDao;

public class ProduitTableUseCase {
	
	private ProduitTableDao produitTableDao;
	
	public ProduitTableUseCase(ProduitTableDao produitTableDao) {
		this.produitTableDao = produitTableDao;
	}
	
	public List<ProduitTableDto> getProduitTableByAvenantIdAndCharge(Integer id, String charge) {
		return produitTableDao.getProduitTableByAvenantIdAndCharge(id, charge);
	}

	public List<ProduitTableDto> getProduitTableByProjectIdAndCharge(Integer id, String charge) {
		return produitTableDao.getProduitTableByProjectIdAndCharge(id, charge);
	}
}
