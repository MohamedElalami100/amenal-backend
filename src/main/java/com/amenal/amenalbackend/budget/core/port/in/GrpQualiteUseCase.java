package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.core.port.out.GrpQualiteDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public class GrpQualiteUseCase {
	
	private GrpQualiteDao grpQualiteDao;
	
	public GrpQualiteUseCase(GrpQualiteDao grpQualiteDao) {
		this.grpQualiteDao = grpQualiteDao;
	}

	public GrpQualite findGrpQualiteById(Integer id) {
	    return grpQualiteDao.findGrpQualiteById(id);
	}

	public List<GrpQualite> findAllGrpQualites() {
		return grpQualiteDao.findAllGrpQualites();
	}
	
	public GrpQualite saveGrpQualite(GrpQualite grpQualite) throws DuplicateElementException{
		return grpQualiteDao.saveGrpQualite(grpQualite);
	}
	
	public GrpQualite updateGrpQualite(GrpQualite grpQualite) throws DuplicateElementException {
		return grpQualiteDao.updateGrpQualite(grpQualite);
	}
	
	public void deleteGrpQualite(Integer id) {
		grpQualiteDao.deleteGrpQualite(id);
	}
	

}
