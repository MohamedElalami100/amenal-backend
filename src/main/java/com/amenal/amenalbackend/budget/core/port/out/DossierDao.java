package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Dossier;

public interface DossierDao {
	Dossier findDossierById(Integer id);
	
	List<Dossier> findAllDossiers();
	
	Dossier saveDossier(Dossier dossier);
	
	Dossier updateDossier(Dossier dossier);
	
	void deleteDossier(Integer id);
	
}
