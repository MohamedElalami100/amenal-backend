package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

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
@Table(name = "detail_devis")
public class DetailDevisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_devis")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_charge_standard")
    private ChargeStandardEntity charge;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devis")
    private DevisEntity devis;
   
	public DetailDevisEntity(Integer id, ChargeStandardEntity charge, Double qte, Double prixUnitaire,
			DevisEntity devis) {
		super();
		this.id = id;
		this.charge = charge;
		this.qte = qte;
		this.prixUnitaire = prixUnitaire;
		this.devis = devis;
	}

	public DetailDevisEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public DevisEntity getDevis() {
		return devis;
	}

	public void setDevis(DevisEntity devis) {
		this.devis = devis;
	}
}
