package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grp_qualite")
public class GrpQualiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grp_qualite")
    private Integer id;

    @Column(name = "titre")
    private String titre;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tache")
    private TacheEntity tache;

	public GrpQualiteEntity(Integer id, String titre, TacheEntity tache, Integer ordre, String ordreMef, String ordrePrt,
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

	public GrpQualiteEntity() {
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

	public TacheEntity getTache() {
		return tache;
	}

	public void setTache(TacheEntity tache) {
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
