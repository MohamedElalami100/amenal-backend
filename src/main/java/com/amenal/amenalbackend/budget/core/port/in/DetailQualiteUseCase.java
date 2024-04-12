package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

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
	
	public DetailQualite saveDetailQualite(DetailQualite detailQualite) throws DuplicateElementException  {
		return detailQualiteDao.saveDetailQualite(detailQualite);
	}
	
	public DetailQualite updateDetailQualite(DetailQualite detailQualite) throws DuplicateElementException {
		return detailQualiteDao.updateDetailQualite(detailQualite);
	}
	
	public void deleteDetailQualite(Integer id) {
		detailQualiteDao.deleteDetailQualite(id);
	}
	

}
