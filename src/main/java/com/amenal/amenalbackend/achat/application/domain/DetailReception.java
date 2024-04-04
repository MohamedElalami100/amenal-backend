package com.amenal.amenalbackend.achat.application.domain;

public class DetailReception {
	private Integer id;
	private DetailDevis detailCommande;
	private Double qte;
	private String lienPhotoArticle;

	private Reception reception;

	public DetailReception() {
		super();
	}

	public DetailReception(Integer id, DetailDevis detailCommande, Double qte, String lienPhotoArticle,
			Reception reception) {
		super();
		this.id = id;
		this.detailCommande = detailCommande;
		this.qte = qte;
		this.lienPhotoArticle = lienPhotoArticle;
		this.reception = reception;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailDevis getDetailCommande() {
		return detailCommande;
	}

	public void setDetailCommande(DetailDevis detailCommande) {
		this.detailCommande = detailCommande;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public String getLienPhotoArticle() {
		return lienPhotoArticle;
	}

	public void setLienPhotoArticle(String lienPhotoArticle) {
		this.lienPhotoArticle = lienPhotoArticle;
	}

	public Reception getReception() {
		return reception;
	}

	public void setReception(Reception reception) {
		this.reception = reception;
	}

	// business methods:
	public Double getMntHt() {
		try {
			return detailCommande.getPrixUnitaire() * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return getMntHt() * 0.2;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return getMntHt() * 1.2;
		} catch (Exception e) {
			return null;
		}
	}
}
