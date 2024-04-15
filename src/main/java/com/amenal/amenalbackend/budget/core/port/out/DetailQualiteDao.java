package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface DetailQualiteDao {
	DetailQualite findDetailQualiteById(Integer id);
	
	List<DetailQualite> findAllDetailQualites();
	
	DetailQualite saveDetailQualite(DetailQualite detailQualite) throws DuplicateElementException;
	
	DetailQualite updateDetailQualite(DetailQualite detailQualite) throws DuplicateElementException;
	
	void deleteDetailQualite(Integer id);

    List<DetailQualite> getDetailQualitesByAvenantId(Integer id);
}
