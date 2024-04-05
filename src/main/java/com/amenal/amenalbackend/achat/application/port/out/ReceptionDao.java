package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Reception;
import com.amenal.amenalbackend.achat.application.dto.ReceptionDto;

public interface ReceptionDao {
	ReceptionDto findReceptionById(Integer id);
	
	List<ReceptionDto> findAllReceptions();
	
	Reception saveReception(Reception reception);
	
	Reception updateReception(Reception reception);
	
	void deleteReception(Integer id);

}
