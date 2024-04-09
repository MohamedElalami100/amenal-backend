package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteAttenteDao;

public class DetailQualiteAttenteUseCase {
	
	private DetailQualiteAttenteDao banqueDao;
	
	public DetailQualiteAttenteUseCase(DetailQualiteAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailQualiteAttente findDetailQualiteAttenteById(Integer id) {
	    return banqueDao.findDetailQualiteAttenteById(id);
	}

	public List<DetailQualiteAttente> findAllDetailQualiteAttentes() {
		return updateErreurs(banqueDao.findAllDetailQualiteAttentes());
	}
	
	public List<DetailQualiteAttente> getDetailQualiteAttentesByAvenantId(Integer id) {
		return updateErreurs(banqueDao.getDetailQualiteAttentesByAvenantId(id));
	}
	
	public DetailQualiteAttente saveDetailQualiteAttente(DetailQualiteAttente banque) {
		return banqueDao.saveDetailQualiteAttente(banque);
	}
	
	public DetailQualiteAttente saveDetailQualiteAttenteWithErreur(DetailQualiteAttente banque) {
		return banqueDao.saveDetailQualiteAttenteWithErreur(banque);
	}
	
	public DetailQualiteAttente updateDetailQualiteAttente(DetailQualiteAttente banque) {
		DetailQualiteAttente detailQualiteAttente = banqueDao.updateDetailQualiteAttente(banque);
		return banqueDao.saveDetailQualiteAttenteWithErreur(detailQualiteAttente);	}
	
	public void deleteDetailQualiteAttente(Integer id) {
		banqueDao.deleteDetailQualiteAttente(id);
	}
	
	public List<DetailQualiteAttente> saveAllDetailQualiteAttente(List<DetailQualiteAttente> detailProduitAttentes) {
		List<DetailQualiteAttente> addedDetails = new ArrayList<>();
				
		for(DetailQualiteAttente detailProduitAttente: detailProduitAttentes) {
			try {
				DetailQualiteAttente addedDetail = banqueDao.saveDetailQualiteAttente(detailProduitAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}	
		
		return updateErreurs(addedDetails) ;	
	}
	
	private List<DetailQualiteAttente> updateErreurs(List<DetailQualiteAttente> detailProduitAttentes) {
		List<DetailQualiteAttente> addedDetailsWithErreurs = new ArrayList<>();

		for(DetailQualiteAttente detailProduitAttente: detailProduitAttentes) {
			try {
				DetailQualiteAttente addedDetail = banqueDao.saveDetailQualiteAttenteWithErreur(detailProduitAttente);
				addedDetailsWithErreurs.add(addedDetail);
			} catch (Exception e) {
			}
		}
		
		return addedDetailsWithErreurs;
	}

}
