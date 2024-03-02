package com.amenal.amenalbackend.application.project.domain;

import java.time.LocalDate;

public class Personnel {
	private Integer matricule;
	private String nom;
	private String prenom;
	private LocalDate dateEmbauche;
	private String gsm;
	private Boolean dimissionne;
	private String motifDeSortie;
	private String adresse;
	private String doc;
	private Integer chargeCompte;
	private String reference;
	private Integer idsup;

	public Personnel(Integer matricule, String nom, String prenom, LocalDate dateEmbauche, String gsm,
			Boolean dimisionne, String motifDeSortie, String adresse, String doc, Integer chargeCompte,
			String reference, Integer idsup) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.gsm = gsm;
		this.dimissionne = dimisionne;
		this.motifDeSortie = motifDeSortie;
		this.adresse = adresse;
		this.doc = doc;
		this.chargeCompte = chargeCompte;
		this.reference = reference;
		this.idsup = idsup;
	}

	public Personnel() {
		super();
	}

	public Integer getMatricule() {
		return matricule;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public Boolean getDimisionne() {
		return dimissionne;
	}

	public void setDimisionne(Boolean dimisionne) {
		this.dimissionne = dimisionne;
	}

	public String getMotifDeSortie() {
		return motifDeSortie;
	}

	public void setMotifDeSortie(String motifDeSortie) {
		this.motifDeSortie = motifDeSortie;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Integer getChargeCompte() {
		return chargeCompte;
	}

	public void setChargeCompte(Integer chargeCompte) {
		this.chargeCompte = chargeCompte;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getIdsup() {
		return idsup;
	}

	public void setIdsup(Integer idsup) {
		this.idsup = idsup;
	}

	// business methods
	public String getNomComplet() {
		try {
			return this.getNom() + " " + this.getPrenom();
		} catch (Exception e) {
			return null;
		}
	}

}
