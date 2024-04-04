package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;
import java.util.List;

public class Paiement {
	private Integer id;
	private LocalDate datePmf;
	private String refOperation;
	private Double montant;
	private String lienPhotoPlf;

	private CompteBanquaire compteDebite;
	private Fournisseur fournisseur;

	private List<Facture> factures;
	private List<Remise> remises;

	public Paiement() {
		super();
	}

	public Paiement(Integer id, LocalDate datePmf, String refOperation, Double montant, String lienPhotoPlf,
			CompteBanquaire compteDebite, Fournisseur fournisseur, List<Facture> factures, List<Remise> remises) {
		super();
		this.id = id;
		this.datePmf = datePmf;
		this.refOperation = refOperation;
		this.montant = montant;
		this.lienPhotoPlf = lienPhotoPlf;
		this.compteDebite = compteDebite;
		this.fournisseur = fournisseur;
		this.factures = factures;
		this.remises = remises;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatePmf() {
		return datePmf;
	}

	public void setDatePmf(LocalDate datePmf) {
		this.datePmf = datePmf;
	}

	public String getRefOperation() {
		return refOperation;
	}

	public void setRefOperation(String refOperation) {
		this.refOperation = refOperation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getLienPhotoPlf() {
		return lienPhotoPlf;
	}

	public void setLienPhotoPlf(String lienPhotoPlf) {
		this.lienPhotoPlf = lienPhotoPlf;
	}

	public CompteBanquaire getCompteDebite() {
		return compteDebite;
	}

	public void setCompteDebite(CompteBanquaire compteDebite) {
		this.compteDebite = compteDebite;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Facture> getFactures() {
		return factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public List<Remise> getRemises() {
		return remises;
	}

	public void setRemises(List<Remise> remises) {
		this.remises = remises;
	}

}
