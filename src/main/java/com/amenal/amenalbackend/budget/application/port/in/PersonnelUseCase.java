package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Personnel;
import com.amenal.amenalbackend.budget.application.port.out.PersonnelDao;

public class PersonnelUseCase {
	
	private PersonnelDao personnelDao;
	
	public PersonnelUseCase(PersonnelDao personnelDao) {
		this.personnelDao = personnelDao;
	}

	public Personnel findPersonnelById(Integer id) {
	    return personnelDao.findPersonnelById(id);
	}

	public List<Personnel> findAllPersonnels() {
		return personnelDao.findAllPersonnels();
	}
	
	public Personnel savePersonnel(Personnel personnel) {
		return personnelDao.savePersonnel(personnel);
	}
	
	public Personnel updatePersonnel(Personnel personnel) {
		return personnelDao.updatePersonnel(personnel);
	}
	
	public void deletePersonnel(Integer id) {
		personnelDao.deletePersonnel(id);
	}
	

}
