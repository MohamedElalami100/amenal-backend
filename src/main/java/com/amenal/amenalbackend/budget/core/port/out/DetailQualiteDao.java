package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;

public interface DetailQualiteDao {
	DetailQualite findDetailQualiteById(Integer id);
	
	List<DetailQualite> findAllDetailQualites();
	
	DetailQualite saveDetailQualite(DetailQualite detailQualite);
	
	DetailQualite updateDetailQualite(DetailQualite detailQualite);
	
	void deleteDetailQualite(Integer id);
	
}
