package com.amenal.amenalbackend.budget.core.domain;

public class DetailQualiteAttente {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private String groupe;
	private String pointDeControle;
	private String erreur;
	private MetreAv metre;

	public DetailQualiteAttente(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String groupe, String pointDeControle, String erreur, MetreAv metre) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.groupe = groupe;
		this.pointDeControle = pointDeControle;
		this.erreur = erreur;
		this.metre = metre;
	}

	public DetailQualiteAttente() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Boolean getCle() {
		return cle;
	}

	public void setCle(Boolean cle) {
		this.cle = cle;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getPointDeControle() {
		return pointDeControle;
	}

	public void setPointDeControle(String pointDeControle) {
		this.pointDeControle = pointDeControle;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public MetreAv getMetre() {
		return metre;
	}

	public void setAvenant(MetreAv metre) {
		this.metre = metre;
	}

}
