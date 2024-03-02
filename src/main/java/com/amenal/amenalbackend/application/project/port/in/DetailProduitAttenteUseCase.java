package com.amenal.amenalbackend.application.project.port.in;

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
		return banqueDao.findAllDetailProduitAttentes();
	}
	
	public List<DetailProduitAttente> getDetailProduitAttentesByAvenantId(Integer id) {
		return banqueDao.getDetailProduitAttentesByAvenantId(id);
	}
	
	public DetailProduitAttente saveDetailProduitAttente(DetailProduitAttente banque) {
		return banqueDao.saveDetailProduitAttente(banque);
	}
	
	public DetailProduitAttente saveDetailProduitAttenteWithErreur(DetailProduitAttente banque) {
		return banqueDao.saveDetailProduitAttenteWithErreur(banque);
	}
	
	public DetailProduitAttente updateDetailProduitAttente(DetailProduitAttente banque) {
		return banqueDao.updateDetailProduitAttente(banque);
	}
	
	public void deleteDetailProduitAttente(Integer id) {
		banqueDao.deleteDetailProduitAttente(id);
	}
	

}
