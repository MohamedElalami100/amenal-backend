package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.port.out.ProduitTableDao;
import com.amenal.amenalbackend.budget.infrastructure.dto.ProduitTableDto;

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
