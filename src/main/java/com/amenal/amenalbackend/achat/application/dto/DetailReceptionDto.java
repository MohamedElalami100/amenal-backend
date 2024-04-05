package com.amenal.amenalbackend.achat.application.dto;

import com.amenal.amenalbackend.achat.application.domain.DetailDevis;
import com.amenal.amenalbackend.achat.application.domain.Reception;

public class DetailReceptionDto {
    private Integer id;
    private DetailDevis detailCommande;
    private Double qte;
    private String lienPhotoArticle;

    private Reception reception;

    private Double mntHt;
    private Double mntTva;
    private Double mntTtc;

    public DetailReceptionDto(Integer id, DetailDevis detailCommande, Double qte, String lienPhotoArticle,
                              Reception reception, Double mntHt, Double mntTva, Double mntTtc) {
        super();
        this.id = id;
        this.detailCommande = detailCommande;
        this.qte = qte;
        this.lienPhotoArticle = lienPhotoArticle;
        this.reception = reception;
        this.mntHt = mntHt;
        this.mntTva = mntTva;
        this.mntTtc = mntTtc;
    }

    public DetailReceptionDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetailDevis getDetailCommande() {
        return detailCommande;
    }

    public void setDetailCommande(DetailDevis detailCommande) {
        this.detailCommande = detailCommande;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public String getLienPhotoArticle() {
        return lienPhotoArticle;
    }

    public void setLienPhotoArticle(String lienPhotoArticle) {
        this.lienPhotoArticle = lienPhotoArticle;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
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
