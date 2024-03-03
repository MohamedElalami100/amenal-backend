package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

public interface DetailChargeDao {
	DetailCharge findDetailChargeById(Integer id);
	
	List<DetailCharge> findAllDetailCharges();
	
	DetailCharge saveDetailCharge(DetailCharge detailCharge) throws DuplicateElementException;
	
	DetailCharge updateDetailCharge(DetailCharge detailCharge) throws DuplicateElementException;
	
	void deleteDetailCharge(Integer id);
	
}
