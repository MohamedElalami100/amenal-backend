package com.amenal.amenalbackend.application.project.port.in;

import java.util.ArrayList;
import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.port.out.LotDao;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

public class LotUseCase {

	private LotDao lotDao;

	public LotUseCase(LotDao lotDao) {
		this.lotDao = lotDao;
	}

	public Lot findLotById(Integer id) {
		return lotDao.findLotById(id);
	}

	public List<Lot> getLotsByAvenantId(Integer id) {
		return lotDao.getLotsByAvenantId(id);
	}

	public List<Lot> getLotsByProjectId(Integer id) {
		return lotDao.getLotsByProjectId(id);
	}

	public List<Lot> findAllLots() {
		return lotDao.findAllLots();
	}

	public Lot saveLot(Lot lot) throws DuplicateElementException {
		// if lot existes:
		List<Lot> currentLots = null;
		try {
			currentLots = lotDao.getLotsByProjectId(lot.getProject().getId());
		} catch (Exception e) {
			System.out.println(e);
		}
		Lot savedLot = lotDao.saveLot(lot);
		if (currentLots != null && currentLots.stream().anyMatch(obj -> obj.getId() == savedLot.getId()))
			throw new DuplicateElementException("Lot Existe Deja");

		// else:
		return savedLot;
	}

	public Lot updateLot(Lot lot) throws DuplicateElementException {
		return lotDao.updateLot(lot);
	}

	public void deleteLot(Integer id) {
		lotDao.deleteLot(id);
	}

	public List<Lot> saveLots(List<Lot> lots) {
		List<Lot> savedLots = new ArrayList<Lot>();
		for (Lot lot : lots)
			savedLots.add(lotDao.saveLot(lot));
		return savedLots;
	}

}
