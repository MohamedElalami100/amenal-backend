package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Utilisateur;

public interface UtilisateurDao {
	Utilisateur findUtilisateurById(Integer id);
	
	List<Utilisateur> findAllUtilisateurs();
	
	Utilisateur saveUtilisateur(Utilisateur utilisateur);
	
	Utilisateur updateUtilisateur(Utilisateur utilisateur);
	
	void deleteUtilisateur(Integer id);
	
}
