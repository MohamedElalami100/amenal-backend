package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailProduit;
import com.amenal.amenalbackend.application.project.port.out.DetailProduitDao;

public class DetailProduitUseCase {
	
	private DetailProduitDao banqueDao;
	
	public DetailProduitUseCase(DetailProduitDao banqueDao) {
		this.banqueDao = banqueDao;
	}

	public DetailProduit findDetailProduitById(Integer id) {
	    return banqueDao.findDetailProduitById(id);
	}

	public List<DetailProduit> findAllDetailProduits() {
		return banqueDao.findAllDetailProduits();
	}
	
	public DetailProduit saveDetailProduit(DetailProduit banque) {
		return banqueDao.saveDetailProduit(banque);
	}
	
	public DetailProduit updateDetailProduit(DetailProduit banque) {
		return banqueDao.updateDetailProduit(banque);
	}
	
	public void deleteDetailProduit(Integer id) {
		banqueDao.deleteDetailProduit(id);
	}
	

}
