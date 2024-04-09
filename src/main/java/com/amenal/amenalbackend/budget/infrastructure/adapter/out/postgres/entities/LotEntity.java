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
@Table(name = "lot")
public class LotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lot")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "pu")
    private Double pu;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "unite")
    private String unite;

    @Column(name = "designation")
    private String designation;

    @Column(name = "article")
    private String article;

    @Column(name = "delai_reel")
    private Integer delaiReel;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "date_saisie")
    private LocalDate dateSaisie;

    @Column(name = "affiche")
    private Boolean affiche;

    @Column(name = "affecte")
    private Boolean affecte;

    @Column(name = "toc_rls")
    private Double tocRls;

    @Column(name = "top_rls")
    private Double topRls;

    @Column(name = "toc_bda")
    private Double tocBda;

    @Column(name = "top_bda")
    private Double topBda;

    @Column(name = "toc_bdg")
    private Double tocBdg;

    @Column(name = "top_bdg")
    private Double topBdg;

    @Column(name = "id_anien")
    private Integer idAnien;

    @Column(name = "choisi")
    private Boolean choisi;

    @Column(name = "maj")
    private Boolean maj;

    @Column(name = "descriptif")
    private String descriptif;

    @Column(name = "ordre_mef")
    private String ordreMef;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "mnt_ref")
    private Double mntRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit_standard")
    private ProduitStandardEntity decompositionStandard;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    private ProjectEntity project;

	public LotEntity(Integer id, String nom, Double pu, Double qte, String unite, String designation, String article,
			Integer delaiReel, LocalDate dateDebut, LocalDate dateFin, LocalDate dateSaisie, Boolean affiche,
			Boolean affecte, Double tocRls, Double topRls, Double tocBda, Double topBda, Double tocBdg, Double topBdg,
			Integer idAnien, Boolean choisi, Boolean maj, String descriptif, String ordreMef, Integer ordre,
			Double mntRef, ProduitStandardEntity decompositionStandard, ProjectEntity project) {
		super();
		this.id = id;
		this.nom = nom;
		this.pu = pu;
		this.qte = qte;
		this.unite = unite;
		this.designation = designation;
		this.article = article;
		this.delaiReel = delaiReel;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateSaisie = dateSaisie;
		this.affiche = affiche;
		this.affecte = affecte;
		this.tocRls = tocRls;
		this.topRls = topRls;
		this.tocBda = tocBda;
		this.topBda = topBda;
		this.tocBdg = tocBdg;
		this.topBdg = topBdg;
		this.idAnien = idAnien;
		this.choisi = choisi;
		this.maj = maj;
		this.descriptif = descriptif;
		this.ordreMef = ordreMef;
		this.ordre = ordre;
		this.mntRef = mntRef;
		this.decompositionStandard = decompositionStandard;
		this.project = project;
	}

	public LotEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPu() {
		return pu;
	}

	public void setPu(Double pu) {
		this.pu = pu;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Integer getDelaiReel() {
		return delaiReel;
	}

	public void setDelaiReel(Integer delaiReel) {
		this.delaiReel = delaiReel;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public LocalDate getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDate dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Boolean getAffiche() {
		return affiche;
	}

	public void setAffiche(Boolean affiche) {
		this.affiche = affiche;
	}

	public Boolean getAffecte() {
		return affecte;
	}

	public void setAffecte(Boolean affecte) {
		this.affecte = affecte;
	}

	public Double getTocRls() {
		return tocRls;
	}

	public void setTocRls(Double tocRls) {
		this.tocRls = tocRls;
	}

	public Double getTopRls() {
		return topRls;
	}

	public void setTopRls(Double topRls) {
		this.topRls = topRls;
	}

	public Double getTocBda() {
		return tocBda;
	}

	public void setTocBda(Double tocBda) {
		this.tocBda = tocBda;
	}

	public Double getTopBda() {
		return topBda;
	}

	public void setTopBda(Double topBda) {
		this.topBda = topBda;
	}

	public Double getTocBdg() {
		return tocBdg;
	}

	public void setTocBdg(Double tocBdg) {
		this.tocBdg = tocBdg;
	}

	public Double getTopBdg() {
		return topBdg;
	}

	public void setTopBdg(Double topBdg) {
		this.topBdg = topBdg;
	}

	public Integer getIdAnien() {
		return idAnien;
	}

	public void setIdAnien(Integer idAnien) {
		this.idAnien = idAnien;
	}

	public Boolean getChoisi() {
		return choisi;
	}

	public void setChoisi(Boolean choisi) {
		this.choisi = choisi;
	}

	public Boolean getMaj() {
		return maj;
	}

	public void setMaj(Boolean maj) {
		this.maj = maj;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getOrdreMef() {
		return ordreMef;
	}

	public void setOrdreMef(String ordreMef) {
		this.ordreMef = ordreMef;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Double getMntRef() {
		return mntRef;
	}

	public void setMntRef(Double mntRef) {
		this.mntRef = mntRef;
	}

	public ProduitStandardEntity getDecompositionStandard() {
		return decompositionStandard;
	}

	public void setDecompositionStandard(ProduitStandardEntity decompositionStandard) {
		this.decompositionStandard = decompositionStandard;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

}
