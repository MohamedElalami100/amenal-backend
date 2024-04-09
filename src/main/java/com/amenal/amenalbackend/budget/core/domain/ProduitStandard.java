package com.amenal.amenalbackend.budget.core.domain;

public class ProduitStandard {
	private Integer id;
	private String designation;
	private Integer ordre;
	private Integer idProduitAncien;
	private Boolean aSupp;

	public ProduitStandard(Integer id, String designation, Integer ordre, Integer idProduitAncien, Boolean aSupp) {
		super();
		this.id = id;
		this.designation = designation;
		this.ordre = ordre;
		this.idProduitAncien = idProduitAncien;
		this.aSupp = aSupp;
	}

	public ProduitStandard() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Integer getIdProduitAncien() {
		return idProduitAncien;
	}

	public void setIdProduitAncien(Integer idProduitAncien) {
		this.idProduitAncien = idProduitAncien;
	}

	public Boolean getaSupp() {
		return aSupp;
	}

	public void setaSupp(Boolean aSupp) {
		this.aSupp = aSupp;
	}

}
