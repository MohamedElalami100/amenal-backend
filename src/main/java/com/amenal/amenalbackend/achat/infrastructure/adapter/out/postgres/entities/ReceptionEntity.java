package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reception")
public class ReceptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reception")
    private Integer id;

    @Column(name = "date_rcf")
    private LocalDate dateRcf;

    @Column(name = "reference")
    private String reference;

    @Column(name = "lien_image_bon_livraison")
    private String lienImageBonLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commande")
    private CommandeEntity commande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facture")
    private FactureEntity facture;

    @OneToMany(mappedBy = "reception")
    private List<TransportEntity> transports;

    @OneToMany(mappedBy = "reception")
    private List<DetailReceptionEntity> detailReceptions;

	public ReceptionEntity(Integer id, LocalDate dateRcf, String reference, String lienImageBonLivraison, CommandeEntity commande,
			FactureEntity facture, List<TransportEntity> transports, List<DetailReceptionEntity> detailReceptions) {
		super();
		this.id = id;
		this.dateRcf = dateRcf;
		this.reference = reference;
		this.lienImageBonLivraison = lienImageBonLivraison;
		this.commande = commande;
		this.facture = facture;
		this.transports = transports;
		this.detailReceptions = detailReceptions;
	}

	public ReceptionEntity() {
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

	public CommandeEntity getCommande() {
		return commande;
	}

	public void setCommande(CommandeEntity commande) {
		this.commande = commande;
	}

	public List<TransportEntity> getTransports() {
		return transports;
	}

	public void setTransports(List<TransportEntity> transports) {
		this.transports = transports;
	}

	public List<DetailReceptionEntity> getDetailReceptions() {
		return detailReceptions;
	}

	public void setDetailReceptions(List<DetailReceptionEntity> detailReceptions) {
		this.detailReceptions = detailReceptions;
	}

	public FactureEntity getFacture() {
		return facture;
	}

	public void setFacture(FactureEntity facture) {
		this.facture = facture;
	}

	// business methods:
	public Double getMntHt() {
		try {
			return detailReceptions.stream().mapToDouble(obj -> obj.getMntHt() != null ? obj.getMntHt() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTva() {
		try {
			return detailReceptions.stream().mapToDouble(obj -> obj.getMntTva() != null ? obj.getMntTva() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntTtc() {
		try {
			return detailReceptions.stream().mapToDouble(obj -> obj.getMntTtc() != null ? obj.getMntTtc() : 0).sum();
		} catch (Exception e) {
			return null;
		}
	}
}
