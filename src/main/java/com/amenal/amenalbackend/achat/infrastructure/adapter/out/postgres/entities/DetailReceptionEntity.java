package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_reception")
public class DetailReceptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_reception")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_devis")
    private DetailDevisEntity detailCommande;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "lien_photo_article")
    private String lienPhotoArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reception")
    private ReceptionEntity reception;

	public DetailReceptionEntity() {
		super();
	}

	public DetailReceptionEntity(Integer id, DetailDevisEntity detailCommande, Double qte, String lienPhotoArticle,
			ReceptionEntity reception) {
		super();
		this.id = id;
		this.detailCommande = detailCommande;
		this.qte = qte;
		this.lienPhotoArticle = lienPhotoArticle;
		this.reception = reception;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailDevisEntity getDetailCommande() {
		return detailCommande;
	}

	public void setDetailCommande(DetailDevisEntity detailCommande) {
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

	public ReceptionEntity getReception() {
		return reception;
	}

	public void setReception(ReceptionEntity reception) {
		this.reception = reception;
	}

	// business methods:
	public Double getMntHt() {
		try {
			return detailCommande.getPrixUnitaire() * qte;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return getMntHt() * 0.2;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return getMntHt() * 1.2;
		} catch (Exception e) {
			return null;
		}
	}
}
