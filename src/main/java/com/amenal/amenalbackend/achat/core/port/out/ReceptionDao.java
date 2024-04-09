package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Reception;
import com.amenal.amenalbackend.achat.infrastructure.dto.ReceptionDto;

public interface ReceptionDao {
	ReceptionDto findReceptionById(Integer id);
	
	List<ReceptionDto> findAllReceptions();
	
	Reception saveReception(Reception reception);
	
	Reception updateReception(Reception reception);
	
	void deleteReception(Integer id);

}
