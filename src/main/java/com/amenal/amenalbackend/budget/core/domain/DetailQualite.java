package com.amenal.amenalbackend.budget.core.domain;

public class DetailQualite {
	private Integer id;
	private String pointDeControle;
	private String importance;
	private String ordre;

	// Fk objects:
	private GrpQualite groupe;

	public DetailQualite(Integer id, String pointDeControle, String importance, String ordreMef, String ordrePrt,
			Integer id2, Boolean maj,String ordre, GrpQualite groupe) {
		super();
		this.id = id;
		this.pointDeControle = pointDeControle;
		this.importance = importance;
		this.ordre = ordre;
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

	public String getPointDeControle() {
		return pointDeControle;
	}

	public void setPointDeControle(String pointDeControle) {
		this.pointDeControle = pointDeControle;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public GrpQualite getGroupe() {
		return groupe;
	}

	public void setGroupe(GrpQualite groupe) {
		this.groupe = groupe;
	}

}
