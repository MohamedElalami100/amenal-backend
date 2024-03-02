package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.GrpQualite;
import com.amenal.amenalbackend.application.project.port.out.GrpQualiteDao;

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
	
	public GrpQualite saveGrpQualite(GrpQualite grpQualite) {
		return grpQualiteDao.saveGrpQualite(grpQualite);
	}
	
	public GrpQualite updateGrpQualite(GrpQualite grpQualite) {
		return grpQualiteDao.updateGrpQualite(grpQualite);
	}
	
	public void deleteGrpQualite(Integer id) {
		grpQualiteDao.deleteGrpQualite(id);
	}
	

}
