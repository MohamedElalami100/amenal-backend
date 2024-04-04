package com.amenal.amenalbackend.budget.application.domain;

import java.time.LocalDate;

public class MetreAv {
	private Integer id;
	private String titre;
	private LocalDate dateBudget;
	private String reference;
	private Double qteGeneralPrevu;
	private String uniteGeneral;
	private Double punitaireGeneral;
	private Integer delaiGlobalPrevu;
	private Integer delaiGlobalReel;
	private Double qteGeneralReel;
	private LocalDate dateDebutPrevu;
	private LocalDate dateFinPrevu;
	private LocalDate dateDebutReel;
	private LocalDate dateFinReel;
	private Boolean valide;
	private BudgetAchatAv budget;

	public MetreAv(Integer id, String titre, LocalDate dateBudget, String reference, Double qteGeneralPrevu,
			String uniteGeneral, Double pUnitaireGeneral, Integer delaiGlobalPrevu, Integer delaiGlobalReel,
			Double qteGeneralReel, LocalDate dateDebutPrevu, LocalDate dateFinPrevu, LocalDate dateDebutReel,
			LocalDate dateFinReel, Boolean valide, BudgetAchatAv budget) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateBudget = dateBudget;
		this.reference = reference;
		this.qteGeneralPrevu = qteGeneralPrevu;
		this.uniteGeneral = uniteGeneral;
		this.punitaireGeneral = pUnitaireGeneral;
		this.delaiGlobalPrevu = delaiGlobalPrevu;
		this.delaiGlobalReel = delaiGlobalReel;
		this.qteGeneralReel = qteGeneralReel;
		this.dateDebutPrevu = dateDebutPrevu;
		this.dateFinPrevu = dateFinPrevu;
		this.dateDebutReel = dateDebutReel;
		this.dateFinReel = dateFinReel;
		this.valide = valide;
		this.budget = budget;
	}

	public MetreAv() {
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

	public LocalDate getDateBudget() {
		return dateBudget;
	}

	public void setDateBudget(LocalDate dateBudget) {
		this.dateBudget = dateBudget;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getQteGeneralPrevu() {
		return qteGeneralPrevu;
	}

	public void setQteGeneralPrevu(Double qteGeneralPrevu) {
		this.qteGeneralPrevu = qteGeneralPrevu;
	}

	public String getUniteGeneral() {
		return uniteGeneral;
	}

	public void setUniteGeneral(String uniteGeneral) {
		this.uniteGeneral = uniteGeneral;
	}

	public Double getPUnitaireGeneral() {
		return punitaireGeneral;
	}

	public void setPUnitaireGeneral(Double pUnitaireGeneral) {
		this.punitaireGeneral = pUnitaireGeneral;
	}

	public Integer getDelaiGlobalPrevu() {
		return delaiGlobalPrevu;
	}

	public void setDelaiGlobalPrevu(Integer delaiGlobalPrevu) {
		this.delaiGlobalPrevu = delaiGlobalPrevu;
	}

	public Integer getDelaiGlobalReel() {
		return delaiGlobalReel;
	}

	public void setDelaiGlobalReel(Integer delaiGlobalReel) {
		this.delaiGlobalReel = delaiGlobalReel;
	}

	public Double getQteGeneralReel() {
		return qteGeneralReel;
	}

	public void setQteGeneralReel(Double qteGeneralReel) {
		this.qteGeneralReel = qteGeneralReel;
	}

	public LocalDate getDateDebutPrevu() {
		return dateDebutPrevu;
	}

	public void setDateDebutPrevu(LocalDate dateDebutPrevu) {
		this.dateDebutPrevu = dateDebutPrevu;
	}

	public LocalDate getDateFinPrevu() {
		return dateFinPrevu;
	}

	public void setDateFinPrevu(LocalDate dateFinPrevu) {
		this.dateFinPrevu = dateFinPrevu;
	}

	public LocalDate getDateDebutReel() {
		return dateDebutReel;
	}

	public void setDateDebutReel(LocalDate dateDebutReel) {
		this.dateDebutReel = dateDebutReel;
	}

	public LocalDate getDateFinReel() {
		return dateFinReel;
	}

	public void setDateFinReel(LocalDate dateFinReel) {
		this.dateFinReel = dateFinReel;
	}

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}
	public BudgetAchatAv getBudget() {
		return budget;
	}

	public void setBudget(BudgetAchatAv budget) {
		this.budget = budget;
	}

}
