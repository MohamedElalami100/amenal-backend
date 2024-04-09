package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Devis;
import com.amenal.amenalbackend.achat.infrastructure.dto.DevisDto;

public interface DevisDao {
	DevisDto findDevisById(Integer id);
	
	List<DevisDto> findAllDeviss();
	
	Devis saveDevis(Devis devis);
	
	Devis updateDevis(Devis devis);
	
	void deleteDevis(Integer id);

}
