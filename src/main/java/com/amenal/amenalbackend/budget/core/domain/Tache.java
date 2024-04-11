package com.amenal.amenalbackend.budget.core.domain;

import java.time.LocalDate;
import java.util.List;

//SousProduitAv in Microsoft access database
public class Tache {
	private Integer id;
	private String titreActivite;
	private Double qte;
	private Double prix;
	private String article;
	private String unite;
	private String observation;
	private Boolean cleAttachement;
	private LocalDate dateSaisie;
	private Integer delai;
	private LocalDate dateDebut;
	private Integer idPrdParent;
	private String defPrix;
	private Double mncRls;
	private Double mncBda;
	private Double qtePRls;
	private Double qtePBda;
	private Double avp;
	private Integer id2;
	private Integer delaiReel;
	private Boolean choisi;
	private Integer idProcedureDetail;
	private Boolean maj;
	private String descriptif;
	private String ordrePrt;
	private String ordreMef;
	private Integer ordre;

	// Fk objects:
	private Produit produit;
	private Lot lot;
	private List<DetailProduit> detailProduits;
	private List<DetailCharge> detailCharges;

	public Tache(Integer id, String titreActivite, Double qte, Double prix, String article, String unite,
			String observation, Boolean cleAttachement, LocalDate dateSaisie, Integer delai, LocalDate dateDebut,
			Integer idPrdParent, String defPrix, Double mncRls, Double mncBda, Double qtePRls, Double qtePBda,
			Double avp, Integer id2, Integer delaiReel, Boolean choisi, Integer idProcedureDetail,
			Boolean maj, String descriptif, String ordrePrt, String ordreMef, Integer ordre,
			Produit produit, Lot lot) {
		super();
		this.id = id;
		this.titreActivite = titreActivite;
		this.qte = qte;
		this.prix = prix;
		this.article = article;
		this.unite = unite;
		this.observation = observation;
		this.cleAttachement = cleAttachement;
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
		this.delaiReel = delaiReel;
		this.choisi = choisi;
		this.idProcedureDetail = idProcedureDetail;
		this.maj = maj;
		this.descriptif = descriptif;
		this.ordrePrt = ordrePrt;
		this.ordreMef = ordreMef;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
	}

	public Tache() {
		super();
	}

	public List<DetailProduit> getDetailProduits() {
		return detailProduits;
	}

	public void setDetailProduits(List<DetailProduit> detailProduits) {
		this.detailProduits = detailProduits;
	}

	public List<DetailCharge> getDetailCharges() {
		return detailCharges;
	}

	public void setDetailCharges(List<DetailCharge> detailCharges) {
		this.detailCharges = detailCharges;
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

	public Boolean getCleAttachement() {
		return cleAttachement;
	}

	public void setCleAttachement(Boolean cleAttachement) {
		this.cleAttachement = cleAttachement;
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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	// business methods:

	public Double getPuRef(){
		try {
			return produit.getPuRef();
		}catch (Exception e){
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

	public Double getPucBdg() {
		try {
			return getMncBdg() / getQtePBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntRefB() {
		try {
			return getPuRef() * getQtePBdg();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Double getMrgRefB() {
		try {
			return getMntRefB() - getMncBdg();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Double getMrpRefB() {
		try {
			return (getMntRefB() == 0 || getMntRefB() == null) ? 0 : (getMntRefB() - getMncBdg()) / getMntRefB();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Double getQtePBdg() {
		try {
			Double qteRefBCalcule = 0.0;
			for (DetailProduit detail : detailProduits) {
				qteRefBCalcule += detail.getQte();
			}
			return qteRefBCalcule;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMncBdg() {
		try {
			Double qteRefBCalcule = 0.0;
			for (DetailCharge detail : detailCharges) {
				qteRefBCalcule += detail.getMontant();		
			}
			return qteRefBCalcule;
		} catch (Exception e) {
			return null;
		}
	}

}
