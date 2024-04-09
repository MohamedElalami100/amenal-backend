package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.CompteBanquaire;
import com.amenal.amenalbackend.achat.core.port.out.CompteBanquaireDao;

public class CompteBanquaireUseCase {
	
	private CompteBanquaireDao compteBanquaireDao;
	
	public CompteBanquaireUseCase(CompteBanquaireDao compteBanquaireDao) {
		this.compteBanquaireDao = compteBanquaireDao;
	}

	public CompteBanquaire findCompteBanquaireById(Integer id) {
	    return compteBanquaireDao.findCompteBanquaireById(id);
	}

	public List<CompteBanquaire> findAllCompteBanquaires() {
		return compteBanquaireDao.findAllCompteBanquaires();
	}
	
	public CompteBanquaire saveCompteBanquaire(CompteBanquaire compteBanquaire) {
		return compteBanquaireDao.saveCompteBanquaire(compteBanquaire);
	}
	
	public CompteBanquaire updateCompteBanquaire(CompteBanquaire compteBanquaire) {
		return compteBanquaireDao.updateCompteBanquaire(compteBanquaire);
	}
	
	public void deleteCompteBanquaire(Integer id) {
		compteBanquaireDao.deleteCompteBanquaire(id);
	}	

}
