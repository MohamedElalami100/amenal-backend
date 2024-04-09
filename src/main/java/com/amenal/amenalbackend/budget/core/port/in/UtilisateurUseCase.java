package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Utilisateur;
import com.amenal.amenalbackend.budget.application.port.out.UtilisateurDao;

public class UtilisateurUseCase {
	
	private UtilisateurDao utilisateurDao;
	
	public UtilisateurUseCase(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public Utilisateur findUtilisateurById(Integer id) {
	    return utilisateurDao.findUtilisateurById(id);
	}

	public List<Utilisateur> findAllUtilisateurs() {
		return utilisateurDao.findAllUtilisateurs();
	}
	
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.saveUtilisateur(utilisateur);
	}
	
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.updateUtilisateur(utilisateur);
	}
	
	public void deleteUtilisateur(Integer id) {
		utilisateurDao.deleteUtilisateur(id);
	}
	

}
