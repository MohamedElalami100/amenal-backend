package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "facture")
public class FactureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture")
    private Integer id;

    @Column(name = "date_fcf")
    private LocalDate dateFcf;

    @Column(name = "reference")
    private String reference;

    @Column(name = "lien_photo_facture")
    private String lienPhotoFacture;

    @Column(name = "delai_facture")
    private Integer delaiFacture;

    @Column(name = "mnt_ht_note")
    private Double mntHtNote;

    @Column(name = "mnt_tva_note")
    private Double mntTvaNote;

    @Column(name = "mnt_ttc_note")
    private Double mntTtcNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fournisseur")
    private FournisseurEntity fournisseur;

    @ManyToMany
    @JoinTable(
            name = "facture_paiement",
            joinColumns = @JoinColumn(name = "id_facture"),
            inverseJoinColumns = @JoinColumn(name = "id_paiement"))
	@JsonIgnore
    private List<PaiementEntity> paiements;

    @OneToMany(mappedBy = "facture")
    private List<ReceptionEntity> receptions;
    
    @OneToMany(mappedBy = "facture")
	private List<DetailFactureEntity> detailFactures;

	public FactureEntity(Integer id, LocalDate dateFcf, String reference, String lienPhotoFacture, Integer delaiFacture,
			Double mntHtNote, Double mntTvaNote, Double mntTtcNote, FournisseurEntity fournisseur,
			List<PaiementEntity> paiements, List<ReceptionEntity> receptions, List<DetailFactureEntity> detailFactures) {
		super();
		this.id = id;
		this.dateFcf = dateFcf;
		this.reference = reference;
		this.lienPhotoFacture = lienPhotoFacture;
		this.delaiFacture = delaiFacture;
		this.mntHtNote = mntHtNote;
		this.mntTvaNote = mntTvaNote;
		this.mntTtcNote = mntTtcNote;
		this.fournisseur = fournisseur;
		this.paiements = paiements;
		this.receptions = receptions;
		this.detailFactures = detailFactures;
	}

	public FactureEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateFcf() {
		return dateFcf;
	}

	public void setDateFcf(LocalDate dateFcf) {
		this.dateFcf = dateFcf;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLienPhotoFacture() {
		return lienPhotoFacture;
	}

	public void setLienPhotoFacture(String lienPhotoFacture) {
		this.lienPhotoFacture = lienPhotoFacture;
	}

	public Integer getDelaiFacture() {
		return delaiFacture;
	}

	public void setDelaiFacture(Integer delaiFacture) {
		this.delaiFacture = delaiFacture;
	}

	public Double getMntHtNote() {
		return mntHtNote;
	}

	public void setMntHtNote(Double mntHtNote) {
		this.mntHtNote = mntHtNote;
	}

	public Double getMntTvaNote() {
		return mntTvaNote;
	}

	public void setMntTvaNote(Double mntTvaNote) {
		this.mntTvaNote = mntTvaNote;
	}

	public Double getMntTtcNote() {
		return mntTtcNote;
	}

	public void setMntTtcNote(Double mntTtcNote) {
		this.mntTtcNote = mntTtcNote;
	}

	public List<ReceptionEntity> getReceptions() {
		return receptions;
	}

	public void setReceptions(List<ReceptionEntity> receptions) {
		this.receptions = receptions;
	}

	public FournisseurEntity getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurEntity fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<PaiementEntity> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<PaiementEntity> paiements) {
		this.paiements = paiements;
	}

	public List<DetailFactureEntity> getDetailFactures() {
		return detailFactures;
	}

	public void setDetailFactures(List<DetailFactureEntity> detailFactures) {
		this.detailFactures = detailFactures;
	}

}
