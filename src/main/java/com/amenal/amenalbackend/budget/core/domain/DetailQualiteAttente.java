package com.amenal.amenalbackend.budget.core.domain;

import java.util.List;

public class DetailQualiteAttente {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private String groupe;
	private String pointDeControle;
	private String erreur;
	private MetreAv metre;

	public DetailQualiteAttente(Integer id, String ordre, String produit, String lot, String activite, String upb,
			Boolean cle,
			String groupe, String pointDeControle, String erreur, MetreAv metre) {
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

	public DetailQualiteAttente() {
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
			List<DetailQualiteAttente> otherDetailsInAttente,
			List<DetailQualite> otherDetailQualites, boolean skipTacheError) {
		// set error message:
		if (!skipTacheError) {
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

			for (DetailQualiteAttente detail : otherDetailsInAttente) {
				Produit produit = new Produit();
				produit.setDesignation(detail.getProduit());

				Lot lot = new Lot();
				lot.setDesignation(detail.getLot());

				Tache tache = new Tache();
				tache.setUpb(detail.getOrdre());
				tache.setTitreActivite(detail.getActivite());
				tache.setProduit(produit);
				tache.setLot(lot);
				tache.setUpb(detail.getUpb());
				tache.setCle(detail.getCle());

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
							&& !tache.getUpb().equalsIgnoreCase(this.getUpb())) {
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
							&& tache.getCle() != this.getCle()) {
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
								if (tache.getCle()) {
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

		// check if a one of the last fields is null or contain an empty string
		erreur = (this.getGroupe() == null || this.getGroupe().isEmpty() ? "| Groupe Vide "
				: "")
				+ (this.getPointDeControle() == null || this.getPointDeControle().isEmpty()
						? "| Point de controle Vide "
						: "");
		if (!erreur.isEmpty())
			return "(7)" + erreur;

		for (DetailQualiteAttente detail : otherDetailsInAttente) {
			Produit produit = new Produit();
			produit.setDesignation(detail.getProduit());

			Lot lot = new Lot();
			lot.setDesignation(detail.getLot());

			Tache tache = new Tache();
			tache.setOrdre(detail.getOrdre());
			tache.setTitreActivite(detail.getActivite());
			tache.setProduit(produit);
			tache.setLot(lot);
			tache.setUpb(detail.getUpb());
			tache.setCle(detail.getCle());

			GrpQualite groupe = new GrpQualite();
			groupe.setTitre(detail.getGroupe());
			groupe.setTache(tache);

			DetailQualite detailQualite = new DetailQualite();
			detailQualite.setPointDeControle(detail.getPointDeControle());
			detailQualite.setGroupe(groupe);

			otherDetailQualites.add(detailQualite);

		}

		// DOUBLONS DE LIGNES
		for (DetailQualite detailQualite : otherDetailQualites) {
			try {
				if (detailQualite.getGroupe().getTache().getTitreActivite()
						.equalsIgnoreCase(this.getActivite())
						&& detailQualite.getGroupe().getTache().getLot().getDesignation()
								.equalsIgnoreCase(this.getLot())
						&& detailQualite.getGroupe().getTache().getProduit().getDesignation()
								.equalsIgnoreCase(this.getProduit())
						&& detailQualite.getGroupe().getTitre().equalsIgnoreCase(this.getGroupe())
						&& detailQualite.getPointDeControle().equalsIgnoreCase(this.getPointDeControle())) {
					return "(8)DOUBLONS DE LIGNES";
				}
			} catch (NullPointerException e) {
			}
		}
		// RCT
		return "(9)RCT";

	}
}
