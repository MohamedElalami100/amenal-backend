package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;
import java.util.List;

public class Reception {
	private Integer id;
	private LocalDate dateRcf;
	private String reference;
	private String lienImageBonLivraison;

	private Commande commande;
	private List<Transport> transports;
	private List<DetailReception> detailReceptions;

	public Reception(Integer id, LocalDate dateRcf, String reference, String lienImageBonLivraison, Commande commande,
					 List<Transport> transports, List<DetailReception> detailReceptions) {
		super();
		this.id = id;
		this.dateRcf = dateRcf;
		this.reference = reference;
		this.lienImageBonLivraison = lienImageBonLivraison;
		this.commande = commande;
		this.transports = transports;
		this.detailReceptions = detailReceptions;
	}

	public Reception() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateRcf() {
		return dateRcf;
	}

	public void setDateRcf(LocalDate dateRcf) {
		this.dateRcf = dateRcf;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLienImageBonLivraison() {
		return lienImageBonLivraison;
	}

	public void setLienImageBonLivraison(String lienImageBonLivraison) {
		this.lienImageBonLivraison = lienImageBonLivraison;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<Transport> getTransports() {
		return transports;
	}

	public void setTransports(List<Transport> transports) {
		this.transports = transports;
	}

	public List<DetailReception> getDetailReceptions() {
		return detailReceptions;
	}

	public void setDetailReceptions(List<DetailReception> detailReceptions) {
		this.detailReceptions = detailReceptions;
	}

	// business methods:
	public Double getMntHt() {
		try {
			return detailReceptions.stream().mapToDouble(obj -> obj.getMntHt() != null ? obj.getMntHt() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return detailReceptions.stream().mapToDouble(obj -> obj.getMntTva() != null ? obj.getMntTva() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return detailReceptions.stream().mapToDouble(obj -> obj.getMntTtc() != null ? obj.getMntTtc() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}
}
