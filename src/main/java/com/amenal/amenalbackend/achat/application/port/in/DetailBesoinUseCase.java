package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailBesoin;
import com.amenal.amenalbackend.achat.application.port.out.DetailBesoinDao;

public class DetailBesoinUseCase {
	
	private DetailBesoinDao detailBesoinDao;
	
	public DetailBesoinUseCase(DetailBesoinDao detailBesoinDao) {
		this.detailBesoinDao = detailBesoinDao;
	}

	public DetailBesoin findDetailBesoinById(Integer id) {
	    return detailBesoinDao.findDetailBesoinById(id);
	}

	public List<DetailBesoin> findAllDetailBesoins() {
		return detailBesoinDao.findAllDetailBesoins();
	}
	
	public DetailBesoin saveDetailBesoin(DetailBesoin detailBesoin) {
		return detailBesoinDao.saveDetailBesoin(detailBesoin);
	}
	
	public DetailBesoin updateDetailBesoin(DetailBesoin detailBesoin) {
		return detailBesoinDao.updateDetailBesoin(detailBesoin);
	}
	
	public void deleteDetailBesoin(Integer id) {
		detailBesoinDao.deleteDetailBesoin(id);
	}	

}
