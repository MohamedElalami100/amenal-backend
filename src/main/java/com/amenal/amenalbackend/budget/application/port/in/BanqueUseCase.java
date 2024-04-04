package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Banque;
import com.amenal.amenalbackend.budget.application.port.out.BanqueDao;

public class BanqueUseCase {
	
	private BanqueDao banqueDao;
	
	public BanqueUseCase(BanqueDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public Banque findBanqueById(Integer id) {
	    return banqueDao.findBanqueById(id);
	}

	public List<Banque> findAllBanques() {
		return banqueDao.findAllBanques();
	}
	
	public Banque saveBanque(Banque banque) {
		return banqueDao.saveBanque(banque);
	}
	
	public Banque updateBanque(Banque banque) {
		return banqueDao.updateBanque(banque);
	}
	
	public void deleteBanque(Integer id) {
		banqueDao.deleteBanque(id);
	}
	

}
