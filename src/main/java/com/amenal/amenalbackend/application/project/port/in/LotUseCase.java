package com.amenal.amenalbackend.application.project.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.port.out.LotDao;

public class LotUseCase {
	
	private LotDao lotDao;
	
	public LotUseCase(LotDao lotDao) {
		this.lotDao = lotDao;
	}

	public Lot findLotById(Integer id) {
	    return lotDao.findLotById(id);
	}
	
	public List<Lot> getLotsByAvenantId(Integer id) {
		return lotDao.getLotsByAvenantId(id);
	}

	public List<Lot> findAllLots() {
		return lotDao.findAllLots();
	}
	
	public Lot saveLot(Lot lot) {
		return lotDao.saveLot(lot);
	}
	
	public Lot updateLot(Lot lot) {
		return lotDao.updateLot(lot);
	}
	
	public void deleteLot(Integer id) {
		lotDao.deleteLot(id);
	}
	
	public List<Lot> saveLots(List<Lot> lots) {
		List<Lot> savedLots = new ArrayList<Lot>();
		for(Lot lot: lots )
			savedLots.add(lotDao.saveLot(lot));
		return savedLots;
	} 
	

}
