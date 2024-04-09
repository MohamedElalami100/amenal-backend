package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Banque;

public interface BanqueDao {
	Banque findBanqueById(Integer id);
	
	List<Banque> findAllBanques();
	
	Banque saveBanque(Banque banque);
	
	Banque updateBanque(Banque banque);
	
	void deleteBanque(Integer id);
	
}
