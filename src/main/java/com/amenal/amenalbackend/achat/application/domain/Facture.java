package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;
import java.util.List;

public class Facture {
	private Integer id;
	private LocalDate dateFcf;
	private String reference;
	private String lienPhotoFacture;
	private Integer delaiFacture;
	private Double mntHtNote;
	private Double mntTvaNote;
	private Double mntTtcNote;

	private Fournisseur fournisseur;
	private List<Paiement> paiements;
	private List<Reception> receptions;

	public Facture(Integer id, LocalDate dateFcf, String reference, String lienPhotoFacture, Integer delaiFacture,
			Double mntHtNote, Double mntTvaNote, Double mntTtcNote, Fournisseur fournisseur, List<Paiement> paiements,
			List<Reception> receptions) {
		super();
		this.id = id;
		this.dateFcf = dateFcf;
		this.reference = reference;
		this.lienPhotoFacture = lienPhotoFacture;
		this.delaiFacture = delaiFacture;
		this.mntHtNote = mntHtNote;
		this.mntTvaNote = mntTvaNote;
		this.mntTtcNote = mntTtcNote;
		this.fournisseur = fournisseur;
		this.paiements = paiements;
		this.receptions = receptions;
	}

	public Facture() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateFcf() {
		return dateFcf;
	}

	public void setDateFcf(LocalDate dateFcf) {
		this.dateFcf = dateFcf;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLienPhotoFacture() {
		return lienPhotoFacture;
	}

	public void setLienPhotoFacture(String lienPhotoFacture) {
		this.lienPhotoFacture = lienPhotoFacture;
	}

	public Integer getDelaiFacture() {
		return delaiFacture;
	}

	public void setDelaiFacture(Integer delaiFacture) {
		this.delaiFacture = delaiFacture;
	}

	public Double getMntHtNote() {
		return mntHtNote;
	}

	public void setMntHtNote(Double mntHtNote) {
		this.mntHtNote = mntHtNote;
	}

	public Double getMntTvaNote() {
		return mntTvaNote;
	}

	public void setMntTvaNote(Double mntTvaNote) {
		this.mntTvaNote = mntTvaNote;
	}

	public Double getMntTtcNote() {
		return mntTtcNote;
	}

	public void setMntTtcNote(Double mntTtcNote) {
		this.mntTtcNote = mntTtcNote;
	}

	public List<Reception> getReceptions() {
		return receptions;
	}

	public void setReceptions(List<Reception> receptions) {
		this.receptions = receptions;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

}
