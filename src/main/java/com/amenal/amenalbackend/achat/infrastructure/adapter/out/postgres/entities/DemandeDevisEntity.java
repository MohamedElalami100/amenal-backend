package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "demande_devis")
public class DemandeDevisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande_devis")
    private Integer id;

    @Column(name = "date_ddf")
    private LocalDate dateDdf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fournisseur")
	private FournisseurEntity fournisseur;

    @OneToMany(mappedBy = "demandeDevis")
    private List<DetailDemandeDevisEntity> detailDemandeDevis;

	public DemandeDevisEntity(Integer id, LocalDate dateDdf, FournisseurEntity fournisseur,
			List<DetailDemandeDevisEntity> detailDemandeDevis) {
		super();
		this.id = id;
		this.dateDdf = dateDdf;
		this.fournisseur = fournisseur;
		this.detailDemandeDevis = detailDemandeDevis;
	}

	public DemandeDevisEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDdf() {
		return dateDdf;
	}

	public void setDateDdf(LocalDate dateDdf) {
		this.dateDdf = dateDdf;
	}

	public FournisseurEntity getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurEntity fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<DetailDemandeDevisEntity> getDetailDemandeDevis() {
		return detailDemandeDevis;
	}

	public void setDetailDemandeDevis(List<DetailDemandeDevisEntity> detailDemandeDevis) {
		this.detailDemandeDevis = detailDemandeDevis;
	}

}
