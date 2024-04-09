package com.amenal.amenalbackend.achat.infrastructure.dto;

import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Fournisseur;
import com.amenal.amenalbackend.achat.core.domain.Paiement;
import com.amenal.amenalbackend.achat.core.domain.Reception;

public class FactureDto {
    private Integer id;
    private LocalDate dateFcf;
    private String reference;
    private String lienPhotoFacture;
    private Integer delaiFacture;
    private Double mntHtNote;
    private Double mntTvaNote;
    private Double mntTtcNote;

    private Fournisseur fournisseur;
    private List<Paiement> paiements;
    private List<Reception> receptions;
    private List<DetailFactureDto> detailFactures;

    private Double mntHt;
    private Double mntTva;
    private Double mntTtc;

    public FactureDto(Integer id, LocalDate dateFcf, String reference, String lienPhotoFacture, Integer delaiFacture,
                      Double mntHtNote, Double mntTvaNote, Double mntTtcNote, Fournisseur fournisseur, List<Paiement> paiements,
                      List<Reception> receptions, List<DetailFactureDto> detailFactures, Double mntHt, Double mntTva, Double mntTtc) {
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
        this.mntHt = mntHt;
        this.mntTva = mntTva;
        this.mntTtc = mntTtc;
    }

    public FactureDto() {
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

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<DetailFactureDto> getDetailFactures() {
        return detailFactures;
    }

    public void setDetailFactures(List<DetailFactureDto> detailFactures) {
        this.detailFactures = detailFactures;
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
