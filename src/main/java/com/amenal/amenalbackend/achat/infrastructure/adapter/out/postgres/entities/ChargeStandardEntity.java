package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "charge_standard")
public class ChargeStandardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_charge_standard")
    private Integer id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "emmagasinable")
    private Boolean emmagasinable;

    @Column(name = "recupable")
    private Boolean recupable;

    @Column(name = "transportable")
    private Boolean transportable;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "tva")
    private Double tva;

    @Column(name = "is_bloque")
    private Boolean isBloque;

    @Column(name = "is_archive")
    private Boolean isArchive;

	public ChargeStandardEntity(Integer id, String designation, Boolean emmagasinable, Boolean recupable,
			Boolean transportable, Double prixUnitaire, Double tva, Boolean isBloque, Boolean isArchive) {
		super();
		this.id = id;
		this.designation = designation;
		this.emmagasinable = emmagasinable;
		this.recupable = recupable;
		this.transportable = transportable;
		this.prixUnitaire = prixUnitaire;
		this.tva = tva;
		this.isBloque = isBloque;
		this.isArchive = isArchive;
	}

	public ChargeStandardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Boolean getEmmagasinable() {
		return emmagasinable;
	}

	public void setEmmagasinable(Boolean emmagasinable) {
		this.emmagasinable = emmagasinable;
	}

	public Boolean getRecupable() {
		return recupable;
	}

	public void setRecupable(Boolean recupable) {
		this.recupable = recupable;
	}

	public Boolean getTransportable() {
		return transportable;
	}

	public void setTransportable(Boolean transportable) {
		this.transportable = transportable;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public Boolean getIsBloque() {
		return isBloque;
	}

	public void setIsBloque(Boolean isBloque) {
		this.isBloque = isBloque;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	//business methods:
	public Double getPrixUnitaireTtc() {
		try {
			return prixUnitaire * (1 + tva / 100);
		} catch (Exception e) {
			return null;
		}
	}

}
