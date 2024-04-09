package com.amenal.amenalbackend.budget.application.domain;

public class NatureArticle {
	private Integer id;
	private String nature;
	private String abreg;
	private String stockParDefaut;
	private Boolean slc;
	private Boolean stockable;
	private Boolean amortissable;

	public NatureArticle(Integer id, String nature, String abreg, String stockParDefaut, Boolean slc, Boolean stockable,
			Boolean amortissable) {
		super();
		this.id = id;
		this.nature = nature;
		this.abreg = abreg;
		this.stockParDefaut = stockParDefaut;
		this.slc = slc;
		this.stockable = stockable;
		this.amortissable = amortissable;
	}

	public NatureArticle() {
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
