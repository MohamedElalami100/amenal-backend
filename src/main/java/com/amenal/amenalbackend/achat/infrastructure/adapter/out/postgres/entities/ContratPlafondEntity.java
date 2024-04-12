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
@Table(name = "contrat_plafond")
public class ContratPlafondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat_plafond")
    private Integer id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "delai_reglement")
    private Integer delaiReglement;

    @Column(name = "lien_contrat")
    private String lienContrat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fournisseur")
	@JsonIgnore
	private FournisseurEntity fournisseur;

	public ContratPlafondEntity(Integer id, LocalDate dateDebut, LocalDate dateFin, Integer delaiReglement,
			String lienContrat, FournisseurEntity fournisseur) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.delaiReglement = delaiReglement;
		this.lienContrat = lienContrat;
		this.fournisseur = fournisseur;
	}

	public ContratPlafondEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getDelaiReglement() {
		return delaiReglement;
	}

	public void setDelaiReglement(Integer delaiReglement) {
		this.delaiReglement = delaiReglement;
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
