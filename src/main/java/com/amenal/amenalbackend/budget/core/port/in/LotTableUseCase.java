package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.port.out.LotTableDao;
import com.amenal.amenalbackend.budget.infrastructure.dto.LotTableDto;

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
