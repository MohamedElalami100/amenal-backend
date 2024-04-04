package com.amenal.amenalbackend.achat.application.domain;

public class DetailFacture {
	private Integer id;
	private DetailReception detailReception;
	private Double qteAFacture;
	private Double qteRectifie;
	private Double prixUnitaireHtRectifie;
	private Facture facture;

	public DetailFacture(Integer id, DetailReception detailReception, Double qteAFacture, Double qteRectifie,
			Double prixUnitaireHtRectifie, Facture facture) {
		super();
		this.id = id;
		this.detailReception = detailReception;
		this.qteAFacture = qteAFacture;
		this.qteRectifie = qteRectifie;
		this.prixUnitaireHtRectifie = prixUnitaireHtRectifie;
		this.facture = facture;
	}

	public DetailFacture() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public DetailReception getDetailReception() {
		return detailReception;
	}

	public void setDetailReception(DetailReception detailReception) {
		this.detailReception = detailReception;
	}

	public Double getQteAFacture() {
		return qteAFacture;
	}

	public void setQteAFacture(Double qteAFacture) {
		this.qteAFacture = qteAFacture;
	}

	public Double getQteRectifie() {
		return qteRectifie;
	}

	public void setQteRectifie(Double qteRectifie) {
		this.qteRectifie = qteRectifie;
	}

	public Double getPrixUnitaireHtRectifie() {
		return prixUnitaireHtRectifie;
	}

	public void setPrixUnitaireHtRectifie(Double prixUnitaireHtRectifie) {
		this.prixUnitaireHtRectifie = prixUnitaireHtRectifie;
	}

	// ToDO: business methods for mntHt, mntTva, mntTtc

}
