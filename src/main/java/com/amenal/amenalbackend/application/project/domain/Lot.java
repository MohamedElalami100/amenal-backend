package com.amenal.amenalbackend.application.project.domain;

import java.time.LocalDate;
import java.util.List;

//budgetAchatDétailAv in Microsoft access database
public class Lot {
	private Integer id;
	private String nom;
	private Double pu;
	private Double qte;
	private String unite;
	private String designation;
	private String article;
	private Integer delaiReel;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private LocalDate dateSaisie;
	private Boolean affiche;
	private Boolean affecte;
	private Double tocRls;
	private Double topRls;
	private Double tocBda;
	private Double topBda;
	private Double tocBdg;
	private Double topBdg;
	private Integer idAnien;
	private Boolean choisi;
	private Boolean maj;
	private String descriptif;
	private String ordreMef;
	private Integer ordre;
	private Double mntRef;
	// Fk objects:
	private ProduitStandard decompositionStandard;
	private Project project;
	private List<Tache> taches;

	public Lot(Integer id, String nom, Double pu, Double qte, String unite, String designation, String article,
			Integer delaiReel, LocalDate dateDebut, LocalDate dateFin, LocalDate dateSaisie, Boolean affiche,
			Boolean affecte, Double tocRls, Double topRls, Double tocBda, Double topBda, Double tocBdg, Double topBdg,
			Integer idAnien, Boolean choisi, Boolean maj, String descriptif, String ordreMef, Integer ordre,
			Double mntRef, ProduitStandard decompositionStandard, Project project, List<Tache> taches) {
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
		this.taches = taches;
	}

	public Lot() {
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

	public ProduitStandard getDecompositionStandard() {
		return decompositionStandard;
	}

	public void setDecompositionStandard(ProduitStandard decompositionStandard) {
		this.decompositionStandard = decompositionStandard;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	//business methods:
	public Double getMntRefB() {
		try {
			Double mntRefCalcule = 0.0;
			for (Tache tache : taches) {
				if (tache.getCleAttachement()) {
					mntRefCalcule += tache.getMntRefB();
				}
			}
			return mntRefCalcule;
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

	public Double getMrgRefB() {
		return (getMntRefB() == null ? 0 : getMntRefB()) - (getMncBdg() == null ? 0 : getMncBdg());
	}

	public Double getMrpRefB() {
		try {
			double result;
			if (getMntRefB() == 0 || getMntRefB() == null) {
				result = 0;
			} else {
				result = getMrgRefB() / getMntRefB();
			}
			return result;
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
