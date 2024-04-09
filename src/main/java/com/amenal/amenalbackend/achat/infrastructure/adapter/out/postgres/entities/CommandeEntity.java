package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commande")
public class CommandeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private Integer id;

    @Column(name = "date_cmf")
    private LocalDate dateCmf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_besoin")
    private BesoinEntity besoin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devis")
    private DevisEntity devis;

    @OneToMany(mappedBy = "commande")
    private List<ContactLivraisonEntity> contacts;

	public CommandeEntity(Integer id, LocalDate dateCmf, BesoinEntity besoin, DevisEntity devis, List<ContactLivraisonEntity> contacts) {
		super();
		this.id = id;
		this.dateCmf = dateCmf;
		this.besoin = besoin;
		this.devis = devis;
		this.contacts = contacts;
	}

	public CommandeEntity() {
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

	public BesoinEntity getBesoin() {
		return besoin;
	}

	public void setBesoin(BesoinEntity besoin) {
		this.besoin = besoin;
	}

	public DevisEntity getDevis() {
		return devis;
	}

	public void setDevis(DevisEntity devis) {
		this.devis = devis;
	}

	public List<ContactLivraisonEntity> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactLivraisonEntity> contacts) {
		this.contacts = contacts;
	}

	// business methods:
	public List<DetailDevisEntity> getDetailCommmande() {
		try {
			return devis.getDetailDevis();
		} catch (Exception e) {
			return null;
		}
	}

}
