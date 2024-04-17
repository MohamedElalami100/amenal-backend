package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailChargeAttente {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private String charge;
	private String nature;
	private String ucb;
	private Double qcb;
	private Double pcb;
	private Double mcb;
	private String rcb;
	private Double qpb;
	private LocalDate ddb;
	private String erreur;
	private MetreAv metre;

	public DetailChargeAttente() {
		super();
	}

	public DetailChargeAttente(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String charge, String nature, String ucb, Double qcb, Double pcb, Double mcb, String rcb, Double qpb,
			LocalDate ddb, String erreur, MetreAv metre) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.charge = charge;
		this.nature = nature;
		this.ucb = ucb;
		this.qcb = qcb;
		this.pcb = pcb;
		this.mcb = mcb;
		this.rcb = rcb;
		this.qpb = qpb;
		this.ddb = ddb;
		this.erreur = erreur;
		this.metre = metre;
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

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getUcb() {
		return ucb;
	}

	public void setUcb(String ucb) {
		this.ucb = ucb;
	}

	public Double getQcb() {
		return qcb;
	}

	public void setQcb(Double qcb) {
		this.qcb = qcb;
	}

	public Double getPcb() {
		return pcb;
	}

	public void setPcb(Double pcb) {
		this.pcb = pcb;
	}

	public Double getMcb() {
		return mcb;
	}

	public void setMcb(Double mcb) {
		this.mcb = mcb;
	}

	public String getRcb() {
		return rcb;
	}

	public void setRcb(String rcb) {
		this.rcb = rcb;
	}

	public Double getQpb() {
		return qpb;
	}

	public void setQpb(Double qpb) {
		this.qpb = qpb;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
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

	public String getAndCalculateErreurMessage(List<Tache> tachesInOtherAvenants,List<Tache> tachesInSameAvenants,
								   List<DetailChargeAttente> otherDetailsInAttente,
									List<DetailCharge> otherDetailCharges, boolean skipTacheError) {
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

			for (DetailChargeAttente detail : otherDetailsInAttente) {
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

		try {
			// check if a one of the last fields is null or contain an empty string
			erreur = (this.getCharge() == null || this.getCharge().isEmpty()
					? "| Charge Vide "
					: "")
					+ (this.getUcb() == null || this.getUcb().isEmpty()
					? "| Unite Charge Vide "
					: "")
					+ (this.getQcb() == null || this.getQcb() == 0 ? "| Qcb null " : "")
					+ (this.getPcb() == null || this.getPcb() == 0 ? "| Pcb null " : "");
			if (!erreur.isEmpty())
				return "(7)" + erreur;
		} catch (NullPointerException e) {
		}

		for (DetailChargeAttente detail : otherDetailsInAttente) {
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

			DetailCharge detailCharge = new DetailCharge();
			detailCharge.setTache(tache);
			detailCharge.setCharge(detail.getCharge());
			detailCharge.setUcb(detail.getUcb());

			otherDetailCharges.add(detailCharge);

		}

		// DOUBLONS DE LIGNES
		for (DetailCharge detailCharge : otherDetailCharges) {
			try {
				if (detailCharge.getTache().getTitreActivite().equalsIgnoreCase(this.getActivite())
						&& detailCharge.getTache().getLot().getDesignation()
						.equalsIgnoreCase(this.getLot())
						&& detailCharge.getTache().getProduit().getDesignation()
						.equalsIgnoreCase(this.getProduit())
						&& detailCharge.getCharge().equalsIgnoreCase(this.getCharge())) {
					return "(8)DOUBLONS DE LIGNES";
				}
			} catch (NullPointerException e) {
			}
		}
		// RCT
		return "(9)RCT";

	}

}
