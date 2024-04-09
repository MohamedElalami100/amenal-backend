package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Remise;
import com.amenal.amenalbackend.achat.application.port.out.RemiseDao;

public class RemiseUseCase {
	
	private RemiseDao remiseDao;
	
	public RemiseUseCase(RemiseDao remiseDao) {
		this.remiseDao = remiseDao;
	}

	public Remise findRemiseById(Integer id) {
	    return remiseDao.findRemiseById(id);
	}

	public List<Remise> findAllRemises() {
		return remiseDao.findAllRemises();
	}
	
	public Remise saveRemise(Remise remise) {
		return remiseDao.saveRemise(remise);
	}
	
	public Remise updateRemise(Remise remise) {
		return remiseDao.updateRemise(remise);
	}
	
	public void deleteRemise(Integer id) {
		remiseDao.deleteRemise(id);
	}	

}
