package com.amenal.amenalbackend.budget.core.domain;

import java.time.LocalDate;

//décompositionBudgetAchatav in Microsoft access database
public class DetailCharge {
	private Integer id;
	private Double prix;
	private Double qte;
	private String unite;
	private String article;
	private String designation;
	private LocalDate dateSaisie;
	private Integer id2;
	private Boolean maj;
	private String ordreMef;
	private String ordrePrt;
	private Integer ordre;
	private Double laQtePrd;
	private String laUnitePrd;
	// Fk objects:
	private NatureArticle nature;
	private Tache tache;

	public DetailCharge(Integer id, Double prix, Double qte, String unite, String article, String designation,
			LocalDate dateSaisie, Integer id2, Boolean maj, String ordreMef, String ordrePrt, Integer ordre,
			Double laQtePrd, String laUnitePrd, NatureArticle nature, Tache tache) {
		super();
		this.id = id;
		this.prix = prix;
		this.qte = qte;
		this.unite = unite;
		this.article = article;
		this.designation = designation;
		this.dateSaisie = dateSaisie;
		this.id2 = id2;
		this.maj = maj;
		this.ordreMef = ordreMef;
		this.ordrePrt = ordrePrt;
		this.ordre = ordre;
		this.laQtePrd = laQtePrd;
		this.laUnitePrd = laUnitePrd;
		this.nature = nature;
		this.tache = tache;
	}

	public DetailCharge() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDate dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public Boolean getMaj() {
		return maj;
	}

	public void setMaj(Boolean maj) {
		this.maj = maj;
	}

	public String getOrdreMef() {
		return ordreMef;
	}

	public void setOrdreMef(String ordreMef) {
		this.ordreMef = ordreMef;
	}

	public String getOrdrePrt() {
		return ordrePrt;
	}

	public void setOrdrePrt(String ordrePrt) {
		this.ordrePrt = ordrePrt;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Double getLaQtePrd() {
		return laQtePrd;
	}

	public void setLaQtePrd(Double laQtePrd) {
		this.laQtePrd = laQtePrd;
	}

	public String getLaUnitePrd() {
		return laUnitePrd;
	}

	public void setLaUnitePrd(String laUnitePrd) {
		this.laUnitePrd = laUnitePrd;
	}

	public NatureArticle getNature() {
		return nature;
	}

	public void setNature(NatureArticle nature) {
		this.nature = nature;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	// business methods:
	public Double getMontant() {
		try {
			return prix * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMontantAchat() {
		try {
			return prix * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public String getLienHhd() {
		try {
			return tache.getId() + "$" + designation.toUpperCase() + Math.round(prix * 100.0) / 100.0
					+ unite.toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

}
