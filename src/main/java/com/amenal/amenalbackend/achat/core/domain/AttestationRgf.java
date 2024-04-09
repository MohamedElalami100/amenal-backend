package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;

public class AttestationRgf {
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String lienAttestation;
	private Fournisseur fournisseur;

	public AttestationRgf(Integer id, LocalDate dateDebut, LocalDate dateFin, String lienAttestation,
			Fournisseur fournisseur) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.lienAttestation = lienAttestation;
		this.fournisseur = fournisseur;
	}

	public AttestationRgf() {
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

	public String getLienAttestation() {
		return lienAttestation;
	}

	public void setLienAttestation(String lienAttestation) {
		this.lienAttestation = lienAttestation;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
