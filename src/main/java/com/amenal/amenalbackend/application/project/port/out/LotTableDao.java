package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.dto.LotTableDto;

public interface LotTableDao {

	List<LotTableDto> getLotTableByAvenantIdAndCharge(Integer id, String charge);

	List<LotTableDto> getLotTableByProjectIdAndCharge(Integer id, String charge);
}
