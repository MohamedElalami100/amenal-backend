package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

public interface TacheDao {
	Tache findTacheById(Integer id);
	
	List<Tache> findAllTaches();
	
	Tache saveTache(Tache tache);
	
	Tache updateTache(Tache tache) throws DuplicateElementException;
	
	void deleteTache(Integer id);

	List<Tache> getTachesByAvenantId(Integer id);
	
}
