package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.application.project.port.out.DetailQualiteAttenteDao;

public class DetailQualiteAttenteUseCase {
	
	private DetailQualiteAttenteDao banqueDao;
	
	public DetailQualiteAttenteUseCase(DetailQualiteAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailQualiteAttente findDetailQualiteAttenteById(Integer id) {
	    return banqueDao.findDetailQualiteAttenteById(id);
	}

	public List<DetailQualiteAttente> findAllDetailQualiteAttentes() {
		return banqueDao.findAllDetailQualiteAttentes();
	}
	
	public List<DetailQualiteAttente> getDetailQualiteAttentesByAvenantId(Integer id) {
		return banqueDao.getDetailQualiteAttentesByAvenantId(id);
	}
	
	public DetailQualiteAttente saveDetailQualiteAttente(DetailQualiteAttente banque) {
		return banqueDao.saveDetailQualiteAttente(banque);
	}
	
	public DetailQualiteAttente saveDetailQualiteAttenteWithErreur(DetailQualiteAttente banque) {
		return banqueDao.saveDetailQualiteAttenteWithErreur(banque);
	}
	
	public DetailQualiteAttente updateDetailQualiteAttente(DetailQualiteAttente banque) {
		return banqueDao.updateDetailQualiteAttente(banque);
	}
	
	public void deleteDetailQualiteAttente(Integer id) {
		banqueDao.deleteDetailQualiteAttente(id);
	}
	

}
