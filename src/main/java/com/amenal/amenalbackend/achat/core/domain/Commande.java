package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;
import java.util.List;

public class Commande {
	private Integer id;
	private LocalDate dateCmf;

	private Besoin besoin;
	private Devis devis;
	private List<ContactLivraison> contacts;

	public Commande(Integer id, LocalDate dateCmf, Besoin besoin, Devis devis, List<ContactLivraison> contacts) {
		super();
		this.id = id;
		this.dateCmf = dateCmf;
		this.besoin = besoin;
		this.devis = devis;
		this.contacts = contacts;
	}

	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateCmf() {
		return dateCmf;
	}

	public void setDateCmf(LocalDate dateCmf) {
		this.dateCmf = dateCmf;
	}

	public Besoin getBesoin() {
		return besoin;
	}

	public void setBesoin(Besoin besoin) {
		this.besoin = besoin;
	}

	public Devis getDevis() {
		return devis;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
	}

	public List<ContactLivraison> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactLivraison> contacts) {
		this.contacts = contacts;
	}

}
