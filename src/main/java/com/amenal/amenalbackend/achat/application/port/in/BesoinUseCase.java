package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Besoin;
import com.amenal.amenalbackend.achat.application.port.out.BesoinDao;

public class BesoinUseCase {
	
	private BesoinDao besoinDao;
	
	public BesoinUseCase(BesoinDao besoinDao) {
		this.besoinDao = besoinDao;
	}

	public Besoin findBesoinById(Integer id) {
	    return besoinDao.findBesoinById(id);
	}

	public List<Besoin> findAllBesoins() {
		return besoinDao.findAllBesoins();
	}
	
	public Besoin saveBesoin(Besoin besoin) {
		return besoinDao.saveBesoin(besoin);
	}
	
	public Besoin updateBesoin(Besoin besoin) {
		return besoinDao.updateBesoin(besoin);
	}
	
	public void deleteBesoin(Integer id) {
		besoinDao.deleteBesoin(id);
	}	

}
