package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;

public class AttestationRgf {
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String lienAttestation;

	public AttestationRgf(Integer id, LocalDate dateDebut, LocalDate dateFin, String lienAttestation) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.lienAttestation = lienAttestation;
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

}
