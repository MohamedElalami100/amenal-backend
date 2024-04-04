package com.amenal.amenalbackend.achat.application.domain;

import com.amenal.amenalbackend.budget.application.domain.DetailProduit;

public class DetailBesoin {
	private Integer id;
	private DetailProduit produit;
	private Double qte;
	private Besoin besoin;

	public DetailBesoin(Integer id, DetailProduit produit, Double qte, Besoin besoin) {
		super();
		this.id = id;
		this.produit = produit;
		this.qte = qte;
		this.besoin = besoin;
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

	public Besoin getBesoin() {
		return besoin;
	}

	public void setBesoin(Besoin besoin) {
		this.besoin = besoin;
	}

}
