package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Devis;
import com.amenal.amenalbackend.achat.application.dto.DevisDto;

public interface DevisDao {
	DevisDto findDevisById(Integer id);
	
	List<DevisDto> findAllDeviss();
	
	Devis saveDevis(Devis devis);
	
	Devis updateDevis(Devis devis);
	
	void deleteDevis(Integer id);

}
