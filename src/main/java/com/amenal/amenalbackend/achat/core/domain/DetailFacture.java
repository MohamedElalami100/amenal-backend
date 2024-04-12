package com.amenal.amenalbackend.achat.core.domain;

public class DetailFacture {
	private Integer id;
	private DetailReception detailReception;
	private Double qteAFacture;
	private Double qteRectifie;
	private Double prixUnitaireHtRectifie;
	public DetailFacture(Integer id, DetailReception detailReception, Double qteAFacture, Double qteRectifie,
			Double prixUnitaireHtRectifie, Facture facture) {
		super();
		this.id = id;
		this.detailReception = detailReception;
		this.qteAFacture = qteAFacture;
		this.qteRectifie = qteRectifie;
		this.prixUnitaireHtRectifie = prixUnitaireHtRectifie;
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
	public Double getMntHt() {
		try {
			return 0.0;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return 0.0;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return 0.0;
		} catch (Exception e) {
			return null;
		}
	}
}
