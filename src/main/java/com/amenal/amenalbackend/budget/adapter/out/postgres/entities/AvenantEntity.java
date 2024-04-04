package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "avenant")
public class AvenantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avenant")
    private Integer id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "delai")
    private Integer delai;

    @Column(name = "valide")
    private Boolean valide;

    @Column(name = "reference")
    private String reference;

    @Column(name = "id_cmc")
    private Integer idCmc;

    @Column(name = "date_avenant")
    private LocalDate dateAvenant;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    private ProjectEntity project;

	public AvenantEntity(Integer id, String titre, LocalDate dateDebut, Integer delai, Boolean valide, String reference,
			Integer idCmc, LocalDate dateAvenant, ProjectEntity project) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.delai = delai;
		this.valide = valide;
		this.reference = reference;
		this.idCmc = idCmc;
		this.dateAvenant = dateAvenant;
		this.project = project;
	}

	public AvenantEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Integer getDelai() {
		return delai;
	}

	public void setDelai(Integer delai) {
		this.delai = delai;
	}

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getIdCmc() {
		return idCmc;
	}

	public void setIdCmc(Integer idCmc) {
		this.idCmc = idCmc;
	}

	public LocalDate getDateAvenant() {
		return dateAvenant;
	}

	public void setDateAvenant(LocalDate dateAvenant) {
		this.dateAvenant = dateAvenant;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	// business methods:
	public LocalDate getDateFin() {
		try {
			return dateDebut.plusDays(delai);
		} catch (Exception e) {
			return null;
		}
	}
}
