package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.dto.TacheTableDto;
import com.amenal.amenalbackend.application.project.port.out.TacheTableDao;

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
