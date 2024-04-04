package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_facture")
public class DetailFactureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_facture")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_reception")
    private DetailReceptionEntity detailReception;

    @Column(name = "qte_a_facture")
    private Double qteAFacture;

    @Column(name = "qte_rectifie")
    private Double qteRectifie;

    @Column(name = "prix_unitaire_ht_rectifie")
    private Double prixUnitaireHtRectifie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facture")
    private FactureEntity facture;

	public DetailFactureEntity(Integer id, DetailReceptionEntity detailReception, Double qteAFacture,
			Double qteRectifie, Double prixUnitaireHtRectifie, FactureEntity facture) {
		super();
		this.id = id;
		this.detailReception = detailReception;
		this.qteAFacture = qteAFacture;
		this.qteRectifie = qteRectifie;
		this.prixUnitaireHtRectifie = prixUnitaireHtRectifie;
		this.facture = facture;
	}

	public DetailFactureEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailReceptionEntity getDetailReception() {
		return detailReception;
	}

	public void setDetailReception(DetailReceptionEntity detailReception) {
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

	public FactureEntity getFacture() {
		return facture;
	}

	public void setFacture(FactureEntity facture) {
		this.facture = facture;
	}

}
