package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Utilisateur;

public interface UtilisateurDao {
	Utilisateur findUtilisateurById(Integer id);
	
	List<Utilisateur> findAllUtilisateurs();
	
	Utilisateur saveUtilisateur(Utilisateur utilisateur);
	
	Utilisateur updateUtilisateur(Utilisateur utilisateur);
	
	void deleteUtilisateur(Integer id);
	
}
