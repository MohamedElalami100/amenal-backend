package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiAttenteDao;

public class DetailDelaiAttenteUseCase {
	
	private DetailDelaiAttenteDao banqueDao;
	
	public DetailDelaiAttenteUseCase(DetailDelaiAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailDelaiAttente findDetailDelaiAttenteById(Integer id) {
	    return banqueDao.findDetailDelaiAttenteById(id);
	}

	public List<DetailDelaiAttente> findAllDetailDelaiAttentes() {
		return updateErreurs(banqueDao.findAllDetailDelaiAttentes()) ;
	}
	
	public List<DetailDelaiAttente> getDetailDelaiAttentesByAvenantId(Integer id) {
		return updateErreurs(banqueDao.getDetailDelaiAttentesByAvenantId(id));
	}
	
	public DetailDelaiAttente saveDetailDelaiAttente(DetailDelaiAttente banque) {
		return banqueDao.saveDetailDelaiAttente(banque);
	}
	
	public DetailDelaiAttente saveDetailDelaiAttenteWithErreur(DetailDelaiAttente banque) {
		return banqueDao.saveDetailDelaiAttenteWithErreur(banque);
	}
	
	public DetailDelaiAttente updateDetailDelaiAttente(DetailDelaiAttente banque) {
		DetailDelaiAttente detailDelaiAttente = banqueDao.updateDetailDelaiAttente(banque);
		return banqueDao.saveDetailDelaiAttenteWithErreur(detailDelaiAttente);	
	}
	
	public void deleteDetailDelaiAttente(Integer id) {
		banqueDao.deleteDetailDelaiAttente(id);
	}
	
	public List<DetailDelaiAttente> saveAllDetailDelaiAttente(List<DetailDelaiAttente> detailDelaiAttentes) {
		List<DetailDelaiAttente> addedDetails = new ArrayList<>();
				
		for(DetailDelaiAttente detailDelaiAttente: detailDelaiAttentes) {
			try {
				DetailDelaiAttente addedDetail = banqueDao.saveDetailDelaiAttente(detailDelaiAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}	
		
		return updateErreurs(addedDetails) ;	
	}
	
	private List<DetailDelaiAttente> updateErreurs(List<DetailDelaiAttente> detailDelaiAttentes) {
		List<DetailDelaiAttente> addedDetailsWithErreurs = new ArrayList<>();

		for(DetailDelaiAttente detailDelaiAttente: detailDelaiAttentes) {
			try {
				DetailDelaiAttente addedDetail = banqueDao.saveDetailDelaiAttenteWithErreur(detailDelaiAttente);
				addedDetailsWithErreurs.add(addedDetail);
			} catch (Exception e) {
			}
		}
		
		return addedDetailsWithErreurs;
	}
	

}
