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
@Table(name = "tache")
public class TacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tache")
    private Integer id;

    @Column(name = "titre_activite")
    private String titreActivite;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "article")
    private String article;

    @Column(name = "unite")
    private String unite;

    @Column(name = "observation")
    private String observation;

    @Column(name = "lie_attachement")
    private Boolean lieAttachement;

    @Column(name = "date_saisie")
    private LocalDate dateSaisie;

    @Column(name = "delai")
    private Integer delai;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "id_prd_parent")
    private Integer idPrdParent;

    @Column(name = "def_prix")
    private String defPrix;

    @Column(name = "mnc_rls")
    private Double mncRls;

    @Column(name = "mnc_bda")
    private Double mncBda;

    @Column(name = "qte_p_rls")
    private Double qtePRls;

    @Column(name = "qte_p_bda")
    private Double qtePBda;

    @Column(name = "avp")
    private Double avp;

    @Column(name = "id2")
    private Integer id2;

    @Column(name = "mnc_bdg")
    private Double mncBdg;

    @Column(name = "delai_reel")
    private Integer delaiReel;

    @Column(name = "choisi")
    private Boolean choisi;

    @Column(name = "id_procedure_detail")
    private Integer idProcedureDetail;

    @Column(name = "maj")
    private Boolean maj;

    @Column(name = "descriptif")
    private String descriptif;

    @Column(name = "ordre_prt")
    private String ordrePrt;

    @Column(name = "ordre_mef")
    private String ordreMef;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "pu_ref")
    private Double puRef;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit")
    private ProduitEntity produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lot")
    private LotEntity lot;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activite_principale")
    private TacheEntity activitePrincipale;

	public TacheEntity(Integer id, String titreActivite, Double qte, Double prix, String article, String unite,
			String observation, Boolean lieAttachement, LocalDate dateSaisie, Integer delai,
			LocalDate dateDebut, Integer idPrdParent, String defPrix, Double mncRls, Double mncBda, Double qtePRls,
			Double qtePBda, Double avp, Integer id2, Double mncBdg, Integer delaiReel, Boolean choisi,
			Integer idProcedureDetail, Boolean maj, String descriptif, String ordrePrt, String ordreMef, Integer ordre,
			Double puRef, ProduitEntity produit, LotEntity lot, TacheEntity activitePrincipale) {
		super();
		this.id = id;
		this.titreActivite = titreActivite;
		this.qte = qte;
		this.prix = prix;
		this.article = article;
		this.unite = unite;
		this.observation = observation;
		this.lieAttachement = lieAttachement;
		this.dateSaisie = dateSaisie;
		this.delai = delai;
		this.dateDebut = dateDebut;
		this.idPrdParent = idPrdParent;
		this.defPrix = defPrix;
		this.mncRls = mncRls;
		this.mncBda = mncBda;
		this.qtePRls = qtePRls;
		this.qtePBda = qtePBda;
		this.avp = avp;
		this.id2 = id2;
		this.mncBdg = mncBdg;
		this.delaiReel = delaiReel;
		this.choisi = choisi;
		this.idProcedureDetail = idProcedureDetail;
		this.maj = maj;
		this.descriptif = descriptif;
		this.ordrePrt = ordrePrt;
		this.ordreMef = ordreMef;
		this.ordre = ordre;
		this.puRef = puRef;
		this.produit = produit;
		this.lot = lot;
		this.activitePrincipale = activitePrincipale;
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

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Boolean getLieAttachement() {
		return lieAttachement;
	}

	public void setLieAttachement(Boolean lieAttachement) {
		this.lieAttachement = lieAttachement;
	}

	public LocalDate getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDate dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Integer getDelai() {
		return delai;
	}

	public void setDelai(Integer delai) {
		this.delai = delai;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Integer getIdPrdParent() {
		return idPrdParent;
	}

	public void setIdPrdParent(Integer idPrdParent) {
		this.idPrdParent = idPrdParent;
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

	public Double getQtePRls() {
		return qtePRls;
	}

	public void setQtePRls(Double qtePRls) {
		this.qtePRls = qtePRls;
	}

	public Double getQtePBda() {
		return qtePBda;
	}

	public void setQtePBda(Double qtePBda) {
		this.qtePBda = qtePBda;
	}

	public Double getAvp() {
		return avp;
	}

	public void setAvp(Double avp) {
		this.avp = avp;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public Double getMncBdg() {
		return mncBdg;
	}

	public void setMncBdg(Double mncBdg) {
		this.mncBdg = mncBdg;
	}

	public Integer getDelaiReel() {
		return delaiReel;
	}

	public void setDelaiReel(Integer delaiReel) {
		this.delaiReel = delaiReel;
	}

	public Boolean getChoisi() {
		return choisi;
	}

	public void setChoisi(Boolean choisi) {
		this.choisi = choisi;
	}

	public Integer getIdProcedureDetail() {
		return idProcedureDetail;
	}

	public void setIdProcedureDetail(Integer idProcedureDetail) {
		this.idProcedureDetail = idProcedureDetail;
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

	public String getOrdrePrt() {
		return ordrePrt;
	}

	public void setOrdrePrt(String ordrePrt) {
		this.ordrePrt = ordrePrt;
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

	public Double getPuRef() {
		return puRef;
	}

	public void setPuRef(Double puRef) {
		this.puRef = puRef;
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

	public TacheEntity getActivitePrincipale() {
		return activitePrincipale;
	}

	public void setActivitePrincipale(TacheEntity activitePrincipale) {
		this.activitePrincipale = activitePrincipale;
	}

	// business methods:
	public Double getMontantVnt() {
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

	public String getLienPrdAtt() {
		try {
			return lot.getId() + "$" + produit.getId();
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateFin() {
		try {
			return (delai < 0) ? null : dateDebut.plusDays(delai - 1);
		} catch (Exception e) {
			return null;
		}
	}

	public Double getQtePBdg() {
		return qte;
	}

	public Double getPucRls() {
		try {
			return mncRls / qtePRls;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getPucBda() {
		try {
			return mncBda / qtePBda;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMnpRls() {
		try {
			return qtePRls * prix;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMnpBda() {
		try {
			return qtePBda * prix;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRls() {
		try {
			return getMnpRls() - mncRls;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpRls() {
		try {
			return (getMnpRls() == 0 || getMnpRls() == null) ? 0 : (getMnpRls() - mncRls) / getMnpRls();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgBda() {
		try {
			return getMnpBda() - mncBda;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpBda() {
		try {
			return (getMnpBda() == 0 || getMnpBda() == null) ? 0 : (getMnpBda() - mncBda) / getMnpRls();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getPucBdg() {
		try {
			return mncBdg / getQtePBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMnpBdg() {
		return getMontantVnt();
	}

	public Double getMrpBdg() {
		try {
			return (getMnpBdg() == 0 || getMnpBdg() == null) ? 0 : (getMnpBdg() - mncBdg) / getMnpBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgBdg() {
		try {
			return getMnpBdg() - mncBdg;
		} catch (Exception e) {
			return null;
		}
	}

	public Integer getDelaiPrevu() {
		try {
			return (int) (getDateFin().toEpochDay() - dateDebut.toEpochDay() + 1);
		} catch (Exception e) {
			return null;
		}
	}

	public Double getQteRef() {
		return qte;
	}

	public Double getMntRef() {
		try {
			return puRef * getQteRef();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRef() {
		try {
			return getMntRef() - mncBdg;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpRef() {
		try {
			return (getMntRef() == 0 || getMntRef() == null) ? 0 : (getMntRef() - mncBdg) / getMntRef();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Boolean getCleAttachement() {
		return activitePrincipale == null;
	}

}
