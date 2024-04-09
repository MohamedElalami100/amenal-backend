package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Fournisseur;

public interface FournisseurDao {
	Fournisseur findFournisseurById(Integer id);
	
	List<Fournisseur> findAllFournisseurs();
	
	Fournisseur saveFournisseur(Fournisseur fournisseur);
	
	Fournisseur updateFournisseur(Fournisseur fournisseur);
	
	void deleteFournisseur(Integer id);

}
