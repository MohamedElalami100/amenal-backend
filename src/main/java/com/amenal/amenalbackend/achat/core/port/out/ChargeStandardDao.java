package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ChargeStandard;

public interface ChargeStandardDao {
	ChargeStandard findChargeStandardById(Integer id);
	
	List<ChargeStandard> findAllChargeStandards();
	
	ChargeStandard saveChargeStandard(ChargeStandard chargeStandard);
	
	ChargeStandard updateChargeStandard(ChargeStandard chargeStandard);
	
	void deleteChargeStandard(Integer id);

}
