package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface GrpQualiteDao {
	GrpQualite findGrpQualiteById(Integer id);
	
	List<GrpQualite> findAllGrpQualites();
	
	GrpQualite saveGrpQualite(GrpQualite grpQualite) throws DuplicateElementException;

	GrpQualite saveGrpQualiteAndReturnItIfExists(GrpQualite grpQualite);
	
	GrpQualite updateGrpQualite(GrpQualite grpQualite) throws DuplicateElementException;
	
	void deleteGrpQualite(Integer id);
	
}
