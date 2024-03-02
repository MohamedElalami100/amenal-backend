package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.MetreAv;

public interface MetreAvDao {
	MetreAv findMetreAvById(Integer id);
	
	List<MetreAv> findAllMetreAvs();
	
	MetreAv saveMetreAv(MetreAv metreAv);
	
	MetreAv updateMetreAv(MetreAv metreAv);
	
	void deleteMetreAv(Integer id);

	MetreAv getOneMetreByAvenantId(Integer id);

	List<MetreAv> getMetresByAvenantId(Integer id);
	
}
