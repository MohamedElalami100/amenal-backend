package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailQualite;

public interface DetailQualiteDao {
	DetailQualite findDetailQualiteById(Integer id);
	
	List<DetailQualite> findAllDetailQualites();
	
	DetailQualite saveDetailQualite(DetailQualite detailQualite);
	
	DetailQualite updateDetailQualite(DetailQualite detailQualite);
	
	void deleteDetailQualite(Integer id);
	
}
