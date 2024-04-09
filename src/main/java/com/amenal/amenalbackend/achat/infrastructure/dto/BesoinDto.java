package com.amenal.amenalbackend.achat.application.dto;

import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ChargeStandard;
import com.amenal.amenalbackend.achat.application.domain.DetailBesoin;
import com.amenal.amenalbackend.budget.application.domain.Tache;

public class BesoinDto {
	private Integer id;
	private LocalDate datePrevu;
	private Double qte;

	private ChargeStandard charge;
	private Tache tache;

	private List<DetailBesoin> detailBesoins;
	
	private Double prixUnitaire;
	private Double mntHt;
	private Double mntTva;
	private Double mntTtc;

	public BesoinDto(Integer id, LocalDate datePrevu, Double qte, ChargeStandard charge, Tache tache,
			List<DetailBesoin> detailBesoins, Double prixUnitaire, Double mntHt, Double mntTva, Double mntTtc) {
		super();
		this.id = id;
		this.datePrevu = datePrevu;
		this.qte = qte;
		this.charge = charge;
		this.tache = tache;
		this.detailBesoins = detailBesoins;
		this.prixUnitaire = prixUnitaire;
		this.mntHt = mntHt;
		this.mntTva = mntTva;
		this.mntTtc = mntTtc;
	}

	public BesoinDto() {
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

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getMntHt() {
		return mntHt;
	}

	public void setMntHt(Double mntHt) {
		this.mntHt = mntHt;
	}

	public Double getMntTva() {
		return mntTva;
	}

	public void setMntTva(Double mntTva) {
		this.mntTva = mntTva;
	}

	public Double getMntTtc() {
		return mntTtc;
	}

	public void setMntTtc(Double mntTtc) {
		this.mntTtc = mntTtc;
	}

}
