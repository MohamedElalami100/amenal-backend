package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Paiement;

public interface PaiementDao {
	Paiement findPaiementById(Integer id);
	
	List<Paiement> findAllPaiements();
	
	Paiement savePaiement(Paiement paiement);
	
	Paiement updatePaiement(Paiement paiement);
	
	void deletePaiement(Integer id);

}
