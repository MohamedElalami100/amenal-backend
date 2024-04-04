package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.DetailCharge;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface DetailChargeDao {
	DetailCharge findDetailChargeById(Integer id);
	
	List<DetailCharge> findAllDetailCharges();
	
	DetailCharge saveDetailCharge(DetailCharge detailCharge) throws DuplicateElementException;
	
	DetailCharge updateDetailCharge(DetailCharge detailCharge) throws DuplicateElementException;
	
	void deleteDetailCharge(Integer id);
	
}
