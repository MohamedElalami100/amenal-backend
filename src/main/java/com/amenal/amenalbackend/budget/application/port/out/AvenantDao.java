package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Avenant;

public interface AvenantDao {
	Avenant findAvenantById(Integer id);
	
	List<Avenant> findAllAvenants();
	
	Avenant saveAvenant(Avenant avenant);
	
	Avenant updateAvenant(Avenant avenant);
	
	void deleteAvenant(Integer id);

	List<Avenant> getAvenantsByProjectId(Integer id);
	
}
