package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Commande;
import com.amenal.amenalbackend.achat.application.port.out.CommandeDao;

public class CommandeUseCase {
	
	private CommandeDao commandeDao;
	
	public CommandeUseCase(CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	public Commande findCommandeById(Integer id) {
	    return commandeDao.findCommandeById(id);
	}

	public List<Commande> findAllCommandes() {
		return commandeDao.findAllCommandes();
	}
	
	public Commande saveCommande(Commande commande) {
		return commandeDao.saveCommande(commande);
	}
	
	public Commande updateCommande(Commande commande) {
		return commandeDao.updateCommande(commande);
	}
	
	public void deleteCommande(Integer id) {
		commandeDao.deleteCommande(id);
	}	

}
