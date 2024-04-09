package com.amenal.amenalbackend.budget.core.domain;

public class GrpQualite {
	private Integer id;
	private String titre;
	private Tache tache;
	private Integer ordre;
	private String ordreMef;
	private String ordrePrt;
	private Integer id2;
	private Boolean maj;

	public GrpQualite(Integer id, String titre, Tache tache, Integer ordre, String ordreMef, String ordrePrt,
			Integer id2, Boolean maj) {
		super();
		this.id = id;
		this.titre = titre;
		this.tache = tache;
		this.ordre = ordre;
		this.ordreMef = ordreMef;
		this.ordrePrt = ordrePrt;
		this.id2 = id2;
		this.maj = maj;
	}

	public GrpQualite() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
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

}
