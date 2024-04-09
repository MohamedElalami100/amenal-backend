package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.MetreAv;
import com.amenal.amenalbackend.budget.core.port.out.MetreAvDao;

public class MetreAvUseCase {
	
	private MetreAvDao metreAvDao;
	
	public MetreAvUseCase(MetreAvDao metreAvDao) {
		this.metreAvDao = metreAvDao;
	}

	public MetreAv findMetreAvById(Integer id) {
	    return metreAvDao.findMetreAvById(id);
	}

	public List<MetreAv> findAllMetreAvs() {
		return metreAvDao.findAllMetreAvs();
	}
	
	public List<MetreAv> getMetresByAvenantId(Integer id) {
		return metreAvDao.getMetresByAvenantId(id);
	}
	
	public MetreAv saveMetreAv(MetreAv metreAv) {
		return metreAvDao.saveMetreAv(metreAv);
	}
	
	public MetreAv updateMetreAv(MetreAv metreAv) {
		return metreAvDao.updateMetreAv(metreAv);
	}
	
	public void deleteMetreAv(Integer id) {
		metreAvDao.deleteMetreAv(id);
	}
	

}
