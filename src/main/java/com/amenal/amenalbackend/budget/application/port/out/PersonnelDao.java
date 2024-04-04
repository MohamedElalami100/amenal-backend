package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Personnel;

public interface PersonnelDao {
	Personnel findPersonnelById(Integer id);
	
	List<Personnel> findAllPersonnels();
	
	Personnel savePersonnel(Personnel personnel);
	
	Personnel updatePersonnel(Personnel personnel);
	
	void deletePersonnel(Integer id);
	
}
