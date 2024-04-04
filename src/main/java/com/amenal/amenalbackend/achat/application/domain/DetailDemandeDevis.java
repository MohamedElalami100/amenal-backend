package com.amenal.amenalbackend.achat.application.domain;

public class DetailDemandeDevis {
	private Integer id;
	private ChargeStandard charge;
	private Double qte;
	private DemandeDevis demandeDevis;

	public DetailDemandeDevis(Integer id, ChargeStandard charge, Double qte, DemandeDevis demandeDevis) {
		super();
		this.id = id;
		this.charge = charge;
		this.qte = qte;
		this.demandeDevis = demandeDevis;
	}

	public DetailDemandeDevis() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DemandeDevis getDemandeDevis() {
		return demandeDevis;
	}

	public void setDemandeDevis(DemandeDevis demandeDevis) {
		this.demandeDevis = demandeDevis;
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

}
