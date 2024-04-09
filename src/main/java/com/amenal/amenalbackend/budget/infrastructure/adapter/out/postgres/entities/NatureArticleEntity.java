package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nature_article")
public class NatureArticleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nature_article")
	private Integer id;

	@Column(name = "nature")
	private String nature;

	@Column(name = "abreg")
	private String abreg;

	@Column(name = "stock_par_defaut")
	private String stockParDefaut;

	@Column(name = "slc")
	private Boolean slc;

	@Column(name = "stockable")
	private Boolean stockable;

	@Column(name = "amortissable")
	private Boolean amortissable;

	public NatureArticleEntity(Integer id, String nature, String abreg, String stockParDefaut, Boolean slc,
			Boolean stockable, Boolean amortissable) {
		super();
		this.id = id;
		this.nature = nature;
		this.abreg = abreg;
		this.stockParDefaut = stockParDefaut;
		this.slc = slc;
		this.stockable = stockable;
		this.amortissable = amortissable;
	}

	public NatureArticleEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getAbreg() {
		return abreg;
	}

	public void setAbreg(String abreg) {
		this.abreg = abreg;
	}

	public String getStockParDefaut() {
		return stockParDefaut;
	}

	public void setStockParDefaut(String stockParDefaut) {
		this.stockParDefaut = stockParDefaut;
	}

	public Boolean getSlc() {
		return slc;
	}

	public void setSlc(Boolean slc) {
		this.slc = slc;
	}

	public Boolean getStockable() {
		return stockable;
	}

	public void setStockable(Boolean stockable) {
		this.stockable = stockable;
	}

	public Boolean getAmortissable() {
		return amortissable;
	}

	public void setAmortissable(Boolean amortissable) {
		this.amortissable = amortissable;
	}

}
