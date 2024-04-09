package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.TacheTableDto;
import com.amenal.amenalbackend.budget.application.port.out.TacheTableDao;

public class TacheTableUseCase {
	
	private TacheTableDao tacheTableDao;
	
	public TacheTableUseCase(TacheTableDao tacheTableDao) {
		this.tacheTableDao = tacheTableDao;
	}

	public List<TacheTableDto> getTacheTableByProjectIdAndCharge(Integer id, String charge) {
		return tacheTableDao.getTacheTableByProjectIdAndCharge(id, charge);
	}
	
	public List<TacheTableDto> getTacheTableByAvenantIdAndCharge(Integer id, String charge) {
		return tacheTableDao.getTacheTableByAvenantIdAndCharge(id, charge);
	}
	
}
