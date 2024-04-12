package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "detail_besoin")
public class DetailBesoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_besoin")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit")
    private DetailProduitEntity produit;

    @Column(name = "qte")
    private Double qte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_besoin")
	@JsonIgnore
    private BesoinEntity besoin;

	public DetailBesoinEntity(Integer id, DetailProduitEntity produit, Double qte, BesoinEntity besoin) {
		super();
		this.id = id;
		this.produit = produit;
		this.qte = qte;
		this.besoin = besoin;
	}

	public DetailBesoinEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailProduitEntity getProduit() {
		return produit;
	}

	public void setProduit(DetailProduitEntity produit) {
		this.produit = produit;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public BesoinEntity getBesoin() {
		return besoin;
	}

	public void setBesoin(BesoinEntity besoin) {
		this.besoin = besoin;
	}

}
