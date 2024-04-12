package com.amenal.amenalbackend.achat.core.domain;

public class DetailDemandeDevis {
	private Integer id;
	private ChargeStandard charge;
	private Double qte;
	public DetailDemandeDevis(Integer id, ChargeStandard charge, Double qte) {
		super();
		this.id = id;
		this.charge = charge;
		this.qte = qte;
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
