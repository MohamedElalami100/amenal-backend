package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface TacheDao {
	Tache findTacheById(Integer id);
	
	List<Tache> findAllTaches();
	
	Tache saveTache(Tache tache);
	
	Tache updateTache(Tache tache) throws DuplicateElementException;
	
	void deleteTache(Integer id);

	List<Tache> getTachesByAvenantId(Integer id);

    List<Tache> getTachesInOtherAvenants(Integer id);
}
