package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Facture;
import com.amenal.amenalbackend.achat.infrastructure.dto.FactureDto;

public interface FactureDao {
	FactureDto findFactureById(Integer id);
	
	List<FactureDto> findAllFactures();
	
	Facture saveFacture(Facture facture);
	
	Facture updateFacture(Facture facture);
	
	void deleteFacture(Integer id);

}
