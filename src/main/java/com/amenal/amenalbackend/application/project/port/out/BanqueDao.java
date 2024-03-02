package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Banque;

public interface BanqueDao {
	Banque findBanqueById(Integer id);
	
	List<Banque> findAllBanques();
	
	Banque saveBanque(Banque banque);
	
	Banque updateBanque(Banque banque);
	
	void deleteBanque(Integer id);
	
}
