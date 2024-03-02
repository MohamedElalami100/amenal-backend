package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Tache;

public interface TacheDao {
	Tache findTacheById(Integer id);
	
	List<Tache> findAllTaches();
	
	Tache saveTache(Tache tache);
	
	Tache updateTache(Tache tache);
	
	void deleteTache(Integer id);
	
}
