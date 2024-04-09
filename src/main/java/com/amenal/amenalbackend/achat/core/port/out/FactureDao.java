package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Facture;
import com.amenal.amenalbackend.achat.application.dto.FactureDto;

public interface FactureDao {
	FactureDto findFactureById(Integer id);
	
	List<FactureDto> findAllFactures();
	
	Facture saveFacture(Facture facture);
	
	Facture updateFacture(Facture facture);
	
	void deleteFacture(Integer id);

}
