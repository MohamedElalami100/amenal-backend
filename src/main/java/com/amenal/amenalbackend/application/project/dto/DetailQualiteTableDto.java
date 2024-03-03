package com.amenal.amenalbackend.application.project.dto;

public class DetailQualiteTableDto {
	private Integer id;
	private String ordre;
	private String produit;
	private String lot;
	private String activite;
	private String upb;
	private Boolean cle;
	private String groupe;
	private String pointDeControle;
	private Integer produitId;
	private Integer lotId;
	private Integer activiteId;

	public DetailQualiteTableDto(Integer id, String ordre, String produit, String lot, String activite, String upb, Boolean cle,
			String groupe, String pointDeControle, Integer produitId, Integer lotId, Integer activiteId) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.upb = upb;
		this.cle = cle;
		this.groupe = groupe;
		this.pointDeControle = pointDeControle;
		this.produitId = produitId;
		this.lotId = lotId;
		this.activiteId = activiteId;
	}

	public DetailQualiteTableDto() {
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

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getPointDeControle() {
		return pointDeControle;
	}

	public void setPointDeControle(String pointDeControle) {
		this.pointDeControle = pointDeControle;
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
