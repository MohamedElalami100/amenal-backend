package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paiement")
public class PaiementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private Integer id;

    @Column(name = "date_pmf")
    private LocalDate datePmf;

    @Column(name = "ref_operation")
    private String refOperation;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "lien_photo_plf")
    private String lienPhotoPlf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compte_debite")
    private CompteBanquaireEntity compteDebite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fournisseur")
    private FournisseurEntity fournisseur;

    @ManyToMany(mappedBy = "paiements")
    private List<FactureEntity> factures;

    @OneToMany(mappedBy = "paiement")
    private List<RemiseEntity> remises;

	public PaiementEntity() {
		super();
	}

	public PaiementEntity(Integer id, LocalDate datePmf, String refOperation, Double montant, String lienPhotoPlf,
			CompteBanquaireEntity compteDebite, FournisseurEntity fournisseur, List<FactureEntity> factures, List<RemiseEntity> remises) {
		super();
		this.id = id;
		this.datePmf = datePmf;
		this.refOperation = refOperation;
		this.montant = montant;
		this.lienPhotoPlf = lienPhotoPlf;
		this.compteDebite = compteDebite;
		this.fournisseur = fournisseur;
		this.factures = factures;
		this.remises = remises;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatePmf() {
		return datePmf;
	}

	public void setDatePmf(LocalDate datePmf) {
		this.datePmf = datePmf;
	}

	public String getRefOperation() {
		return refOperation;
	}

	public void setRefOperation(String refOperation) {
		this.refOperation = refOperation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getLienPhotoPlf() {
		return lienPhotoPlf;
	}

	public void setLienPhotoPlf(String lienPhotoPlf) {
		this.lienPhotoPlf = lienPhotoPlf;
	}

	public CompteBanquaireEntity getCompteDebite() {
		return compteDebite;
	}

	public void setCompteDebite(CompteBanquaireEntity compteDebite) {
		this.compteDebite = compteDebite;
	}

	public FournisseurEntity getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurEntity fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<FactureEntity> getFactures() {
		return factures;
	}

	public void setFactures(List<FactureEntity> factures) {
		this.factures = factures;
	}

	public List<RemiseEntity> getRemises() {
		return remises;
	}

	public void setRemises(List<RemiseEntity> remises) {
		this.remises = remises;
	}

}
