package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.utils.core.domain.Colorable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetailCharge extends Colorable {
	private Integer id;
	private Double pcb;
	private Double qcb;
	private String ucb;
	private String charge;
	private String nature;
	private LocalDate ddb;
	private String ordre;
	private Double qpb;
	private String rcb;
	// Fk objects:
	private Tache tache;

	public DetailCharge(Integer id, Double pcb, Double qcb, String ucb, String charge,
			LocalDate ddb, String ordre,
			Double qpb, String rcb, String nature, Tache tache) {
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

	@Override
	public List<List<Colorable>> getSons() {
		return new ArrayList<>();
	}

	@Override
	public List<String> getErrors() {
		return new ArrayList<>();
	}

	public DetailCharge() {
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
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

}
