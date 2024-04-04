package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Devis;
import com.amenal.amenalbackend.achat.application.port.out.DevisDao;

public class DevisUseCase {
	
	private DevisDao devisDao;
	
	public DevisUseCase(DevisDao devisDao) {
		this.devisDao = devisDao;
	}

	public Devis findDevisById(Integer id) {
	    return devisDao.findDevisById(id);
	}

	public List<Devis> findAllDeviss() {
		return devisDao.findAllDeviss();
	}
	
	public Devis saveDevis(Devis devis) {
		return devisDao.saveDevis(devis);
	}
	
	public Devis updateDevis(Devis devis) {
		return devisDao.updateDevis(devis);
	}
	
	public void deleteDevis(Integer id) {
		devisDao.deleteDevis(id);
	}	

}
