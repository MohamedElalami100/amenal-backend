package com.amenal.amenalbackend.budget.application.dto;

import java.time.LocalDate;

public class DetailChargeTableDto {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private String charge;
	private String nature;
	private String ucb;
	private Double qcb;
	private Double pcb;
	private Double mcb;
	private String rcb;
	private Double qpb;
	private LocalDate ddb;
	private Integer produitId;
	private Integer lotId;
	private Integer activiteId;

	public DetailChargeTableDto(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String charge, String nature, String ucb, Double qcb, Double pcb, Double mcb, String rcb, Double qpb,
			LocalDate ddb, Integer produitId, Integer lotId, Integer activiteId) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.charge = charge;
		this.nature = nature;
		this.ucb = ucb;
		this.qcb = qcb;
		this.pcb = pcb;
		this.mcb = mcb;
		this.rcb = rcb;
		this.qpb = qpb;
		this.ddb = ddb;
		this.produitId = produitId;
		this.lotId = lotId;
		this.activiteId = activiteId;
	}

	public DetailChargeTableDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Boolean getCle() {
		return cle;
	}

	public void setCle(Boolean cle) {
		this.cle = cle;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getUcb() {
		return ucb;
	}

	public void setUcb(String ucb) {
		this.ucb = ucb;
	}

	public Double getQcb() {
		return qcb;
	}

	public void setQcb(Double qcb) {
		this.qcb = qcb;
	}

	public Double getPcb() {
		return pcb;
	}

	public void setPcb(Double pcb) {
		this.pcb = pcb;
	}

	public Double getMcb() {
		return mcb;
	}

	public void setMcb(Double mcb) {
		this.mcb = mcb;
	}

	public String getRcb() {
		return rcb;
	}

	public void setRcb(String rcb) {
		this.rcb = rcb;
	}

	public Double getQpb() {
		return qpb;
	}

	public void setQpb(Double qpb) {
		this.qpb = qpb;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public Integer getProduitId() {
		return produitId;
	}

	public void setProduitId(Integer produitId) {
		this.produitId = produitId;
	}

	public Integer getLotId() {
		return lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public Integer getActiviteId() {
		return activiteId;
	}

	public void setActiviteId(Integer activiteId) {
		this.activiteId = activiteId;
	}

}
