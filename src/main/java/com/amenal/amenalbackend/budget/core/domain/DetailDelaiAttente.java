package com.amenal.amenalbackend.budget.core.domain;

import java.time.LocalDate;
import java.util.List;

public class DetailDelaiAttente {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;
	private String erreur;
	private MetreAv metre;
	
	public DetailDelaiAttente(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			LocalDate ddb, Integer dlb, LocalDate dfb, String erreur, MetreAv metre) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
		this.erreur = erreur;
		this.metre = metre;
	}

	public DetailDelaiAttente() {
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

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public Integer getDlb() {
		return dlb;
	}

	public void setDlb(Integer dlb) {
		this.dlb = dlb;
	}

	public LocalDate getDfb() {
		return dfb;
	}

	public void setDfb(LocalDate dfb) {
		this.dfb = dfb;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public MetreAv getMetre() {
		return metre;
	}

	public void setAvenant(MetreAv metre) {
		this.metre = metre;
	}

	public void setMetre(MetreAv metre) {
		this.metre = metre;
	}

	public String getAndCalculateErreurMessage(List<Tache> tachesInOtherAvenants, List<Tache> tachesInSameAvenants,
											   List<DetailDelaiAttente> otherDetailsInAttente,
											   boolean skipTacheError) {
		// set error message:
		if (!skipTacheError){
			// check if a one of the first fields is null or contain an empty string
			String erreur = "";
			try {
				erreur = (this.getProduit() == null || this.getProduit().isEmpty()
						? "| Produit Vide "
						: "")
						+ (this.getLot() == null || this.getLot().isEmpty() ? "| Lot Vide "
						: "")
						+ (this.getActivite() == null || this.getActivite() == ""
						? "| Activite Vide "
						: "")
						+ (this.getUpb() == null || this.getUpb().isEmpty() ? "| Unite Vide "
						: "");
				if (!erreur.isEmpty())
					return "(1)" + erreur;
			} catch (NullPointerException e) {
			}
			try {
				// TACHE EXISTE DANS UN AUTRE AVENANT
				for (Tache tache : tachesInOtherAvenants) {
					if (tache.getTitreActivite().equalsIgnoreCase(this.getActivite())
							&& tache.getLot().getDesignation().equalsIgnoreCase(this.getLot())) {
						return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
					}
				}
			} catch (NullPointerException e) {
			}

			for (DetailDelaiAttente detail : otherDetailsInAttente) {
				Produit produit = new Produit();
				produit.setDesignation(detail.getProduit());

				Lot lot = new Lot();
				lot.setDesignation(detail.getLot());

				Tache tache = new Tache();
				tache.setOrdreMef(detail.getOrdre());
				tache.setTitreActivite(detail.getActivite());
				tache.setProduit(produit);
				tache.setLot(lot);
				tache.setUnite(detail.getUpb());
				tache.setCleAttachement(detail.getCle());

				tachesInSameAvenants.add(tache);
			}
			// TACHE LIEE A DEUX PRODUITS DIFFERENTS
			for (Tache tache : tachesInSameAvenants) {
				try {
					if (tache.getTitreActivite().equalsIgnoreCase(this.getActivite())
							&& tache.getLot().getDesignation().equalsIgnoreCase(this.getLot())
							&& !tache.getProduit().getDesignation().equalsIgnoreCase(this.getProduit())) {
						return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
					}
				} catch (NullPointerException e) {
				}
			}

			// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
			for (Tache tache : tachesInSameAvenants) {
				try {
					if (tache.getTitreActivite().equalsIgnoreCase(this.getActivite())
							&& tache.getLot().getDesignation().equalsIgnoreCase(this.getLot())
							&& tache.getProduit().getDesignation().equalsIgnoreCase(this.getProduit())
							&& !tache.getUnite().equalsIgnoreCase(this.getUpb())) {
						return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
					}
				} catch (NullPointerException e) {
				}
			}
			// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
			for (Tache tache : tachesInSameAvenants) {
				try {
					if (tache.getTitreActivite().equalsIgnoreCase(this.getActivite())
							&& tache.getLot().getDesignation().equalsIgnoreCase(this.getLot())
							&& tache.getProduit().getDesignation().equalsIgnoreCase(this.getProduit())
							&& tache.getCleAttachement() != this.getCle()) {
						return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
					}
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
			try {
				// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
				Boolean activitePrincipaleExist = false;
				if (!this.getCle()) {
					for (Tache tache : tachesInSameAvenants) {
						try {
							if (tache.getTitreActivite().equalsIgnoreCase(this.getActivite())
									&& tache.getLot().getDesignation().equalsIgnoreCase(this.getLot())
									&& tache.getProduit().getDesignation()
									.equalsIgnoreCase(this.getProduit())) {
								if (tache.getCleAttachement()) {
									activitePrincipaleExist = true;
								}
							}
						} catch (NullPointerException e) {
						}
					}
				} else {
					activitePrincipaleExist = true;
				}
				if (!activitePrincipaleExist)
					return "(6)TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE";
			} catch (NullPointerException e) {
			}
		}

		try {
			// check if a one of the last fields is null or contain an empty string
			erreur = (this.getDdb() == null ? "| Ddb Vide " : "")
					+ (this.getDlb() == null || this.getDlb() == 0 ? "| Delai null " : "");
			if (!erreur.isEmpty())
				return "(7)" + erreur;
		} catch (NullPointerException e) {

		}
		// DOUBLONS DE LIGNES
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(this.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(this.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(this.getProduit())) {
					return "(8)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
				}
			} catch (NullPointerException e) {

			}
		}

		// RCT
		return "(9)RCT";

	}
	
}
