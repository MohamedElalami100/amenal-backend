package com.amenal.amenalbackend.adapter.project.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "detail_charge_attente")
public class DetailChargeAttenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ordre")
    private String ordre;

    @Column(name = "produit")
    private String produit;

    @Column(name = "lot")
    private String lot;

    @Column(name = "activite")
    private String activite;

    @Column(name = "upb")
    private String upb;

    @Column(name = "cle")
    private Boolean cle;

    @Column(name = "charge")
    private String charge;

    @Column(name = "nature")
    private String nature;

    @Column(name = "ucb")
    private String ucb;

    @Column(name = "qcb")
    private Double qcb;

    @Column(name = "pcb")
    private Double pcb;

    @Column(name = "mcb")
    private Double mcb;

    @Column(name = "rcb")
    private String rcb;

    @Column(name = "qpb")
    private Double qpb;

    @Column(name = "ddb")
    private LocalDate ddb;

    @Column(name = "erreur")
    private String erreur;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metre_av")
    private MetreAvEntity metre;

	public DetailChargeAttenteEntity() {
		super();
	}

	public DetailChargeAttenteEntity(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String charge, String nature, String ucb, Double qcb, Double pcb, Double mcb, String rcb, Double qpb,
			LocalDate ddb, String erreur, MetreAvEntity metre) {
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

	public MetreAvEntity getMetre() {
		return metre;
	}

	public void setMetre(MetreAvEntity metre) {
		this.metre = metre;
	}

}
