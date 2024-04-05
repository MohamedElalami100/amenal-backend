package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailFacture;
import com.amenal.amenalbackend.achat.application.dto.DetailFactureDto;
import com.amenal.amenalbackend.achat.application.port.out.DetailFactureDao;

public class DetailFactureUseCase {
	
	private DetailFactureDao detailFactureDao;
	
	public DetailFactureUseCase(DetailFactureDao detailFactureDao) {
		this.detailFactureDao = detailFactureDao;
	}

	public DetailFactureDto findDetailFactureById(Integer id) {
	    return detailFactureDao.findDetailFactureById(id);
	}

	public List<DetailFactureDto> findAllDetailFactures() {
		return detailFactureDao.findAllDetailFactures();
	}
	
	public DetailFacture saveDetailFacture(DetailFacture detailFacture) {
		return detailFactureDao.saveDetailFacture(detailFacture);
	}
	
	public DetailFacture updateDetailFacture(DetailFacture detailFacture) {
		return detailFactureDao.updateDetailFacture(detailFacture);
	}
	
	public void deleteDetailFacture(Integer id) {
		detailFactureDao.deleteDetailFacture(id);
	}	

}
