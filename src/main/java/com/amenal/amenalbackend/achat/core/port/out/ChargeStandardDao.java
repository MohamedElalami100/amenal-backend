package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.ChargeStandard;

public interface ChargeStandardDao {
	ChargeStandard findChargeStandardById(Integer id);
	
	List<ChargeStandard> findAllChargeStandards();
	
	ChargeStandard saveChargeStandard(ChargeStandard chargeStandard);
	
	ChargeStandard updateChargeStandard(ChargeStandard chargeStandard);
	
	void deleteChargeStandard(Integer id);

}
