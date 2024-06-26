package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;

public class EvaluationFournisseur {
	private Integer id;
	private LocalDate date;
	private String evaluation;

	public EvaluationFournisseur(Integer id, LocalDate date, String evaluation, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.date = date;
		this.evaluation = evaluation;
	}

	public EvaluationFournisseur() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}



}
