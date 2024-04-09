package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Besoin;
import com.amenal.amenalbackend.achat.infrastructure.dto.BesoinDto;

public interface BesoinDao {
	BesoinDto findBesoinById(Integer id);
	
	List<BesoinDto> findAllBesoins();
	
	Besoin saveBesoin(Besoin besoin);
	
	Besoin updateBesoin(Besoin besoin);
	
	void deleteBesoin(Integer id);

}
