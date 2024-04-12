package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;

public class Transport {
	private Integer id;
	private LocalDate dateDepart;
	private LocalDate dateArrive;
	private String prisEnChargePar;
	private String lienPhoto;
	private String type;
	private String matricule;
	private String chauffeur;
	private String chauffeurCin;

	public Transport(Integer id, LocalDate dateDepart, LocalDate dateArrive, String prisEnChargePar, String lienPhoto,
			String type, String matricule, String chauffeur, String chauffeurCin) {
		super();
		this.id = id;
		this.dateDepart = dateDepart;
		this.dateArrive = dateArrive;
		this.prisEnChargePar = prisEnChargePar;
		this.lienPhoto = lienPhoto;
		this.type = type;
		this.matricule = matricule;
		this.chauffeur = chauffeur;
		this.chauffeurCin = chauffeurCin;
	}

	public Transport() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public LocalDate getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(LocalDate dateArrive) {
		this.dateArrive = dateArrive;
	}

	public String getPrisEnChargePar() {
		return prisEnChargePar;
	}

	public void setPrisEnChargePar(String prisEnChargePar) {
		this.prisEnChargePar = prisEnChargePar;
	}

	public String getLienPhoto() {
		return lienPhoto;
	}

	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getChauffeurCin() {
		return chauffeurCin;
	}

	public void setChauffeurCin(String chauffeurCin) {
		this.chauffeurCin = chauffeurCin;
	}

	
}
