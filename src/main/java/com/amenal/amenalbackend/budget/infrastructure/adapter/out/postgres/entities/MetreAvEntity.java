package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

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
@Table(name = "metre_av")
public class MetreAvEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_metre_av")
	private Integer id;

	@Column(name = "titre")
	private String titre;

	@Column(name = "date_budget")
	private LocalDate dateBudget;

	@Column(name = "reference")
	private String reference;

	@Column(name = "qte_general_prevu")
	private Double qteGeneralPrevu;

	@Column(name = "unite_general")
	private String uniteGeneral;

	@Column(name = "punitaire_general")
	private Double punitaireGeneral;

	@Column(name = "delai_global_prevu")
	private Integer delaiGlobalPrevu;

	@Column(name = "delai_global_reel")
	private Integer delaiGlobalReel;

	@Column(name = "qte_general_reel")
	private Double qteGeneralReel;

	@Column(name = "date_debut_prevu")
	private LocalDate dateDebutPrevu;

	@Column(name = "date_fin_prevu")
	private LocalDate dateFinPrevu;

	@Column(name = "date_debut_reel")
	private LocalDate dateDebutReel;

	@Column(name = "date_fin_reel")
	private LocalDate dateFinReel;

	@Column(name = "valide")
	private Boolean valide;

	// Fk objects:
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_budget_achat_av")
	private BudgetAchatAvEntity budget;

	public MetreAvEntity(Integer id, String titre, LocalDate dateBudget, String reference, Double qteGeneralPrevu,
			String uniteGeneral, Double pUnitaireGeneral, Integer delaiGlobalPrevu, Integer delaiGlobalReel,
			Double qteGeneralReel, LocalDate dateDebutPrevu, LocalDate dateFinPrevu, LocalDate dateDebutReel,
			LocalDate dateFinReel, Boolean valide, BudgetAchatAvEntity budget) {
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

	public MetreAvEntity() {
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

	public BudgetAchatAvEntity getBudget() {
		return budget;
	}

	public void setBudget(BudgetAchatAvEntity budget) {
		this.budget = budget;
	}

}
