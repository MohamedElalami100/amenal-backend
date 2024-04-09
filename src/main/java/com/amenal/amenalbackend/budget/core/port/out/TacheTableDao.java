package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.infrastructure.dto.TacheTableDto;

public interface TacheTableDao {

	List<TacheTableDto> getTacheTableByAvenantIdAndCharge(Integer id, String charge);

	List<TacheTableDto> getTacheTableByProjectIdAndCharge(Integer id, String charge);
}
