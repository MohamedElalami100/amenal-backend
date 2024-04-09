package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Devis;
import com.amenal.amenalbackend.achat.core.port.out.DevisDao;
import com.amenal.amenalbackend.achat.infrastructure.dto.DevisDto;

public class DevisUseCase {
	
	private DevisDao devisDao;
	
	public DevisUseCase(DevisDao devisDao) {
		this.devisDao = devisDao;
	}

	public DevisDto findDevisById(Integer id) {
	    return devisDao.findDevisById(id);
	}

	public List<DevisDto> findAllDeviss() {
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
