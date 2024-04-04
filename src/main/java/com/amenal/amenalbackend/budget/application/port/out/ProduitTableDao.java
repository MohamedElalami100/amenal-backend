package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.ProduitTableDto;

public interface ProduitTableDao {

	List<ProduitTableDto> getProduitTableByAvenantIdAndCharge(Integer id, String charge);

	List<ProduitTableDto> getProduitTableByProjectIdAndCharge(Integer id, String charge);
}
