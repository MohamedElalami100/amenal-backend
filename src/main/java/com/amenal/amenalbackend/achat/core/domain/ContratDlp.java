package com.amenal.amenalbackend.achat.core.domain;

public class ContratDlp {
	private Integer id;
	private Integer plafond;
	private String lienContrat;

	private Fournisseur fournisseur;

	public ContratDlp(Integer id, Integer plafond, String lienContrat, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.plafond = plafond;
		this.lienContrat = lienContrat;
		this.fournisseur = fournisseur;
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

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
