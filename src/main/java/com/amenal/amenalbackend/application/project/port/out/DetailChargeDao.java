package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailCharge;

public interface DetailChargeDao {
	DetailCharge findDetailChargeById(Integer id);
	
	List<DetailCharge> findAllDetailCharges();
	
	DetailCharge saveDetailCharge(DetailCharge detailCharge);
	
	DetailCharge updateDetailCharge(DetailCharge detailCharge);
	
	void deleteDetailCharge(Integer id);
	
}
