package com.amenal.amenalbackend.achat.core.domain;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;

public class DetailBesoin {
	private Integer id;
	private DetailProduit produit;
	private Double qte;

	public DetailBesoin(Integer id, DetailProduit produit, Double qte) {
		super();
		this.id = id;
		this.produit = produit;
		this.qte = qte;
	}

	public DetailBesoin() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailProduit getProduit() {
		return produit;
	}

	public void setProduit(DetailProduit produit) {
		this.produit = produit;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

}
