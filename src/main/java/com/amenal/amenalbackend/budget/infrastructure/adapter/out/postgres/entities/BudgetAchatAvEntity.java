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
@Table(name = "budget_achat_av")
public class BudgetAchatAvEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_budget_achat_av")
    private Integer id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "object")
    private String object;

    @Column(name = "observation")
    private String observation;

    @Column(name = "date_etablissement")
    private LocalDate dateEtablissement;

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

    @Column(name = "date_saisie")
    private LocalDate dateSaisie;

    @Column(name = "expire")
    private Boolean expire;

    @Column(name = "id_supp")
    private Integer idSupp;

    @Column(name = "maj")
    private Boolean maj;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avenant")
    private AvenantEntity avenant;

	public BudgetAchatAvEntity(Integer id, String reference, String object, String observation, LocalDate dateEtablissement,
			Double qteGeneralPrevu, String uniteGeneral, Double pUnitaireGeneral, Integer delaiGlobalPrevu,
			Integer delaiGlobalReel, Double qteGeneralReel, LocalDate dateSaisie, Boolean expire, Integer idSupp,
			Boolean maj, AvenantEntity avenant) {
		super();
		this.id = id;
		this.reference = reference;
		this.object = object;
		this.observation = observation;
		this.dateEtablissement = dateEtablissement;
		this.qteGeneralPrevu = qteGeneralPrevu;
		this.uniteGeneral = uniteGeneral;
		this.punitaireGeneral = pUnitaireGeneral;
		this.delaiGlobalPrevu = delaiGlobalPrevu;
		this.delaiGlobalReel = delaiGlobalReel;
		this.qteGeneralReel = qteGeneralReel;
		this.dateSaisie = dateSaisie;
		this.expire = expire;
		this.idSupp = idSupp;
		this.maj = maj;
		this.avenant = avenant;
	}

	public BudgetAchatAvEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public LocalDate getDateEtablissement() {
		return dateEtablissement;
	}

	public void setDateEtablissement(LocalDate dateEtablissement) {
		this.dateEtablissement = dateEtablissement;
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

	public LocalDate getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDate dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Boolean getExpire() {
		return expire;
	}

	public void setExpire(Boolean expire) {
		this.expire = expire;
	}

	public Integer getIdSupp() {
		return idSupp;
	}

	public void setIdSupp(Integer idSupp) {
		this.idSupp = idSupp;
	}

	public Boolean getMaj() {
		return maj;
	}

	public void setMaj(Boolean maj) {
		this.maj = maj;
	}

	public AvenantEntity getAvenant() {
		return avenant;
	}

	public void setAvenant(AvenantEntity avenant) {
		this.avenant = avenant;
	}

}
