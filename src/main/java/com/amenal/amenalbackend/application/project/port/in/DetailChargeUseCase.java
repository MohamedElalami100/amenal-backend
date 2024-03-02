package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeDao;

public class DetailChargeUseCase {
	
	private DetailChargeDao banqueDao;
	
	public DetailChargeUseCase(DetailChargeDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailCharge findDetailChargeById(Integer id) {
	    return banqueDao.findDetailChargeById(id);
	}

	public List<DetailCharge> findAllDetailCharges() {
		return banqueDao.findAllDetailCharges();
	}
	
	public DetailCharge saveDetailCharge(DetailCharge banque) {
		return banqueDao.saveDetailCharge(banque);
	}
	
	public DetailCharge updateDetailCharge(DetailCharge banque) {
		return banqueDao.updateDetailCharge(banque);
	}
	
	public void deleteDetailCharge(Integer id) {
		banqueDao.deleteDetailCharge(id);
	}
	

}
