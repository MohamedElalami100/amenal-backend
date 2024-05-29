package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Commande;

public interface CommandeDao {
	Commande findCommandeById(Integer id);
	
	List<Commande> findAllCommandes();
	
	Commande saveCommande(Commande commande);
	
	Commande updateCommande(Commande commande);
	
	void deleteCommande(Integer id);

    List<Commande> getCommandesByProjectId(Integer id);
}
