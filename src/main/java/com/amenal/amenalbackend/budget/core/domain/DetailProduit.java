package com.amenal.amenalbackend.budget.core.domain;

//détailslotav in Microsoft access database
public class DetailProduit {
	private Integer id;
	private String reference;
	private Double nbr;
	private Double dim1;
	private Double dim2;
	private Double dim3;
	private String ordre;

	// Fk objects:
	private Tache tache;

	public DetailProduit(Integer id, String reference, Double nbr, Double dim1, Double dim2, Double dim3, String ordre, String ordreMef, String ordrePrt, Tache tache) {
		super();
		this.id = id;
		this.reference = reference;
		this.nbr = nbr;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.ordre = ordre;
		this.tache = tache;
	}

	public DetailProduit() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
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
