package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.application.project.port.out.DetailDelaiAttenteDao;

public class DetailDelaiAttenteUseCase {
	
	private DetailDelaiAttenteDao banqueDao;
	
	public DetailDelaiAttenteUseCase(DetailDelaiAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailDelaiAttente findDetailDelaiAttenteById(Integer id) {
	    return banqueDao.findDetailDelaiAttenteById(id);
	}

	public List<DetailDelaiAttente> findAllDetailDelaiAttentes() {
		return banqueDao.findAllDetailDelaiAttentes();
	}
	
	public List<DetailDelaiAttente> getDetailDelaiAttentesByAvenantId(Integer id) {
		return banqueDao.getDetailDelaiAttentesByAvenantId(id);
	}
	
	public DetailDelaiAttente saveDetailDelaiAttente(DetailDelaiAttente banque) {
		return banqueDao.saveDetailDelaiAttente(banque);
	}
	
	public DetailDelaiAttente saveDetailDelaiAttenteWithErreur(DetailDelaiAttente banque) {
		return banqueDao.saveDetailDelaiAttenteWithErreur(banque);
	}
	
	public DetailDelaiAttente updateDetailDelaiAttente(DetailDelaiAttente banque) {
		return banqueDao.updateDetailDelaiAttente(banque);
	}
	
	public void deleteDetailDelaiAttente(Integer id) {
		banqueDao.deleteDetailDelaiAttente(id);
	}
	

}
