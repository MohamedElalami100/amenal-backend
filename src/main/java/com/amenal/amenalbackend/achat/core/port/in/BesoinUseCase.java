package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Besoin;
import com.amenal.amenalbackend.achat.core.port.out.BesoinDao;
import com.amenal.amenalbackend.achat.infrastructure.dto.BesoinDto;

public class BesoinUseCase {
	
	private BesoinDao besoinDao;
	
	public BesoinUseCase(BesoinDao besoinDao) {
		this.besoinDao = besoinDao;
	}

	public BesoinDto findBesoinById(Integer id) {
	    return besoinDao.findBesoinById(id);
	}

	public List<BesoinDto> findAllBesoins() {
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
