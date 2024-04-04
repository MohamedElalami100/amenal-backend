package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Besoin;

public interface BesoinDao {
	Besoin findBesoinById(Integer id);
	
	List<Besoin> findAllBesoins();
	
	Besoin saveBesoin(Besoin besoin);
	
	Besoin updateBesoin(Besoin besoin);
	
	void deleteBesoin(Integer id);

}
