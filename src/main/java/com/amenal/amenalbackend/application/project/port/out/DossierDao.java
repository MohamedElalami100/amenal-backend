package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Dossier;

public interface DossierDao {
	Dossier findDossierById(Integer id);
	
	List<Dossier> findAllDossiers();
	
	Dossier saveDossier(Dossier dossier);
	
	Dossier updateDossier(Dossier dossier);
	
	void deleteDossier(Integer id);
	
}
