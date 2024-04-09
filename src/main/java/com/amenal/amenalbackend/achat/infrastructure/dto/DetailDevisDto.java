package com.amenal.amenalbackend.achat.infrastructure.dto;

import com.amenal.amenalbackend.achat.core.domain.ChargeStandard;
import com.amenal.amenalbackend.achat.core.domain.Devis;

public class DetailDevisDto {
    private Integer id;
    private ChargeStandard charge;
    private Double qte;
    private Double prixUnitaire;
    private Devis devis;

    private Double mntHt;
    private Double mntTva;
    private Double mntTtc;

    public DetailDevisDto(Integer id, ChargeStandard charge, Double qte, Double prixUnitaire, Devis devis,
                          Double mntHt, Double mntTva, Double mntTtc) {
        super();
        this.id = id;
        this.charge = charge;
        this.qte = qte;
        this.prixUnitaire = prixUnitaire;
        this.devis = devis;
        this.mntHt = mntHt;
        this.mntTva = mntTva;
        this.mntTtc = mntTtc;
    }

    public DetailDevisDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChargeStandard getCharge() {
        return charge;
    }

    public void setCharge(ChargeStandard charge) {
        this.charge = charge;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
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
