package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;
import java.util.List;

public class Devis {
	private Integer id;
	private LocalDate dateDvf;
	private String reference;

	private Fournisseur fournisseur;

	private List<DetailDevis> detailDevis;

	public Devis(Integer id, LocalDate dateDvf, String reference, Fournisseur fournisseur,
			List<DetailDevis> detailDevis) {
		super();
		this.id = id;
		this.dateDvf = dateDvf;
		this.reference = reference;
		this.fournisseur = fournisseur;
		this.detailDevis = detailDevis;
	}

	public Devis() {
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

	public List<DetailDevis> getDetailDevis() {
		return detailDevis;
	}

	public void setDetailDevis(List<DetailDevis> detailDevis) {
		this.detailDevis = detailDevis;
	}

	// business methods:
	public Double getMntHt() {
		try {
			return detailDevis.stream().mapToDouble(obj -> obj.getMntHt() != null ? obj.getMntHt() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return detailDevis.stream().mapToDouble(obj -> obj.getMntTva() != null ? obj.getMntTva() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return detailDevis.stream().mapToDouble(obj -> obj.getMntTtc() != null ? obj.getMntTtc() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}
}
