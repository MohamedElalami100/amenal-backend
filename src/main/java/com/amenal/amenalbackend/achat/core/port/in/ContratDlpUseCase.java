package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ContratDlp;
import com.amenal.amenalbackend.achat.application.port.out.ContratDlpDao;

public class ContratDlpUseCase {
	
	private ContratDlpDao contratDlpDao;
	
	public ContratDlpUseCase(ContratDlpDao contratDlpDao) {
		this.contratDlpDao = contratDlpDao;
	}

	public ContratDlp findContratDlpById(Integer id) {
	    return contratDlpDao.findContratDlpById(id);
	}

	public List<ContratDlp> findAllContratDlps() {
		return contratDlpDao.findAllContratDlps();
	}
	
	public ContratDlp saveContratDlp(ContratDlp contratDlp) {
		return contratDlpDao.saveContratDlp(contratDlp);
	}
	
	public ContratDlp updateContratDlp(ContratDlp contratDlp) {
		return contratDlpDao.updateContratDlp(contratDlp);
	}
	
	public void deleteContratDlp(Integer id) {
		contratDlpDao.deleteContratDlp(id);
	}	

}
