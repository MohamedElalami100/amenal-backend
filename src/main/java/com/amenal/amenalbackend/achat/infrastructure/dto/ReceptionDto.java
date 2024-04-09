package com.amenal.amenalbackend.achat.infrastructure.dto;

import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Commande;
import com.amenal.amenalbackend.achat.core.domain.Facture;
import com.amenal.amenalbackend.achat.core.domain.Transport;

public class ReceptionDto {
    private Integer id;
    private LocalDate dateRcf;
    private String reference;
    private String lienImageBonLivraison;

    private Commande commande;
    private Facture facture;

    private List<Transport> transports;
    private List<DetailReceptionDto> detailReceptions;

    private Double mntHt;
    private Double mntTva;
    private Double mntTtc;

    public ReceptionDto(Integer id, LocalDate dateRcf, String reference, String lienImageBonLivraison, Commande commande,
                        Facture facture, List<Transport> transports, List<DetailReceptionDto> detailReceptions,
                        Double mntHt, Double mntTva, Double mntTtc) {
        super();
        this.id = id;
        this.dateRcf = dateRcf;
        this.reference = reference;
        this.lienImageBonLivraison = lienImageBonLivraison;
        this.commande = commande;
        this.facture = facture;
        this.transports = transports;
        this.detailReceptions = detailReceptions;
        this.mntHt = mntHt;
        this.mntTva = mntTva;
        this.mntTtc = mntTtc;
    }

    public ReceptionDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateRcf() {
        return dateRcf;
    }

    public void setDateRcf(LocalDate dateRcf) {
        this.dateRcf = dateRcf;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLienImageBonLivraison() {
        return lienImageBonLivraison;
    }

    public void setLienImageBonLivraison(String lienImageBonLivraison) {
        this.lienImageBonLivraison = lienImageBonLivraison;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public List<DetailReceptionDto> getDetailReceptions() {
        return detailReceptions;
    }

    public void setDetailReceptions(List<DetailReceptionDto> detailReceptions) {
        this.detailReceptions = detailReceptions;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
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
