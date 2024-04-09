package com.amenal.amenalbackend.achat.infrastructure.dto;

import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Fournisseur;

public class DevisDto {
	private Integer id;
	private LocalDate dateDvf;
	private String reference;

	private Fournisseur fournisseur;

	private List<DetailDevisDto> detailDevis;
	
	private Double mntHt;
	private Double mntTva;
	private Double mntTtc;

	
	public DevisDto(Integer id, LocalDate dateDvf, String reference, Fournisseur fournisseur,
			List<DetailDevisDto> detailDevis, Double mntHt, Double mntTva, Double mntTtc) {
		super();
		this.id = id;
		this.dateDvf = dateDvf;
		this.reference = reference;
		this.fournisseur = fournisseur;
		this.detailDevis = detailDevis;
		this.mntHt = mntHt;
		this.mntTva = mntTva;
		this.mntTtc = mntTtc;
	}

	public DevisDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDvf() {
		return dateDvf;
	}

	public void setDateDvf(LocalDate dateDvf) {
		this.dateDvf = dateDvf;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<DetailDevisDto> getDetailDevis() {
		return detailDevis;
	}

	public void setDetailDevis(List<DetailDevisDto> detailDevis) {
		this.detailDevis = detailDevis;
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
