package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ChargeStandard;
import com.amenal.amenalbackend.achat.application.port.out.ChargeStandardDao;

public class ChargeStandardUseCase {
	
	private ChargeStandardDao chargeStandardDao;
	
	public ChargeStandardUseCase(ChargeStandardDao chargeStandardDao) {
		this.chargeStandardDao = chargeStandardDao;
	}

	public ChargeStandard findChargeStandardById(Integer id) {
	    return chargeStandardDao.findChargeStandardById(id);
	}

	public List<ChargeStandard> findAllChargeStandards() {
		return chargeStandardDao.findAllChargeStandards();
	}
	
	public ChargeStandard saveChargeStandard(ChargeStandard chargeStandard) {
		return chargeStandardDao.saveChargeStandard(chargeStandard);
	}
	
	public ChargeStandard updateChargeStandard(ChargeStandard chargeStandard) {
		return chargeStandardDao.updateChargeStandard(chargeStandard);
	}
	
	public void deleteChargeStandard(Integer id) {
		chargeStandardDao.deleteChargeStandard(id);
	}	

}
