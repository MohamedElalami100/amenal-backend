package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transport")
public class TransportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transport")
    private Integer id;

    @Column(name = "date_depart")
    private LocalDate dateDepart;

    @Column(name = "date_arrive")
    private LocalDate dateArrive;

    @Column(name = "pris_en_charge_par")
    private String prisEnChargePar;

    @Column(name = "lien_photo")
    private String lienPhoto;

    @Column(name = "type")
    private String type;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "chauffeur")
    private String chauffeur;

    @Column(name = "chauffeur_cin")
    private String chauffeurCin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reception")
	@JsonIgnore
    private ReceptionEntity reception;

	public TransportEntity(Integer id, LocalDate dateDepart, LocalDate dateArrive, String prisEnChargePar, String lienPhoto,
			String type, String matricule, String chauffeur, String chauffeurCin, ReceptionEntity reception) {
		super();
		this.id = id;
		this.dateDepart = dateDepart;
		this.dateArrive = dateArrive;
		this.prisEnChargePar = prisEnChargePar;
		this.lienPhoto = lienPhoto;
		this.type = type;
		this.matricule = matricule;
		this.chauffeur = chauffeur;
		this.chauffeurCin = chauffeurCin;
		this.reception = reception;
	}

	public TransportEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public LocalDate getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(LocalDate dateArrive) {
		this.dateArrive = dateArrive;
	}

	public String getPrisEnChargePar() {
		return prisEnChargePar;
	}

	public void setPrisEnChargePar(String prisEnChargePar) {
		this.prisEnChargePar = prisEnChargePar;
	}

	public String getLienPhoto() {
		return lienPhoto;
	}

	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getChauffeurCin() {
		return chauffeurCin;
	}

	public void setChauffeurCin(String chauffeurCin) {
		this.chauffeurCin = chauffeurCin;
	}

	public ReceptionEntity getReception() {
		return reception;
	}

	public void setReception(ReceptionEntity reception) {
		this.reception = reception;
	}
	
}
