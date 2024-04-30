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
@Table(name = "produit")
public class ProduitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produit")
	private Integer id;

	@Column(name = "designation")
	private String designation;

	@Column(name = "upb")
	private String upb;

	@Column(name = "art")
	private String art;

	@Column(name = "ppm")
	private Double ppm;

	@Column(name = "qpm")
	private Double qpm;

	// Fk objects:
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_metre_av")
	private MetreAvEntity metre;

	public ProduitEntity(Integer id, String art, String designation, String upb, Double ppm, Double qpm,
			MetreAvEntity metre) {
		this.id = id;
		this.art = art;
		this.designation = designation;
		this.upb = upb;
		this.ppm = ppm;
		this.qpm = qpm;
		this.metre = metre;
	}

	public ProduitEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Double getPpm() {
		return ppm;
	}

	public void setPpm(Double ppm) {
		this.ppm = ppm;
	}

	public Double getQpm() {
		return qpm;
	}

	public void setQpm(Double qpm) {
		this.qpm = qpm;
	}

	public MetreAvEntity getMetre() {
		return metre;
	}

	public void setMetre(MetreAvEntity metre) {
		this.metre = metre;
	}

}
