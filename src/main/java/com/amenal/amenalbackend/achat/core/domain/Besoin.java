package com.amenal.amenalbackend.achat.application.domain;

import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.Tache;

public class Besoin {
	private Integer id;
	private LocalDate datePrevu;
	private Double qte;

	private ChargeStandard charge;
	private Tache tache;

	private List<DetailBesoin> detailBesoins;

	public Besoin(Integer id, LocalDate datePrevu, Double qte, ChargeStandard charge, Tache tache,
			List<DetailBesoin> detailBesoins) {
		super();
		this.id = id;
		this.datePrevu = datePrevu;
		this.qte = qte;
		this.charge = charge;
		this.tache = tache;
		this.detailBesoins = detailBesoins;
	}

	public Besoin() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatePrevu() {
		return datePrevu;
	}

	public void setDatePrevu(LocalDate datePrevu) {
		this.datePrevu = datePrevu;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public ChargeStandard getCharge() {
		return charge;
	}

	public void setCharge(ChargeStandard charge) {
		this.charge = charge;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public List<DetailBesoin> getDetailBesoins() {
		return detailBesoins;
	}

	public void setDetailBesoins(List<DetailBesoin> detailBesoins) {
		this.detailBesoins = detailBesoins;
	}

	// business methods:
	public Double getPrixUnitaireHT() {
		try {
			return charge.getPrixUnitaire();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntHt() {
		try {
			return getPrixUnitaireHT() * qte;
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
