package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.infrastructure.dto.LotTableDto;

public interface LotTableDao {

	List<LotTableDto> getLotTableByAvenantIdAndCharge(Integer id, String charge);

	List<LotTableDto> getLotTableByProjectIdAndCharge(Integer id, String charge);
}
