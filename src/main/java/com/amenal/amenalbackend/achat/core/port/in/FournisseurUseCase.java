package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Fournisseur;
import com.amenal.amenalbackend.achat.core.port.out.FournisseurDao;

public class FournisseurUseCase {
	
	private FournisseurDao fournisseurDao;
	
	public FournisseurUseCase(FournisseurDao fournisseurDao) {
		this.fournisseurDao = fournisseurDao;
	}

	public Fournisseur findFournisseurById(Integer id) {
	    return fournisseurDao.findFournisseurById(id);
	}

	public List<Fournisseur> findAllFournisseurs() {
		return fournisseurDao.findAllFournisseurs();
	}
	
	public Fournisseur saveFournisseur(Fournisseur fournisseur) {
		return fournisseurDao.saveFournisseur(fournisseur);
	}
	
	public Fournisseur updateFournisseur(Fournisseur fournisseur) {
		return fournisseurDao.updateFournisseur(fournisseur);
	}
	
	public void deleteFournisseur(Integer id) {
		fournisseurDao.deleteFournisseur(id);
	}	

}
