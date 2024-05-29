package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Paiement;
import com.amenal.amenalbackend.achat.core.port.out.FactureDao;
import com.amenal.amenalbackend.achat.core.port.out.PaiementDao;

public class PaiementUseCase {
	
	private PaiementDao paiementDao;

	private FactureDao factureDao;

	public PaiementUseCase(PaiementDao paiementDao, FactureDao factureDao) {
		this.paiementDao = paiementDao;
		this.factureDao = factureDao;
	}

	public Paiement findPaiementById(Integer id) {
	    return paiementDao.findPaiementById(id);
	}

	public List<Paiement> findAllPaiements() {
		return paiementDao.findAllPaiements();
	}
	
	public Paiement savePaiement(Paiement paiement) {
		if(paiement.getFactures() != null){
			var facturesPayee = paiement.getFactures();
			for (var facturePayee: facturesPayee) {
				facturePayee.setPayee(true);
				factureDao.updateFacture(facturePayee);
			}
		}
		return paiementDao.savePaiement(paiement);
	}
	
	public Paiement updatePaiement(Paiement paiement) {
		return paiementDao.updatePaiement(paiement);
	}
	
	public void deletePaiement(Integer id) {
		paiementDao.deletePaiement(id);
	}	

}
