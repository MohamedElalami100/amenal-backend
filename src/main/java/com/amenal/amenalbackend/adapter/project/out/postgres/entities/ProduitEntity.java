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
import java.time.LocalDate;

@Entity
@Table(name = "produit")
public class ProduitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Integer id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "unite")
    private String unite;

    @Column(name = "pu")
    private Double pu;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "article")
    private String article;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "titre")
    private String titre;

    @Column(name = "cache")
    private Boolean cache;

    @Column(name = "valide_dcp")
    private Boolean valideDcp;

    @Column(name = "def_prix")
    private String defPrix;

    @Column(name = "mnc_rls")
    private Double mncRls;

    @Column(name = "mnc_bda")
    private Double mncBda;

    @Column(name = "qtep_rls")
    private Double qtepRls;

    @Column(name = "qtep_bda")
    private Double qtepBda;

    @Column(name = "avp")
    private Double avp;

    @Column(name = "pu_ref")
    private Double puRef;

    @Column(name = "qte_ref")
    private Double qteRef;

    @Column(name = "id_supp")
    private Integer idSupp;

    @Column(name = "date_dbt_ini")
    private LocalDate dateDbtIni;

    @Column(name = "dla_ini")
    private Integer dlaIni;

    @Column(name = "date_dbt_fin")
    private LocalDate dateDbtFin;

    @Column(name = "dla_fin")
    private Integer dlaFin;

    @Column(name = "mdf_manelle")
    private Boolean mdfManelle;

    @Column(name = "id2")
    private Integer id2;

    @Column(name = "maj")
    private Boolean maj;

    @Column(name = "amj")
    private Boolean amj;

    @Column(name = "order_mef")
    private String orderMef;

    @Column(name = "affecte")
    private Boolean affecte;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metre_av")
    private MetreAvEntity metre;

	public ProduitEntity(Integer id, String designation, String unite, Double pu, Double qte, String article, Integer ordre,
			String titre, Boolean cache, Boolean valideDcp, String defPrix, Double mncRls,
			Double mncBda, Double qtepRls, Double qtepBda, Double avp, Double puRef, Double qteRef, Integer idSupp,
			LocalDate dateDbtIni, Integer dlaIni, LocalDate dateDbtFin, Integer dlaFin, Boolean mdfManelle, Integer id2,
			Boolean maj, Boolean amj, String orderMef, Boolean affecte, MetreAvEntity metre) {
		super();
		this.id = id;
		this.designation = designation;
		this.unite = unite;
		this.pu = pu;
		this.qte = qte;
		this.article = article;
		this.ordre = ordre;
		this.titre = titre;
		this.cache = cache;
		this.valideDcp = valideDcp;
		this.defPrix = defPrix;
		this.mncRls = mncRls;
		this.mncBda = mncBda;
		this.qtepRls = qtepRls;
		this.qtepBda = qtepBda;
		this.avp = avp;
		this.puRef = puRef;
		this.qteRef = qteRef;
		this.idSupp = idSupp;
		this.dateDbtIni = dateDbtIni;
		this.dlaIni = dlaIni;
		this.dateDbtFin = dateDbtFin;
		this.dlaFin = dlaFin;
		this.mdfManelle = mdfManelle;
		this.id2 = id2;
		this.maj = maj;
		this.amj = amj;
		this.orderMef = orderMef;
		this.affecte = affecte;
		this.metre = metre;
	}

	public ProduitEntity() {
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

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
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

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Boolean getCache() {
		return cache;
	}

	public void setCache(Boolean cache) {
		this.cache = cache;
	}

	public Boolean getValideDcp() {
		return valideDcp;
	}

	public void setValideDcp(Boolean valideDcp) {
		this.valideDcp = valideDcp;
	}

	public String getDefPrix() {
		return defPrix;
	}

	public void setDefPrix(String defPrix) {
		this.defPrix = defPrix;
	}

	public Double getMncRls() {
		return mncRls;
	}

	public void setMncRls(Double mncRls) {
		this.mncRls = mncRls;
	}

	public Double getMncBda() {
		return mncBda;
	}

	public void setMncBda(Double mncBda) {
		this.mncBda = mncBda;
	}

	public Double getQtepRls() {
		return qtepRls;
	}

	public void setQtepRls(Double qtepRls) {
		this.qtepRls = qtepRls;
	}

	public Double getQtepBda() {
		return qtepBda;
	}

	public void setQtepBda(Double qtepBda) {
		this.qtepBda = qtepBda;
	}

	public Double getAvp() {
		return avp;
	}

	public void setAvp(Double avp) {
		this.avp = avp;
	}

	public Double getPuRef() {
		return puRef;
	}

	public void setPuRef(Double puRef) {
		this.puRef = puRef;
	}

	public Double getQteRef() {
		return qteRef;
	}

	public void setQteRef(Double qteRef) {
		this.qteRef = qteRef;
	}

	public Integer getIdSupp() {
		return idSupp;
	}

	public void setIdSupp(Integer idSupp) {
		this.idSupp = idSupp;
	}

	public LocalDate getDateDbtIni() {
		return dateDbtIni;
	}

	public void setDateDbtIni(LocalDate dateDbtIni) {
		this.dateDbtIni = dateDbtIni;
	}

	public Integer getDlaIni() {
		return dlaIni;
	}

	public void setDlaIni(Integer dlaIni) {
		this.dlaIni = dlaIni;
	}

	public LocalDate getDateDbtFin() {
		return dateDbtFin;
	}

	public void setDateDbtFin(LocalDate dateDbtFin) {
		this.dateDbtFin = dateDbtFin;
	}

	public Integer getDlaFin() {
		return dlaFin;
	}

	public void setDlaFin(Integer dlaFin) {
		this.dlaFin = dlaFin;
	}

	public Boolean getMdfManelle() {
		return mdfManelle;
	}

	public void setMdfManelle(Boolean mdfManelle) {
		this.mdfManelle = mdfManelle;
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

	public Boolean getAmj() {
		return amj;
	}

	public void setAmj(Boolean amj) {
		this.amj = amj;
	}

	public String getOrderMef() {
		return orderMef;
	}

	public void setOrderMef(String orderMef) {
		this.orderMef = orderMef;
	}

	public Boolean getAffecte() {
		return affecte;
	}

	public void setAffecte(Boolean affecte) {
		this.affecte = affecte;
	}

	public MetreAvEntity getMetre() {
		return metre;
	}

	public void setMetre(MetreAvEntity metre) {
		this.metre = metre;
	}

}
