package com.amenal.amenalbackend.application.project.domain;

import java.time.LocalDate;
import java.util.List;

//MétréDétailAv in Microsoft access database
public class Produit {
	private Integer id;
	private String designation;
	private String unite;
	private Double pu;
	private Double qte;
	private String article;
	private Integer ordre;
	private String titre;
	private Boolean cache;
	private Boolean valideDcp;
	private String defPrix;
	private Double mncRls;
	private Double mncBda;
	private Double qtepRls;
	private Double qtepBda;
	private Double avp;
	private Double puRef;
	private Double qteRef;
	private Integer idSupp;
	private LocalDate dateDbtFin;
	private Integer dlaFin;
	private Boolean mdfManelle;
	private Integer id2;
	private Boolean maj;
	private Boolean amj;
	private String orderMef;
	private Boolean affecte;
	// Fk objects:
	private MetreAv metre;
	private List<Tache> taches;

	public Produit(Integer id, String designation, String unite, Double pu, Double qte, String article, Integer ordre,
			String titre, Boolean cache, Boolean valideDcp, String defPrix, Double mncRls, Double mncBda,
			Double qtepRls, Double qtepBda, Double avp, Double puRef, Double qteRef, Integer idSupp,
			LocalDate dateDbtFin, Integer dlaFin, Boolean mdfManelle, Integer id2,
			Boolean maj, Boolean amj, String orderMef, Boolean affecte, MetreAv metre, List<Tache> taches) {
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
		this.dateDbtFin = dateDbtFin;
		this.dlaFin = dlaFin;
		this.mdfManelle = mdfManelle;
		this.id2 = id2;
		this.maj = maj;
		this.amj = amj;
		this.orderMef = orderMef;
		this.affecte = affecte;
		this.metre = metre;
		this.taches = taches;
	}

	public Produit() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
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

	public MetreAv getMetre() {
		return metre;
	}

	public void setMetre(MetreAv metre) {
		this.metre = metre;
	}

	// business methods:
	public Double getMontant() {
		try {
			return pu * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean isValideTitre() {
		return qte == null && unite == null;
	}

	public Boolean isValideArticle() {
		return qte != null && unite != null;
	}

	public Double getQtepBdg() {
		return getQteCum();
	}

	public Double getPucRls() {
		try {
			return (qtepRls == 0 || qtepRls == null) ? 0 : mncRls / qtepRls;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getPucBda() {
		try {
			return (qtepBda == 0 || qtepBda == null) ? 0 : mncBda / qtepBda;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getPucBdg() {
		try {
			Double qteCum = getQteCum();
			return (qteCum == 0 || qteCum == null) ? 0 : getMncBdg() / qteCum;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMnpRls() {
		try {
			return pu * qtepRls;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMnpBda() {
		try {
			return pu * qtepBda;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMnpBdg() {
		return getMontant();
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
			double result;
			if (getMnpRls() == 0) {
				result = 0;
			} else {
				result = (getMnpRls() - mncRls) / getMnpRls();
			}
			return result;
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
			double result;
			if (getMnpBda() == 0) {
				result = 0;
			} else {
				result = (getMnpBda() - mncBda) / getMnpBda();
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgBdg() {
		try {
			return getMnpBdg() - getMncBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpBdg() {
		try {
			double result;
			if (getMnpBdg() == 0) {
				result = 0;
			} else {
				result = (getMnpBdg() - getMncBdg()) / getMnpBdg();
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateFinFin() {
		try {
			return dateDbtFin.plusDays(dlaFin);
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntRef() {
		try {
			return qteRef * puRef;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRef() {
		try {
			return getMntRef() - getMncBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpRef() {
		try {
			double result;
			if (getMntRef() == 0) {
				result = 0;
			} else {
				result = (getMntRef() - getMncBdg()) / getMntRef();
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getQteRefB() {
		return getQteCum();
	}

	public Double getMntRefB() {
		try {
			return getQteRefB() * puRef;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRefB() {
		return (getMntRefB() == null ? 0 : getMntRefB()) - (getMncBdg() == null ? 0 : getMncBdg());
	}

	public Double getMrpRefB() {
		try {
			double result;
			if (getMntRefB() == 0 || getMntRefB() == null) {
				result = 0;
			} else {
				result = getMrgRefB() / getMntRef();
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getQteCum() {
		try {
			Double qteCumCalcule = 0.0;
			for (Tache tache : taches) {
				if (tache.getCleAttachement()) {
					qteCumCalcule += tache.getQtePBdg();
				}
			}
			return qteCumCalcule;
		} catch (Exception e) {
			return null;
		}
	}
	

	public Double getMncBdg() {
		try {
			Double mncBdgCalcule = 0.0;
			for (Tache tache : taches) {
				if (tache.getCleAttachement()) {
					mncBdgCalcule += tache.getMncBdg();
				}
			}
			return mncBdgCalcule;
		} catch (Exception e) {
			return null;
		}
	}
	
	public LocalDate getDateDbtIni() {
		try {
			return taches.stream()
            .map(Tache::getDateDebut)
            .filter(date -> date != null) // Filter out null dates
            .min(LocalDate::compareTo).get();
		} catch (Exception e) {
			return null;	
		}
	}
	
	public LocalDate getDateFinIni() {
		try {
			return taches.stream()
            .map(Tache::getDateFin)
            .filter(date -> date != null) // Filter out null dates
            .max(LocalDate::compareTo).get();
		} catch (Exception e) {
			return null;	
		}
	}
	
	public Integer getDlaIni() {
		try {
			if (getDateDbtIni() == null && getDateFinIni() == null)
				return 1;
			else
				return (int) (getDateFinIni().toEpochDay() - getDateDbtIni().toEpochDay() + 1);
		} catch (Exception e) {
			return null;
		}
	}
	


}
