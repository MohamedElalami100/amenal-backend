package com.amenal.amenalbackend.achat.core.domain;

public class ContratDlp {
	private Integer id;
	private Integer plafond;
	private String lienContrat;

	public ContratDlp(Integer id, Integer plafond, String lienContrat) {
		super();
		this.id = id;
		this.plafond = plafond;
		this.lienContrat = lienContrat;
	}

	public ContratDlp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlafond() {
		return plafond;
	}

	public void setPlafond(Integer plafond) {
		this.plafond = plafond;
	}

	public String getLienContrat() {
		return lienContrat;
	}

	public void setLienContrat(String lienContrat) {
		this.lienContrat = lienContrat;
	}



}
