package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailChargeAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeAttenteDao;

public class DetailChargeAttenteUseCase {
	
	private DetailChargeAttenteDao banqueDao;
	
	public DetailChargeAttenteUseCase(DetailChargeAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailChargeAttente findDetailChargeAttenteById(Integer id) {
	    return banqueDao.findDetailChargeAttenteById(id);
	}

	public List<DetailChargeAttente> findAllDetailChargeAttentes() {
		return updateErreurs(banqueDao.findAllDetailChargeAttentes());
	}
	
	public List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id) {
		return updateErreurs(banqueDao.getDetailChargeAttentesByAvenantId(id));
	}
	
	public DetailChargeAttente saveDetailChargeAttente(DetailChargeAttente banque) {
		return banqueDao.saveDetailChargeAttente(banque);
	}
	
	public DetailChargeAttente saveDetailChargeAttenteWithErreur(DetailChargeAttente banque) {
		return banqueDao.saveDetailChargeAttenteWithErreur(banque);
	}
	
	public DetailChargeAttente updateDetailChargeAttente(DetailChargeAttente banque) {
		DetailChargeAttente detailChargeAttente = banqueDao.updateDetailChargeAttente(banque);
		return banqueDao.saveDetailChargeAttenteWithErreur(detailChargeAttente);
	}
	
	public void deleteDetailChargeAttente(Integer id) {
		banqueDao.deleteDetailChargeAttente(id);
	}
	
	public List<DetailChargeAttente> saveAllDetailChargeAttente(List<DetailChargeAttente> detailChargeAttentes) {
		List<DetailChargeAttente> addedDetails = new ArrayList<>();
				
		for(DetailChargeAttente detailChargeAttente: detailChargeAttentes) {
			try {
				DetailChargeAttente addedDetail = banqueDao.saveDetailChargeAttente(detailChargeAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}	
		
		return updateErreurs(addedDetails) ;	
	}
	
	private List<DetailChargeAttente> updateErreurs(List<DetailChargeAttente> detailChargeAttentes) {
		List<DetailChargeAttente> addedDetailsWithErreurs = new ArrayList<>();

		for(DetailChargeAttente detailChargeAttente: detailChargeAttentes) {
			try {
				DetailChargeAttente addedDetail = banqueDao.saveDetailChargeAttenteWithErreur(detailChargeAttente);
				addedDetailsWithErreurs.add(addedDetail);
			} catch (Exception e) {
			}
		}
		
		return addedDetailsWithErreurs;
	}
	

}
