package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrat_dlp")
public class ContratDlpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat_dlp")
    private Integer id;

    @Column(name = "plafond")
    private Integer plafond;

    @Column(name = "lien_contrat")
    private String lienContrat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fournisseur")
    private FournisseurEntity fournisseur;

	public ContratDlpEntity(Integer id, Integer plafond, String lienContrat, FournisseurEntity fournisseur) {
		super();
		this.id = id;
		this.plafond = plafond;
		this.lienContrat = lienContrat;
		this.fournisseur = fournisseur;
	}

	public ContratDlpEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlafond() {
		return plafond;
	}

	public void setPlafond(Integer plafond) {
		this.plafond = plafond;
	}

	public String getLienContrat() {
		return lienContrat;
	}

	public void setLienContrat(String lienContrat) {
		this.lienContrat = lienContrat;
	}

	public FournisseurEntity getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurEntity fournisseur) {
		this.fournisseur = fournisseur;
	}

}
