package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Reception;
import com.amenal.amenalbackend.achat.application.dto.ReceptionDto;
import com.amenal.amenalbackend.achat.application.port.out.ReceptionDao;

public class ReceptionUseCase {
	
	private ReceptionDao receptionDao;
	
	public ReceptionUseCase(ReceptionDao receptionDao) {
		this.receptionDao = receptionDao;
	}

	public ReceptionDto findReceptionById(Integer id) {
	    return receptionDao.findReceptionById(id);
	}

	public List<ReceptionDto> findAllReceptions() {
		return receptionDao.findAllReceptions();
	}
	
	public Reception saveReception(Reception reception) {
		return receptionDao.saveReception(reception);
	}
	
	public Reception updateReception(Reception reception) {
		return receptionDao.updateReception(reception);
	}
	
	public void deleteReception(Integer id) {
		receptionDao.deleteReception(id);
	}	

}
