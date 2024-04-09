package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produit_standard")
public class ProduitStandardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit_standard")
    private Integer id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "id_produit_ancien")
    private Integer idProduitAncien;

    @Column(name = "a_supp")
    private Boolean aSupp;

	public ProduitStandardEntity(Integer id, String designation, Integer ordre, Integer idProduitAncien, Boolean aSupp) {
		super();
		this.id = id;
		this.designation = designation;
		this.ordre = ordre;
		this.idProduitAncien = idProduitAncien;
		this.aSupp = aSupp;
	}

	public ProduitStandardEntity() {
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
