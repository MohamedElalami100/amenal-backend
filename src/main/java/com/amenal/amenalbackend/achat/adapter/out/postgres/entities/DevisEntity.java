package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

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
@Table(name = "devis")
public class DevisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devis")
    private Integer id;

    @Column(name = "date_dvf")
    private LocalDate dateDvf;

    @Column(name = "reference")
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fournisseur")
    private FournisseurEntity fournisseur;

    @OneToMany(mappedBy = "devis")
    private List<DetailDevisEntity> detailDevis;

	public DevisEntity(Integer id, LocalDate dateDvf, String reference, FournisseurEntity fournisseur,
			List<DetailDevisEntity> detailDevis) {
		super();
		this.id = id;
		this.dateDvf = dateDvf;
		this.reference = reference;
		this.fournisseur = fournisseur;
		this.detailDevis = detailDevis;
	}

	public DevisEntity() {
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

	public FournisseurEntity getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurEntity fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<DetailDevisEntity> getDetailDevis() {
		return detailDevis;
	}

	public void setDetailDevis(List<DetailDevisEntity> detailDevis) {
		this.detailDevis = detailDevis;
	}
}
