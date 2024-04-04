package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.DetailQualite;

public interface DetailQualiteDao {
	DetailQualite findDetailQualiteById(Integer id);
	
	List<DetailQualite> findAllDetailQualites();
	
	DetailQualite saveDetailQualite(DetailQualite detailQualite);
	
	DetailQualite updateDetailQualite(DetailQualite detailQualite);
	
	void deleteDetailQualite(Integer id);
	
}
