package com.amenal.amenalbackend.application.project.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.TacheDao;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

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

	public Tache saveTache(Tache tache) throws DuplicateElementException {
		// if tache existes:
		List<Tache> currentTaches = null;
		try {
			currentTaches = tacheDao
					.getTachesByAvenantId(tache.getProduit().getMetre().getBudget().getAvenant().getId());
		} catch (Exception e) {
			System.out.println(e);
		}
		Tache savedTache = tacheDao.saveTache(tache);

		if (currentTaches != null && currentTaches.stream().anyMatch(obj -> obj.getId() == savedTache.getId()))
			throw new DuplicateElementException("Tache Existe Deja");

		// else:
		return savedTache;
	}

	public Tache updateTache(Tache tache) throws DuplicateElementException {
		return tacheDao.updateTache(tache);
	}

	public void deleteTache(Integer id) {
		tacheDao.deleteTache(id);
	}

	public List<Tache> saveTaches(List<Tache> taches) {
		List<Tache> savedTaches = new ArrayList<Tache>();
		for (Tache tache : taches)
			savedTaches.add(tacheDao.saveTache(tache));
		return savedTaches;
	}

}
