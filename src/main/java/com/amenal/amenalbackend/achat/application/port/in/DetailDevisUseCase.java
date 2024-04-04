package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailDevis;
import com.amenal.amenalbackend.achat.application.port.out.DetailDevisDao;

public class DetailDevisUseCase {
	
	private DetailDevisDao detailDevisDao;
	
	public DetailDevisUseCase(DetailDevisDao detailDevisDao) {
		this.detailDevisDao = detailDevisDao;
	}

	public DetailDevis findDetailDevisById(Integer id) {
	    return detailDevisDao.findDetailDevisById(id);
	}

	public List<DetailDevis> findAllDetailDeviss() {
		return detailDevisDao.findAllDetailDeviss();
	}
	
	public DetailDevis saveDetailDevis(DetailDevis detailDevis) {
		return detailDevisDao.saveDetailDevis(detailDevis);
	}
	
	public DetailDevis updateDetailDevis(DetailDevis detailDevis) {
		return detailDevisDao.updateDetailDevis(detailDevis);
	}
	
	public void deleteDetailDevis(Integer id) {
		detailDevisDao.deleteDetailDevis(id);
	}	

}
