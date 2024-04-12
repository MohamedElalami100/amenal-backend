package com.amenal.amenalbackend.achat.infrastructure.dto;

import com.amenal.amenalbackend.achat.core.domain.Facture;

public class DetailFactureDto {
    private Integer id;
    private DetailReceptionDto detailReception;
    private Double qteAFacture;
    private Double qteRectifie;
    private Double prixUnitaireHtRectifie;
    private Double mntHt;
    private Double mntTva;
    private Double mntTtc;

    public DetailFactureDto(Integer id, DetailReceptionDto detailReception, Double qteAFacture, Double qteRectifie,
                            Double prixUnitaireHtRectifie ,Double mntHt, Double mntTva, Double mntTtc) {
        super();
        this.id = id;
        this.detailReception = detailReception;
        this.qteAFacture = qteAFacture;
        this.qteRectifie = qteRectifie;
        this.prixUnitaireHtRectifie = prixUnitaireHtRectifie;
        this.mntHt = mntHt;
        this.mntTva = mntTva;
        this.mntTtc = mntTtc;
    }

    public DetailFactureDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetailReceptionDto getDetailReception() {
        return detailReception;
    }

    public void setDetailReception(DetailReceptionDto detailReception) {
        this.detailReception = detailReception;
    }

    public Double getQteAFacture() {
        return qteAFacture;
    }

    public void setQteAFacture(Double qteAFacture) {
        this.qteAFacture = qteAFacture;
    }

    public Double getQteRectifie() {
        return qteRectifie;
    }

    public void setQteRectifie(Double qteRectifie) {
        this.qteRectifie = qteRectifie;
    }

    public Double getPrixUnitaireHtRectifie() {
        return prixUnitaireHtRectifie;
    }

    public void setPrixUnitaireHtRectifie(Double prixUnitaireHtRectifie) {
        this.prixUnitaireHtRectifie = prixUnitaireHtRectifie;
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
