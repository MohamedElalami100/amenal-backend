package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailFacture;
import com.amenal.amenalbackend.achat.core.port.out.DetailFactureDao;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailFactureDto;

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
