package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.MetreAv;

public interface MetreAvDao {
	MetreAv findMetreAvById(Integer id);
	
	List<MetreAv> findAllMetreAvs();
	
	MetreAv saveMetreAv(MetreAv metreAv);
	
	MetreAv updateMetreAv(MetreAv metreAv);
	
	void deleteMetreAv(Integer id);

	MetreAv getOneMetreByAvenantId(Integer id);

	List<MetreAv> getMetresByAvenantId(Integer id);
	
}
