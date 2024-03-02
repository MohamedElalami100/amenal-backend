package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailQualite;
import com.amenal.amenalbackend.application.project.port.out.DetailQualiteDao;

public class DetailQualiteUseCase {
	
	private DetailQualiteDao detailQualiteDao;
	
	public DetailQualiteUseCase(DetailQualiteDao detailQualiteDao) {
		this.detailQualiteDao = detailQualiteDao;
	}

	public DetailQualite findDetailQualiteById(Integer id) {
	    return detailQualiteDao.findDetailQualiteById(id);
	}

	public List<DetailQualite> findAllDetailQualites() {
		return detailQualiteDao.findAllDetailQualites();
	}
	
	public DetailQualite saveDetailQualite(DetailQualite detailQualite) {
		return detailQualiteDao.saveDetailQualite(detailQualite);
	}
	
	public DetailQualite updateDetailQualite(DetailQualite detailQualite) {
		return detailQualiteDao.updateDetailQualite(detailQualite);
	}
	
	public void deleteDetailQualite(Integer id) {
		detailQualiteDao.deleteDetailQualite(id);
	}
	

}
