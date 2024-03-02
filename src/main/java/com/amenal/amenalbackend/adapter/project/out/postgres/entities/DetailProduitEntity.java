package com.amenal.amenalbackend.adapter.project.out.postgres.entities;

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
@Table(name = "detail_produit")
public class DetailProduitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_produit")
    private Integer id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "nbr")
    private Double nbr;

    @Column(name = "dim1")
    private Double dim1;

    @Column(name = "dim2")
    private Double dim2;

    @Column(name = "dim3")
    private Double dim3;

    @Column(name = "id2")
    private Integer id2;

    @Column(name = "maj")
    private Boolean maj;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "ordre_mef")
    private String ordreMef;

    @Column(name = "ordre_prt")
    private String ordrePrt;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tache")
    private TacheEntity tache;

	public DetailProduitEntity(Integer id, String reference, Double nbr, Double dim1, Double dim2, Double dim3,
			Integer id2, Boolean maj, Integer ordre, String ordreMef, String ordrePrt, TacheEntity tache) {
		super();
		this.id = id;
		this.reference = reference;
		this.nbr = nbr;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.id2 = id2;
		this.maj = maj;
		this.ordre = ordre;
		this.ordreMef = ordreMef;
		this.ordrePrt = ordrePrt;
		this.tache = tache;
	}

	public DetailProduitEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getNbr() {
		return nbr;
	}

	public void setNbr(Double nbr) {
		this.nbr = nbr;
	}

	public Double getDim1() {
		return dim1;
	}

	public void setDim1(Double dim1) {
		this.dim1 = dim1;
	}

	public Double getDim2() {
		return dim2;
	}

	public void setDim2(Double dim2) {
		this.dim2 = dim2;
	}

	public Double getDim3() {
		return dim3;
	}

	public void setDim3(Double dim3) {
		this.dim3 = dim3;
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

	public TacheEntity getTache() {
		return tache;
	}

	public void setTache(TacheEntity tache) {
		this.tache = tache;
	}

}
