package com.amenal.amenalbackend.budget.core.domain;

import java.time.LocalDate;

import com.amenal.amenalbackend.utils.infrastructure.exception.InvalidInputException;

public class Dossier {
	private Integer id;
	private String numDossier;
	private LocalDate dateOuverture;
	private String object;
	private String nomAgent;
	private Boolean cloture;
	private Project project;

	public Dossier(Integer id, String numDossier, LocalDate dateOuverture, String object, String nomAgent,
			Boolean cloture, Project project) throws InvalidInputException {
		super();
		this.id = id;
		// Validate and format the input using the specified mask
		if (isValidNumDossier(numDossier)) {
			this.numDossier = numDossier;
		} else {
			// Throw an exception for invalid input
			throw new InvalidInputException("Invalid input: " + numDossier);
		}
		this.dateOuverture = dateOuverture;
		this.object = object;
		this.nomAgent = nomAgent;
		this.cloture = cloture;
		this.project = project;
	}

	public Dossier() {
		super();
	}

	public String getNumDossier() {
		return numDossier;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	// input validation for numDossier:
	private boolean isValidNumDossier(String numDossier) {
		// Validate the input against the specified mask
		String regex = "\\d{2}-\\d{3}";
		return numDossier.matches(regex);
	}

}
