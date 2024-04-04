package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Devis;

public interface DevisDao {
	Devis findDevisById(Integer id);
	
	List<Devis> findAllDeviss();
	
	Devis saveDevis(Devis devis);
	
	Devis updateDevis(Devis devis);
	
	void deleteDevis(Integer id);

}
