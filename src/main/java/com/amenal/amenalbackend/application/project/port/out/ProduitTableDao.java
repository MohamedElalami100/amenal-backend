package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.dto.ProduitTableDto;

public interface ProduitTableDao {

	List<ProduitTableDto> getProduitTableByAvenantIdAndCharge(Integer id, String charge);

	List<ProduitTableDto> getProduitTableByProjectIdAndCharge(Integer id, String charge);
}
