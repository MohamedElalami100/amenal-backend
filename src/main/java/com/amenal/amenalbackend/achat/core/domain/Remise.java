package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;

public class Remise {
	private Integer id;
	private LocalDate dateRemise;
	private String remiseEn;
	private String remiseA;
	private String remiseVia;
	private String lienPhotoRemise;

	private Paiement paiement;

	public Remise() {
		super();
	}

	public Remise(Integer id, LocalDate dateRemise, String remiseEn, String remiseA, String remiseVia,
			String lienPhotoRemise, Paiement paiement) {
		super();
		this.id = id;
		this.dateRemise = dateRemise;
		this.remiseEn = remiseEn;
		this.remiseA = remiseA;
		this.remiseVia = remiseVia;
		this.lienPhotoRemise = lienPhotoRemise;
		this.paiement = paiement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(LocalDate dateRemise) {
		this.dateRemise = dateRemise;
	}

	public String getRemiseEn() {
		return remiseEn;
	}

	public void setRemiseEn(String remiseEn) {
		this.remiseEn = remiseEn;
	}

	public String getRemiseA() {
		return remiseA;
	}

	public void setRemiseA(String remiseA) {
		this.remiseA = remiseA;
	}

	public String getRemiseVia() {
		return remiseVia;
	}

	public void setRemiseVia(String remiseVia) {
		this.remiseVia = remiseVia;
	}

	public String getLienPhotoRemise() {
		return lienPhotoRemise;
	}

	public void setLienPhotoRemise(String lienPhotoRemise) {
		this.lienPhotoRemise = lienPhotoRemise;
	}

	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

}
