package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Banque;
import com.amenal.amenalbackend.application.project.port.out.BanqueDao;

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
