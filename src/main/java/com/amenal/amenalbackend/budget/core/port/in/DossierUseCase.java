package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Dossier;
import com.amenal.amenalbackend.budget.core.port.out.DossierDao;

public class DossierUseCase {
	
	private DossierDao dossierDao;
	
	public DossierUseCase(DossierDao dossierDao) {
		this.dossierDao = dossierDao;
	}

	public Dossier findDossierById(Integer id) {
	    return dossierDao.findDossierById(id);
	}

	public List<Dossier> findAllDossiers() {
		return dossierDao.findAllDossiers();
	}
	
	public Dossier saveDossier(Dossier dossier) {
		return dossierDao.saveDossier(dossier);
	}
	
	public Dossier updateDossier(Dossier dossier) {
		return dossierDao.updateDossier(dossier);
	}
	
	public void deleteDossier(Integer id) {
		dossierDao.deleteDossier(id);
	}
	

}
