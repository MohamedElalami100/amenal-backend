package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

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
	
	public DetailProduit saveDetailProduit(DetailProduit banque) throws DuplicateElementException {
		return banqueDao.saveDetailProduit(banque);
	}
	
	public DetailProduit updateDetailProduit(DetailProduit banque) throws DuplicateElementException {
		return banqueDao.updateDetailProduit(banque);
	}
	
	public void deleteDetailProduit(Integer id) {
		banqueDao.deleteDetailProduit(id);
	}
	

}
