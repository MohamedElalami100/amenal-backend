package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "dossier")
public class DossierEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dossier")
	private Integer id;

	@Column(name = "num_dossier")
	private String numDossier;

	@Column(name = "date_ouverture")
	private LocalDate dateOuverture;

	@Column(name = "object")
	private String object;

	@Column(name = "nom_agent")
	private String nomAgent;

	@Column(name = "cloture")
	private Boolean cloture;

	public DossierEntity(Integer id, String numDossier, LocalDate dateOuverture, String object, String nomAgent,
			Boolean cloture) {
		super();
		this.id = id;
		this.numDossier = numDossier;
		this.dateOuverture = dateOuverture;
		this.object = object;
		this.nomAgent = nomAgent;
		this.cloture = cloture;
	}

	public DossierEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public LocalDate getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getNomAgent() {
		return nomAgent;
	}

	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}

	public Boolean getCloture() {
		return cloture;
	}

	public void setCloture(Boolean cloture) {
		this.cloture = cloture;
	}
}
