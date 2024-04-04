package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.GrpQualite;

public interface GrpQualiteDao {
	GrpQualite findGrpQualiteById(Integer id);
	
	List<GrpQualite> findAllGrpQualites();
	
	GrpQualite saveGrpQualite(GrpQualite grpQualite);
	
	GrpQualite updateGrpQualite(GrpQualite grpQualite);
	
	void deleteGrpQualite(Integer id);
	
}
