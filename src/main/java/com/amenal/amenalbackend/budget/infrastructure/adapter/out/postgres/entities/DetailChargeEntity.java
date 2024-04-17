package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

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
@Table(name = "detail_charge")
public class DetailChargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_charge")
    private Integer id;

    @Column(name = "pcb")
    private Double pcb;

    @Column(name = "qcb")
    private Double qcb;

    @Column(name = "ucb")
    private String ucb;
	
    @Column(name = "charge")
    private String charge;

    @Column(name = "date_saisie")
    private LocalDate ddb;

    @Column(name = "ordre")
    private String ordre;

    @Column(name = "la_qcb_prd")
    private Double qpb;

    @Column(name = "la_ucb_prd")
    private String rcb;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nature_article")
    private NatureArticleEntity nature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tache")
    private TacheEntity tache;

	public DetailChargeEntity(Integer id, Double pcb, Double qcb, String ucb, String charge,
			LocalDate ddb, String ordre,
			Double qpb, String rcb, NatureArticleEntity nature, TacheEntity tache) {
		super();
		this.id = id;
		this.pcb = pcb;
		this.qcb = qcb;
		this.ucb = ucb;
		this.charge = charge;
		this.ddb = ddb;
		this.ordre = ordre;
		this.qpb = qpb;
		this.rcb = rcb;
		this.nature = nature;
		this.tache = tache;
	}

	public DetailChargeEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPcb() {
		return pcb;
	}

	public void setPcb(Double pcb) {
		this.pcb = pcb;
	}

	public Double getQcb() {
		return qcb;
	}

	public void setQcb(Double qcb) {
		this.qcb = qcb;
	}

	public String getUcb() {
		return ucb;
	}

	public void setUcb(String ucb) {
		this.ucb = ucb;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public Double getQpb() {
		return qpb;
	}

	public void setQpb(Double qpb) {
		this.qpb = qpb;
	}

	public String getRcb() {
		return rcb;
	}

	public void setRcb(String rcb) {
		this.rcb = rcb;
	}

	public NatureArticleEntity getNature() {
		return nature;
	}

	public void setNature(NatureArticleEntity nature) {
		this.nature = nature;
	}

	public TacheEntity getTache() {
		return tache;
	}

	public void setTache(TacheEntity tache) {
		this.tache = tache;
	}

	// business methods:
	public Double getMontant() {
		try {
			return pcb * qcb;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMontantAchat() {
		try {
			return pcb * qcb;
		} catch (Exception e) {
			return null;
		}
	}

	public String getLienHhd() {
		try {
			return tache.getId() + "$" + charge.toUpperCase() + Math.round(pcb * 100.0) / 100.0
					+ ucb.toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

}
