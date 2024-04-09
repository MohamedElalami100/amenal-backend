package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DemandeDevis;
import com.amenal.amenalbackend.achat.core.port.out.DemandeDevisDao;

public class DemandeDevisUseCase {
	
	private DemandeDevisDao demandeDevisDao;
	
	public DemandeDevisUseCase(DemandeDevisDao demandeDevisDao) {
		this.demandeDevisDao = demandeDevisDao;
	}

	public DemandeDevis findDemandeDevisById(Integer id) {
	    return demandeDevisDao.findDemandeDevisById(id);
	}

	public List<DemandeDevis> findAllDemandeDeviss() {
		return demandeDevisDao.findAllDemandeDeviss();
	}
	
	public DemandeDevis saveDemandeDevis(DemandeDevis demandeDevis) {
		return demandeDevisDao.saveDemandeDevis(demandeDevis);
	}
	
	public DemandeDevis updateDemandeDevis(DemandeDevis demandeDevis) {
		return demandeDevisDao.updateDemandeDevis(demandeDevis);
	}
	
	public void deleteDemandeDevis(Integer id) {
		demandeDevisDao.deleteDemandeDevis(id);
	}	

}
