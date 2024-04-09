package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.CompteBanquaire;

public interface CompteBanquaireDao {
	CompteBanquaire findCompteBanquaireById(Integer id);
	
	List<CompteBanquaire> findAllCompteBanquaires();
	
	CompteBanquaire saveCompteBanquaire(CompteBanquaire compteBanquaire);
	
	CompteBanquaire updateCompteBanquaire(CompteBanquaire compteBanquaire);
	
	void deleteCompteBanquaire(Integer id);

}
