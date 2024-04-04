package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Lot;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface LotDao {
	Lot findLotById(Integer id);
	
	List<Lot> findAllLots();
	
	Lot saveLot(Lot lot);
	
	Lot updateLot(Lot lot) throws DuplicateElementException;
	
	void deleteLot(Integer id);

	List<Lot> getLotsByAvenantId(Integer id);
	
	List<Lot> getLotsByProjectId(Integer id);
	
}
