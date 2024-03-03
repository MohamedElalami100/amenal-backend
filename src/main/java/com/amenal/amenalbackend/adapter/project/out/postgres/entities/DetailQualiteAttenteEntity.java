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
@Table(name = "detail_qualite_attente")
public class DetailQualiteAttenteEntity {
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

    @Column(name = "groupe")
    private String groupe;

    @Column(name = "point_de_controle")
    private String pointDeControle;

    @Column(name = "erreur")
    private String erreur;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metre_av")
    private MetreAvEntity metre;

	public DetailQualiteAttenteEntity(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String groupe, String pointDeControle, String erreur, MetreAvEntity metre) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.groupe = groupe;
		this.pointDeControle = pointDeControle;
		this.erreur = erreur;
		this.metre = metre;
	}

	public DetailQualiteAttenteEntity() {
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

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getPointDeControle() {
		return pointDeControle;
	}

	public void setPointDeControle(String pointDeControle) {
		this.pointDeControle = pointDeControle;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public MetreAvEntity getMetre() {
		return metre;
	}

	public void setMetre(MetreAvEntity metre) {
		this.metre = metre;
	}

}
