package com.amenal.amenalbackend.budget.core.domain;

import java.time.LocalDate;

public class BudgetAchatAv {
	private Integer id;
	private String reference;
	private String object;
	private String observation;
	private LocalDate dateEtablissement;
	private Double qteGeneralPrevu;
	private String uniteGeneral;
	private Double punitaireGeneral;
	private Integer delaiGlobalPrevu;
	private Integer delaiGlobalReel;
	private Double qteGeneralReel;
	private LocalDate dateSaisie;
	private Boolean expire;
	private Integer idSupp;
	private Boolean maj;
	// Fk objects:
	private Avenant avenant;

	public BudgetAchatAv(Integer id, String reference, String object, String observation, LocalDate dateEtablissement,
			Double qteGeneralPrevu, String uniteGeneral, Double pUnitaireGeneral, Integer delaiGlobalPrevu,
			Integer delaiGlobalReel, Double qteGeneralReel, LocalDate dateSaisie, Boolean expire, Integer idSupp,
			Boolean maj, Avenant avenant) {
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

	public BudgetAchatAv() {
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

	public Avenant getAvenant() {
		return avenant;
	}

	public void setAvenant(Avenant avenant) {
		this.avenant = avenant;
	}

}
