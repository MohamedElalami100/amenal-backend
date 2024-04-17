package com.amenal.amenalbackend.budget.core.domain;

import java.util.List;

public class DetailProduitAttente {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private String reference;
	private Double nbr;
	private Double dim1;
	private Double dim2;
	private Double dim3;
	private Double rls;
	private String erreur;
	private MetreAv metre;
	
	public DetailProduitAttente(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String reference, Double nbr, Double dim1, Double dim2, Double dim3, Double rls, String erreur, MetreAv metre) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.reference = reference;
		this.nbr = nbr;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.rls = rls;
		this.erreur = erreur;
		this.metre = metre;
	}

	public DetailProduitAttente() {
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getNbr() {
		return nbr;
	}

	public void setNbr(Double nbr) {
		this.nbr = nbr;
	}

	public Double getDim1() {
		return dim1;
	}

	public void setDim1(Double dim1) {
		this.dim1 = dim1;
	}

	public Double getDim2() {
		return dim2;
	}

	public void setDim2(Double dim2) {
		this.dim2 = dim2;
	}

	public Double getDim3() {
		return dim3;
	}

	public void setDim3(Double dim3) {
		this.dim3 = dim3;
	}

	public Double getRls() {
		return rls;
	}

	public void setRls(Double rls) {
		this.rls = rls;
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
											   List<DetailProduitAttente> otherDetailsInAttente,
											   List<DetailProduit> otherDetailProduits, boolean skipTacheError) {
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

			for (DetailProduitAttente detail : otherDetailsInAttente) {
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

		try {
			// check if a one of the last fields is null or contain an empty string
			erreur = (this.getProduit() == null || this.getProduit().isEmpty()
					? "| Produit Vide "
					: "")
					+ (this.getNbr() == null || this.getNbr() == 0 ? "| Nbr null "
					: "");
			if (!erreur.isEmpty())
				return "(7)" + erreur;
		} catch (NullPointerException e) {
		}

		try {
			for (DetailProduitAttente detail : otherDetailsInAttente) {
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

				DetailProduit detailProduit = new DetailProduit();
				detailProduit.setTache(tache);
				detailProduit.setReference(detail.getReference());

				otherDetailProduits.add(detailProduit);

			}
		} catch (NullPointerException e) {
		}

		// DOUBLONS DE LIGNES
		for (DetailProduit detailProduit : otherDetailProduits) {
			try {
				if (detailProduit.getTache().getTitreActivite().equalsIgnoreCase(this.getActivite())
						&& detailProduit.getTache().getLot().getDesignation()
						.equalsIgnoreCase(this.getLot())
						&& detailProduit.getTache().getProduit().getDesignation()
						.equalsIgnoreCase(this.getProduit())
						&& detailProduit.getReference().equalsIgnoreCase(this.getReference())) {
					return "(8)DOUBLONS DE LIGNES";
				}
			} catch (NullPointerException e) {
			}
		}

		// RCT
		return "(9)RCT";

	}
	
}
