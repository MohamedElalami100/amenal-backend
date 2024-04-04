package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail_demande_devis")
public class DetailDemandeDevisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_demande_devis")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_charge_standard")
    private ChargeStandardEntity charge;

    @Column(name = "qte")
    private Double qte;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande_devis")
    private DemandeDevisEntity demandeDevis;

	public DetailDemandeDevisEntity(Integer id, ChargeStandardEntity charge, Double qte,
			DemandeDevisEntity demandeDevis) {
		super();
		this.id = id;
		this.charge = charge;
		this.qte = qte;
		this.demandeDevis = demandeDevis;
	}

	public DetailDemandeDevisEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DemandeDevisEntity getDemandeDevis() {
		return demandeDevis;
	}

	public void setDemandeDevis(DemandeDevisEntity demandeDevis) {
		this.demandeDevis = demandeDevis;
	}

	public ChargeStandardEntity getCharge() {
		return charge;
	}

	public void setCharge(ChargeStandardEntity charge) {
		this.charge = charge;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

}
