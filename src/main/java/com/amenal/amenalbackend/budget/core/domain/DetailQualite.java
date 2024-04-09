package com.amenal.amenalbackend.budget.core.domain;

public class DetailQualite {
	private Integer id;
	private String affaire;
	private String importance;
	private Integer ordre;
	private String ordreMef;
	private String ordrePrt;
	private Integer id2;
	private Boolean maj;

	// Fk objects:
	private GrpQualite groupe;

	public DetailQualite(Integer id, String affaire, String importance, Integer ordre, String ordreMef, String ordrePrt,
			Integer id2, Boolean maj, GrpQualite groupe) {
		super();
		this.id = id;
		this.affaire = affaire;
		this.importance = importance;
		this.ordre = ordre;
		this.ordreMef = ordreMef;
		this.ordrePrt = ordrePrt;
		this.id2 = id2;
		this.maj = maj;
		this.groupe = groupe;
	}

	public DetailQualite() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAffaire() {
		return affaire;
	}

	public void setAffaire(String affaire) {
		this.affaire = affaire;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
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

	public GrpQualite getGroupe() {
		return groupe;
	}

	public void setGroupe(GrpQualite groupe) {
		this.groupe = groupe;
	}

}
