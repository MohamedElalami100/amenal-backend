package com.amenal.amenalbackend.budget.core.domain;

import java.time.LocalDate;

public class DetailDelaiAttente {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;
	private String erreur;
	private MetreAv metre;
	
	public DetailDelaiAttente(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			LocalDate ddb, Integer dlb, LocalDate dfb, String erreur, MetreAv metre) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
		this.erreur = erreur;
		this.metre = metre;
	}

	public DetailDelaiAttente() {
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

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public Integer getDlb() {
		return dlb;
	}

	public void setDlb(Integer dlb) {
		this.dlb = dlb;
	}

	public LocalDate getDfb() {
		return dfb;
	}

	public void setDfb(LocalDate dfb) {
		this.dfb = dfb;
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

	public void setMetre(MetreAv metre) {
		this.metre = metre;
	}
	
}
