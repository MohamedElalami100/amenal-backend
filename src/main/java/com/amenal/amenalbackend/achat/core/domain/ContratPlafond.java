package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;

public class ContratPlafond {
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Integer delaiReglement;
	private String lienContrat;

	private Fournisseur fournisseur;

	public ContratPlafond(Integer id, LocalDate dateDebut, LocalDate dateFin, Integer delaiReglement,
			String lienContrat, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.delaiReglement = delaiReglement;
		this.lienContrat = lienContrat;
		this.fournisseur = fournisseur;
	}

	public ContratPlafond() {
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

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
