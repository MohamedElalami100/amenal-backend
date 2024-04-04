package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Facture;

public interface FactureDao {
	Facture findFactureById(Integer id);
	
	List<Facture> findAllFactures();
	
	Facture saveFacture(Facture facture);
	
	Facture updateFacture(Facture facture);
	
	void deleteFacture(Integer id);

}
