package com.amenal.amenalbackend.application.project.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailProduitAttente;
import com.amenal.amenalbackend.application.project.port.out.DetailProduitAttenteDao;

public class DetailProduitAttenteUseCase {
	
	private DetailProduitAttenteDao banqueDao;
	
	public DetailProduitAttenteUseCase(DetailProduitAttenteDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailProduitAttente findDetailProduitAttenteById(Integer id) {
	    return banqueDao.findDetailProduitAttenteById(id);
	}

	public List<DetailProduitAttente> findAllDetailProduitAttentes() {
		return updateErreurs(banqueDao.findAllDetailProduitAttentes()) ;
	}
	
	public List<DetailProduitAttente> getDetailProduitAttentesByAvenantId(Integer id) {
		return updateErreurs(banqueDao.getDetailProduitAttentesByAvenantId(id));
	}
	
	public DetailProduitAttente saveDetailProduitAttente(DetailProduitAttente banque) {
		return banqueDao.saveDetailProduitAttente(banque);
	}
	
	public DetailProduitAttente saveDetailProduitAttenteWithErreur(DetailProduitAttente banque) {
		return banqueDao.saveDetailProduitAttenteWithErreur(banque);
	}
	
	public DetailProduitAttente updateDetailProduitAttente(DetailProduitAttente banque) {
		DetailProduitAttente detailProduitAttente = banqueDao.updateDetailProduitAttente(banque);
		return banqueDao.saveDetailProduitAttenteWithErreur(detailProduitAttente);	}
	
	public void deleteDetailProduitAttente(Integer id) {
		banqueDao.deleteDetailProduitAttente(id);
	}
	
	public List<DetailProduitAttente> saveAllDetailProduitAttente(List<DetailProduitAttente> detailProduitAttentes) {
		List<DetailProduitAttente> addedDetails = new ArrayList<>();
				
		for(DetailProduitAttente detailProduitAttente: detailProduitAttentes) {
			try {
				DetailProduitAttente addedDetail = banqueDao.saveDetailProduitAttente(detailProduitAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}	
		
		return updateErreurs(addedDetails) ;	
	}
	
	private List<DetailProduitAttente> updateErreurs(List<DetailProduitAttente> detailProduitAttentes) {
		List<DetailProduitAttente> addedDetailsWithErreurs = new ArrayList<>();

		for(DetailProduitAttente detailProduitAttente: detailProduitAttentes) {
			try {
				DetailProduitAttente addedDetail = banqueDao.saveDetailProduitAttenteWithErreur(detailProduitAttente);
				addedDetailsWithErreurs.add(addedDetail);
			} catch (Exception e) {
			}
		}
		
		return addedDetailsWithErreurs;
	}
	

}
