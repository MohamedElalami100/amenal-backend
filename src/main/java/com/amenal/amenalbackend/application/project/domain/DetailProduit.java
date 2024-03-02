package com.amenal.amenalbackend.application.project.domain;

//détailslotav in Microsoft access database
public class DetailProduit {
	private Integer id;
	private String reference;
	private Double nbr;
	private Double dim1;
	private Double dim2;
	private Double dim3;
	private Integer id2;
	private Boolean maj;
	private Integer ordre;
	private String ordreMef;
	private String ordrePrt;

	// Fk objects:
	private Tache tache;

	public DetailProduit(Integer id, String reference, Double nbr, Double dim1, Double dim2, Double dim3,
			Integer id2, Boolean maj, Integer ordre, String ordreMef, String ordrePrt, Tache tache) {
		super();
		this.id = id;
		this.reference = reference;
		this.nbr = nbr;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.id2 = id2;
		this.maj = maj;
		this.ordre = ordre;
		this.ordreMef = ordreMef;
		this.ordrePrt = ordrePrt;
		this.tache = tache;
	}

	public DetailProduit() {
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

	public Double getNbr() {
		return nbr;
	}

	public void setNbr(Double nbr) {
		this.nbr = nbr;
	}

	public Double getDim1() {
		return dim1;
	}

	public void setDim1(Double dim1) {
		this.dim1 = dim1;
	}

	public Double getDim2() {
		return dim2;
	}

	public void setDim2(Double dim2) {
		this.dim2 = dim2;
	}

	public Double getDim3() {
		return dim3;
	}

	public void setDim3(Double dim3) {
		this.dim3 = dim3;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public Boolean getMaj() {
		return maj;
	}

	public void setMaj(Boolean maj) {
		this.maj = maj;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public String getOrdreMef() {
		return ordreMef;
	}

	public void setOrdreMef(String ordreMef) {
		this.ordreMef = ordreMef;
	}

	public String getOrdrePrt() {
		return ordrePrt;
	}

	public void setOrdrePrt(String ordrePrt) {
		this.ordrePrt = ordrePrt;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	// business methods:
	public Double getQte() {
		return (dim1 == null || dim1 == 0 ? 1 : dim1) * (dim2 == null || dim2 == 0 ? 1 : dim2)
				* (dim3 == null || dim3 == 0 ? 1 : dim3) * (nbr == null || nbr == 0 ? 1 : nbr);
	}
}
