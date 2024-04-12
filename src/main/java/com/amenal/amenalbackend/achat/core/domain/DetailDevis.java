package com.amenal.amenalbackend.achat.core.domain;

public class DetailDevis {
	private Integer id;
	private ChargeStandard charge;
	private Double qte;
	private Double prixUnitaire;
	public DetailDevis(Integer id, ChargeStandard charge, Double qte, Double prixUnitaire) {
		super();
		this.id = id;
		this.charge = charge;
		this.qte = qte;
		this.prixUnitaire = prixUnitaire;
	}

	public DetailDevis() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChargeStandard getCharge() {
		return charge;
	}

	public void setCharge(ChargeStandard charge) {
		this.charge = charge;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	// business methods:
	public Double getMntHt() {
		try {
			return prixUnitaire * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return getMntHt() * 0.2;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return getMntHt() * 1.2;
		} catch (Exception e) {
			return null;
		}
	}
}
