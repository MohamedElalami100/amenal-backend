package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

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
@Table(name = "detail_charge")
public class DetailChargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_charge")
    private Integer id;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "unite")
    private String unite;

    @Column(name = "article")
    private String article;

    @Column(name = "designation")
    private String designation;

    @Column(name = "date_saisie")
    private LocalDate dateSaisie;

    @Column(name = "id2")
    private Integer id2;

    @Column(name = "maj")
    private Boolean maj;

    @Column(name = "ordre_mef")
    private String ordreMef;

    @Column(name = "ordre_prt")
    private String ordrePrt;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "la_qte_prd")
    private Double laQtePrd;

    @Column(name = "la_unite_prd")
    private String laUnitePrd;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nature_article")
    private NatureArticleEntity nature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tache")
    private TacheEntity tache;

	public DetailChargeEntity(Integer id, Double prix, Double qte, String unite, String article, String designation,
			LocalDate dateSaisie, Integer id2, Boolean maj, String ordreMef, String ordrePrt, Integer ordre,
			Double laQtePrd, String laUnitePrd, NatureArticleEntity nature, TacheEntity tache) {
		super();
		this.id = id;
		this.prix = prix;
		this.qte = qte;
		this.unite = unite;
		this.article = article;
		this.designation = designation;
		this.dateSaisie = dateSaisie;
		this.id2 = id2;
		this.maj = maj;
		this.ordreMef = ordreMef;
		this.ordrePrt = ordrePrt;
		this.ordre = ordre;
		this.laQtePrd = laQtePrd;
		this.laUnitePrd = laUnitePrd;
		this.nature = nature;
		this.tache = tache;
	}

	public DetailChargeEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDate dateSaisie) {
		this.dateSaisie = dateSaisie;
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

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Double getLaQtePrd() {
		return laQtePrd;
	}

	public void setLaQtePrd(Double laQtePrd) {
		this.laQtePrd = laQtePrd;
	}

	public String getLaUnitePrd() {
		return laUnitePrd;
	}

	public void setLaUnitePrd(String laUnitePrd) {
		this.laUnitePrd = laUnitePrd;
	}

	public NatureArticleEntity getNature() {
		return nature;
	}

	public void setNature(NatureArticleEntity nature) {
		this.nature = nature;
	}

	public TacheEntity getTache() {
		return tache;
	}

	public void setTache(TacheEntity tache) {
		this.tache = tache;
	}

	// business methods:
	public Double getMontant() {
		try {
			return prix * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMontantAchat() {
		try {
			return prix * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public String getLienHhd() {
		try {
			return tache.getId() + "$" + designation.toUpperCase() + Math.round(prix * 100.0) / 100.0
					+ unite.toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

}
