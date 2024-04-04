package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.LotTableDto;

public interface LotTableDao {

	List<LotTableDto> getLotTableByAvenantIdAndCharge(Integer id, String charge);

	List<LotTableDto> getLotTableByProjectIdAndCharge(Integer id, String charge);
}
