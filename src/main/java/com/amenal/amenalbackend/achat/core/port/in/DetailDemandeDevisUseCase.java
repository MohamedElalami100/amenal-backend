package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailDemandeDevis;
import com.amenal.amenalbackend.achat.core.port.out.DetailDemandeDevisDao;

public class DetailDemandeDevisUseCase {
	
	private DetailDemandeDevisDao detailDemandeDevisDao;
	
	public DetailDemandeDevisUseCase(DetailDemandeDevisDao detailDemandeDevisDao) {
		this.detailDemandeDevisDao = detailDemandeDevisDao;
	}

	public DetailDemandeDevis findDetailDemandeDevisById(Integer id) {
	    return detailDemandeDevisDao.findDetailDemandeDevisById(id);
	}

	public List<DetailDemandeDevis> findAllDetailDemandeDeviss() {
		return detailDemandeDevisDao.findAllDetailDemandeDeviss();
	}
	
	public DetailDemandeDevis saveDetailDemandeDevis(DetailDemandeDevis detailDemandeDevis) {
		return detailDemandeDevisDao.saveDetailDemandeDevis(detailDemandeDevis);
	}
	
	public DetailDemandeDevis updateDetailDemandeDevis(DetailDemandeDevis detailDemandeDevis) {
		return detailDemandeDevisDao.updateDetailDemandeDevis(detailDemandeDevis);
	}
	
	public void deleteDetailDemandeDevis(Integer id) {
		detailDemandeDevisDao.deleteDetailDemandeDevis(id);
	}	

}
