package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.dto.TacheTableDto;

public interface TacheTableDao {

	List<TacheTableDto> getTacheTableByAvenantIdAndCharge(Integer id, String charge);

	List<TacheTableDto> getTacheTableByProjectIdAndCharge(Integer id, String charge);
}
