package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Paiement;
import com.amenal.amenalbackend.achat.application.port.out.PaiementDao;

public class PaiementUseCase {
	
	private PaiementDao paiementDao;
	
	public PaiementUseCase(PaiementDao paiementDao) {
		this.paiementDao = paiementDao;
	}

	public Paiement findPaiementById(Integer id) {
	    return paiementDao.findPaiementById(id);
	}

	public List<Paiement> findAllPaiements() {
		return paiementDao.findAllPaiements();
	}
	
	public Paiement savePaiement(Paiement paiement) {
		return paiementDao.savePaiement(paiement);
	}
	
	public Paiement updatePaiement(Paiement paiement) {
		return paiementDao.updatePaiement(paiement);
	}
	
	public void deletePaiement(Integer id) {
		paiementDao.deletePaiement(id);
	}	

}
