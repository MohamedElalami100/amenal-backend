package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "contact_livraison")
public class ContactLivraisonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact_livraison")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "lieu_livraison")
    private String lieuLivraison;

    @Column(name = "date_livraison")
    private LocalDate dateLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commande")
	@JsonIgnore
    private CommandeEntity commande;

	public ContactLivraisonEntity(Integer id, String nom, String lieuLivraison, LocalDate dateLivraison,
			CommandeEntity commande) {
		super();
		this.id = id;
		this.nom = nom;
		this.lieuLivraison = lieuLivraison;
		this.dateLivraison = dateLivraison;
		this.commande = commande;
	}

	public ContactLivraisonEntity() {
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

	public void setLieuLivraison(String lieuLivraison) {
		this.lieuLivraison = lieuLivraison;
	}

	public LocalDate getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(LocalDate dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public CommandeEntity getCommande() {
		return commande;
	}

	public void setCommande(CommandeEntity commande) {
		this.commande = commande;
	}

}
