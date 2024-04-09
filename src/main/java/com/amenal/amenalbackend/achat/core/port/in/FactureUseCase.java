package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Facture;
import com.amenal.amenalbackend.achat.core.port.out.FactureDao;
import com.amenal.amenalbackend.achat.infrastructure.dto.FactureDto;

public class FactureUseCase {
	
	private FactureDao factureDao;
	
	public FactureUseCase(FactureDao factureDao) {
		this.factureDao = factureDao;
	}

	public FactureDto findFactureById(Integer id) {
	    return factureDao.findFactureById(id);
	}

	public List<FactureDto> findAllFactures() {
		return factureDao.findAllFactures();
	}
	
	public Facture saveFacture(Facture facture) {
		return factureDao.saveFacture(facture);
	}
	
	public Facture updateFacture(Facture facture) {
		return factureDao.updateFacture(facture);
	}
	
	public void deleteFacture(Integer id) {
		factureDao.deleteFacture(id);
	}	

}
