package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ContratPlafond;
import com.amenal.amenalbackend.achat.application.port.out.ContratPlafondDao;

public class ContratPlafondUseCase {
	
	private ContratPlafondDao contratPlafondDao;
	
	public ContratPlafondUseCase(ContratPlafondDao contratPlafondDao) {
		this.contratPlafondDao = contratPlafondDao;
	}

	public ContratPlafond findContratPlafondById(Integer id) {
	    return contratPlafondDao.findContratPlafondById(id);
	}

	public List<ContratPlafond> findAllContratPlafonds() {
		return contratPlafondDao.findAllContratPlafonds();
	}
	
	public ContratPlafond saveContratPlafond(ContratPlafond contratPlafond) {
		return contratPlafondDao.saveContratPlafond(contratPlafond);
	}
	
	public ContratPlafond updateContratPlafond(ContratPlafond contratPlafond) {
		return contratPlafondDao.updateContratPlafond(contratPlafond);
	}
	
	public void deleteContratPlafond(Integer id) {
		contratPlafondDao.deleteContratPlafond(id);
	}	

}
