package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Remise;

public interface RemiseDao {
	Remise findRemiseById(Integer id);
	
	List<Remise> findAllRemises();
	
	Remise saveRemise(Remise remise);
	
	Remise updateRemise(Remise remise);
	
	void deleteRemise(Integer id);

}
