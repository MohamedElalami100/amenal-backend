package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.infrastructure.dto.ProduitTableDto;

public interface ProduitTableDao {

	List<ProduitTableDto> getProduitTableByAvenantIdAndCharge(Integer id, String charge);

	List<ProduitTableDto> getProduitTableByProjectIdAndCharge(Integer id, String charge);
}
