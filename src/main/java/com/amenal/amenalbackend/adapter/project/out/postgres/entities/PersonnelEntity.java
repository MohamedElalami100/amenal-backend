package com.amenal.amenalbackend.adapter.project.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "personnel")
public class PersonnelEntity {

    @Id
    @Column(name = "matricule")
    private Integer matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @Column(name = "gsm")
    private String gsm;

    @Column(name = "dimissionne")
    private Boolean dimissionne;

    @Column(name = "motif_de_sortie")
    private String motifDeSortie;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "doc")
    private String doc;

    @Column(name = "charge_compte")
    private Integer chargeCompte;

    @Column(name = "reference")
    private String reference;

    @Column(name = "idsup")
    private Integer idsup;

	public PersonnelEntity(Integer matricule, String nom, String prenom, LocalDate dateEmbauche, String gsm,
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

	public PersonnelEntity() {
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
