package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.port.out.AvenantDao;

public class AvenantUseCase {
	
	private AvenantDao avenantDao;
	
	public AvenantUseCase(AvenantDao avenantDao) {
		this.avenantDao = avenantDao;
	}

	public Avenant findAvenantById(Integer id) {
	    return avenantDao.findAvenantById(id);
	}

	public List<Avenant> findAllAvenants() {
		return avenantDao.findAllAvenants();
	}
	
	public Avenant saveAvenant(Avenant avenant) {
		return avenantDao.saveAvenant(avenant);
	}
	
	public Avenant updateAvenant(Avenant avenant) {
		return avenantDao.updateAvenant(avenant);
	}
	
	public void deleteAvenant(Integer id) {
		avenantDao.deleteAvenant(id);
	}

	public List<Avenant> getAvenantsByProjectId(Integer id) {
	    return avenantDao.getAvenantsByProjectId(id);
	}
	

}
