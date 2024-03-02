package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Lot;

public interface LotDao {
	Lot findLotById(Integer id);
	
	List<Lot> findAllLots();
	
	Lot saveLot(Lot lot);
	
	Lot updateLot(Lot lot);
	
	void deleteLot(Integer id);

	List<Lot> getLotsByAvenantId(Integer id);
	
}
