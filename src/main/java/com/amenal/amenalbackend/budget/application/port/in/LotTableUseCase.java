package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.LotTableDto;
import com.amenal.amenalbackend.budget.application.port.out.LotTableDao;

public class LotTableUseCase {
	
	private LotTableDao lotTableDao;
	
	public LotTableUseCase(LotTableDao lotTableDao) {
		this.lotTableDao = lotTableDao;
	}

	public List<LotTableDto> getLotTableByProjectIdAndCharge(Integer id, String charge) {
		return lotTableDao.getLotTableByProjectIdAndCharge(id, charge);
	}
	
	public List<LotTableDto> getLotTableByAvenantIdAndCharge(Integer id, String charge) {
		return lotTableDao.getLotTableByAvenantIdAndCharge(id, charge);
	}
}
