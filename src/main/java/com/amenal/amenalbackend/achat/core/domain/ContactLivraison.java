package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;

public class ContactLivraison {
	private Integer id;
	private String nom;
	private String lieuLivraison;
	private LocalDate dateLivraison;
	private Commande commande;

	public ContactLivraison(Integer id, String nom, String lieuLivraison, LocalDate dateLivraison,
			Commande commande) {
		super();
		this.id = id;
		this.nom = nom;
		this.lieuLivraison = lieuLivraison;
		this.dateLivraison = dateLivraison;
		this.commande = commande;
	}

	public ContactLivraison() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLieuLivraison() {
		return lieuLivraison;
	}

	public void setLieuLivraison(String lieuLivraiison) {
		this.lieuLivraison = lieuLivraiison;
	}

	public LocalDate getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(LocalDate dateLivaraison) {
		this.dateLivraison = dateLivaraison;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

}
