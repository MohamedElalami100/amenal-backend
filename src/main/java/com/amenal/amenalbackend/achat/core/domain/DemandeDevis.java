package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;
import java.util.List;

public class DemandeDevis {
	private Integer id;
	private LocalDate dateDdf;

	private Fournisseur fournisseur;

	private List<DetailDemandeDevis> detailDemandeDevis;

	public DemandeDevis(Integer id, LocalDate dateDdf, Fournisseur fournisseur,
			List<DetailDemandeDevis> detailDemandeDevis) {
		super();
		this.id = id;
		this.dateDdf = dateDdf;
		this.fournisseur = fournisseur;
		this.detailDemandeDevis = detailDemandeDevis;
	}

	public DemandeDevis() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDdf() {
		return dateDdf;
	}

	public void setDateDdf(LocalDate dateDdf) {
		this.dateDdf = dateDdf;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<DetailDemandeDevis> getDetailDemandeDevis() {
		return detailDemandeDevis;
	}

	public void setDetailDemandeDevis(List<DetailDemandeDevis> detailDemandeDevis) {
		this.detailDemandeDevis = detailDemandeDevis;
	}

}
