package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailReception;
import com.amenal.amenalbackend.achat.core.port.out.DetailReceptionDao;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailReceptionDto;

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
