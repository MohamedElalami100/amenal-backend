package com.amenal.amenalbackend.adapter.project.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail_produit_attente")
public class DetailProduitAttenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ordre")
    private String ordre;

    @Column(name = "produit")
    private String produit;

    @Column(name = "lot")
    private String lot;

    @Column(name = "activite")
    private String activite;

    @Column(name = "upb")
    private String upb;

    @Column(name = "cle")
    private Boolean cle;

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

    @Column(name = "rls")
    private Double rls;

    @Column(name = "erreur")
    private String erreur;

    @ManyToOne
    @JoinColumn(name = "id_avenant")
    private AvenantEntity avenant;
	
	public DetailProduitAttenteEntity(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String reference, Double nbr, Double dim1, Double dim2, Double dim3, Double rls, String erreur, AvenantEntity avenant) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.reference = reference;
		this.nbr = nbr;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.rls = rls;
		this.erreur = erreur;
		this.avenant = avenant;
	}

	public DetailProduitAttenteEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Boolean getCle() {
		return cle;
	}

	public void setCle(Boolean cle) {
		this.cle = cle;
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

	public Double getRls() {
		return rls;
	}

	public void setRls(Double rls) {
		this.rls = rls;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public AvenantEntity getAvenant() {
		return avenant;
	}

	public void setAvenant(AvenantEntity avenant) {
		this.avenant = avenant;
	}
	
}
