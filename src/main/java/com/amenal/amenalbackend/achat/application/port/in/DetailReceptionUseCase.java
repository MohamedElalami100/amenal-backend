package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailReception;
import com.amenal.amenalbackend.achat.application.dto.DetailReceptionDto;
import com.amenal.amenalbackend.achat.application.port.out.DetailReceptionDao;

public class DetailReceptionUseCase {
	
	private DetailReceptionDao detailReceptionDao;
	
	public DetailReceptionUseCase(DetailReceptionDao detailReceptionDao) {
		this.detailReceptionDao = detailReceptionDao;
	}

	public DetailReceptionDto findDetailReceptionById(Integer id) {
	    return detailReceptionDao.findDetailReceptionById(id);
	}

	public List<DetailReceptionDto> findAllDetailReceptions() {
		return detailReceptionDao.findAllDetailReceptions();
	}
	
	public DetailReception saveDetailReception(DetailReception detailReception) {
		return detailReceptionDao.saveDetailReception(detailReception);
	}
	
	public DetailReception updateDetailReception(DetailReception detailReception) {
		return detailReceptionDao.updateDetailReception(detailReception);
	}
	
	public void deleteDetailReception(Integer id) {
		detailReceptionDao.deleteDetailReception(id);
	}	

}
