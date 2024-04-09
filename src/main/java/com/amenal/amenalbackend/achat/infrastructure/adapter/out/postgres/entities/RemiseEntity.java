package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "remise")
public class RemiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remise")
    private Integer id;

    @Column(name = "date_remise")
    private LocalDate dateRemise;

    @Column(name = "remise_en")
    private String remiseEn;

    @Column(name = "remise_a")
    private String remiseA;

    @Column(name = "remise_via")
    private String remiseVia;

    @Column(name = "lien_photo_remise")
    private String lienPhotoRemise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paiement")
    private PaiementEntity paiement;

	public RemiseEntity() {
		super();
	}

	public RemiseEntity(Integer id, LocalDate dateRemise, String remiseEn, String remiseA, String remiseVia,
			String lienPhotoRemise, PaiementEntity paiement) {
		super();
		this.id = id;
		this.dateRemise = dateRemise;
		this.remiseEn = remiseEn;
		this.remiseA = remiseA;
		this.remiseVia = remiseVia;
		this.lienPhotoRemise = lienPhotoRemise;
		this.paiement = paiement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(LocalDate dateRemise) {
		this.dateRemise = dateRemise;
	}

	public String getRemiseEn() {
		return remiseEn;
	}

	public void setRemiseEn(String remiseEn) {
		this.remiseEn = remiseEn;
	}

	public String getRemiseA() {
		return remiseA;
	}

	public void setRemiseA(String remiseA) {
		this.remiseA = remiseA;
	}

	public String getRemiseVia() {
		return remiseVia;
	}

	public void setRemiseVia(String remiseVia) {
		this.remiseVia = remiseVia;
	}

	public String getLienPhotoRemise() {
		return lienPhotoRemise;
	}

	public void setLienPhotoRemise(String lienPhotoRemise) {
		this.lienPhotoRemise = lienPhotoRemise;
	}

	public PaiementEntity getPaiement() {
		return paiement;
	}

	public void setPaiement(PaiementEntity paiement) {
		this.paiement = paiement;
	}

}
