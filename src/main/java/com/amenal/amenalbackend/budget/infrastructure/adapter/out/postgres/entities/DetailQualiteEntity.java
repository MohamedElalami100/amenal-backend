package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail_qualite")
public class DetailQualiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_qualite")
    private Integer id;

    @Column(name = "affaire")
    private String affaire;

    @Column(name = "importance")
    private String importance;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "ordre_mef")
    private String ordreMef;

    @Column(name = "ordre_prt")
    private String ordrePrt;

    @Column(name = "id2")
    private Integer id2;

    @Column(name = "maj")
    private Boolean maj;

    // Fk objects:
    @ManyToOne
    @JoinColumn(name = "id_grp_qualite")
    private GrpQualiteEntity groupe;

	public DetailQualiteEntity(Integer id, String affaire, String importance, Integer ordre, String ordreMef, String ordrePrt,
			Integer id2, Boolean maj, GrpQualiteEntity groupe) {
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

	public DetailQualiteEntity() {
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

	public GrpQualiteEntity getGroupe() {
		return groupe;
	}

	public void setGroupe(GrpQualiteEntity groupe) {
		this.groupe = groupe;
	}

}
