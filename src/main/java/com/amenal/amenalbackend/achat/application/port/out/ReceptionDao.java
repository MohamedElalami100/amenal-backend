package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Reception;

public interface ReceptionDao {
	Reception findReceptionById(Integer id);
	
	List<Reception> findAllReceptions();
	
	Reception saveReception(Reception reception);
	
	Reception updateReception(Reception reception);
	
	void deleteReception(Integer id);

}
