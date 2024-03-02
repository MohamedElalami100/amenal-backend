package com.amenal.amenalbackend.application.project.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.TacheDao;

public class TacheUseCase {
	
	private TacheDao tacheDao;
	
	public TacheUseCase(TacheDao tacheDao) {
		this.tacheDao = tacheDao;
	}

	public Tache findTacheById(Integer id) {
	    return tacheDao.findTacheById(id);
	}

	public List<Tache> findAllTaches() {
		return tacheDao.findAllTaches();
	}
	
	public Tache saveTache(Tache tache) {
		return tacheDao.saveTache(tache);
	}
	
	public Tache updateTache(Tache tache) {
		return tacheDao.updateTache(tache);
	}
	
	public void deleteTache(Integer id) {
		tacheDao.deleteTache(id);
	}
	
	public List<Tache> saveTaches(List<Tache> taches) {
		List<Tache> savedTaches = new ArrayList<Tache>();
		for(Tache tache: taches )
			savedTaches.add(tacheDao.saveTache(tache));
		return savedTaches;
	} 

}
