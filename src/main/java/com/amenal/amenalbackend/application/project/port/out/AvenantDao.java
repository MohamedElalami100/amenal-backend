package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Avenant;

public interface AvenantDao {
	Avenant findAvenantById(Integer id);
	
	List<Avenant> findAllAvenants();
	
	Avenant saveAvenant(Avenant avenant);
	
	Avenant updateAvenant(Avenant avenant);
	
	void deleteAvenant(Integer id);

	List<Avenant> getAvenantsByProjectId(Integer id);
	
}
