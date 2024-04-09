package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Personnel;

public interface PersonnelDao {
	Personnel findPersonnelById(Integer id);
	
	List<Personnel> findAllPersonnels();
	
	Personnel savePersonnel(Personnel personnel);
	
	Personnel updatePersonnel(Personnel personnel);
	
	void deletePersonnel(Integer id);
	
}
