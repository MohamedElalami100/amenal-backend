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
import java.time.LocalDate;

@Entity
@Table(name = "tache")
public class TacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tache")
    private Integer id;

    @Column(name = "titre_activite")
    private String titreActivite;
	
    @Column(name = "upb")
    private String upb;

    @Column(name = "cle")
    private Boolean cle;

    @Column(name = "dlb")
    private Integer dlb;

    @Column(name = "ddb")
    private LocalDate ddb;

    @Column(name = "ordre")
    private String ordre;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit")
    private ProduitEntity produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lot")
    private LotEntity lot;

	public TacheEntity(Integer id, String titreActivite, String upb, Boolean cle, Integer dlb, LocalDate ddb, String ordre, ProduitEntity produit, LotEntity lot) {
		this.id = id;
		this.titreActivite = titreActivite;
		this.upb = upb;
		this.cle = cle;
		this.dlb = dlb;
		this.ddb = ddb;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
	}

	public TacheEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitreActivite() {
		return titreActivite;
	}

	public void setTitreActivite(String titreActivite) {
		this.titreActivite = titreActivite;
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

	public Integer getDlb() {
		return dlb;
	}

	public void setDlb(Integer dlb) {
		this.dlb = dlb;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public ProduitEntity getProduit() {
		return produit;
	}

	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}

	public LotEntity getLot() {
		return lot;
	}

	public void setLot(LotEntity lot) {
		this.lot = lot;
	}

}
