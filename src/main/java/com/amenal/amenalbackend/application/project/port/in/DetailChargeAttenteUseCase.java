package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailChargeAttente;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeAttenteDao;

public class DetailChargeAttenteUseCase {
	
	private DetailChargeAttenteDao banqueDao;
	
	public DetailChargeAttenteUseCase(DetailChargeAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailChargeAttente findDetailChargeAttenteById(Integer id) {
	    return banqueDao.findDetailChargeAttenteById(id);
	}

	public List<DetailChargeAttente> findAllDetailChargeAttentes() {
		return banqueDao.findAllDetailChargeAttentes();
	}
	
	public List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id) {
		return banqueDao.getDetailChargeAttentesByAvenantId(id);
	}
	
	public DetailChargeAttente saveDetailChargeAttente(DetailChargeAttente banque) {
		return banqueDao.saveDetailChargeAttente(banque);
	}
	
	public DetailChargeAttente saveDetailChargeAttenteWithErreur(DetailChargeAttente banque) {
		return banqueDao.saveDetailChargeAttenteWithErreur(banque);
	}
	
	public DetailChargeAttente updateDetailChargeAttente(DetailChargeAttente banque) {
		return banqueDao.updateDetailChargeAttente(banque);
	}
	
	public void deleteDetailChargeAttente(Integer id) {
		banqueDao.deleteDetailChargeAttente(id);
	}
	

}
