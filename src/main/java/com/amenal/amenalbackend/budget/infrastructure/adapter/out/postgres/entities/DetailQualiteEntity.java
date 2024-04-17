package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

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

    @Column(name = "pointDeControle")
    private String pointDeControle;

    @Column(name = "importance")
    private String importance;

    @Column(name = "ordre")
    private String ordre;

    // Fk objects:
    @ManyToOne
    @JoinColumn(name = "id_grp_qualite")
    private GrpQualiteEntity groupe;

	public DetailQualiteEntity(Integer id, String pointDeControle, String importance, String ordre,
			GrpQualiteEntity groupe) {
		super();
		this.id = id;
		this.pointDeControle = pointDeControle;
		this.importance = importance;
		this.ordre = ordre;;
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

	public String getPointDeControle() {
		return pointDeControle;
	}

	public void setPointDeControle(String pointDeControle) {
		this.pointDeControle = pointDeControle;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public GrpQualiteEntity getGroupe() {
		return groupe;
	}

	public void setGroupe(GrpQualiteEntity groupe) {
		this.groupe = groupe;
	}

}
