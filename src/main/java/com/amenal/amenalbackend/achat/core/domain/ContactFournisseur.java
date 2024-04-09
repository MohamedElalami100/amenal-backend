package com.amenal.amenalbackend.achat.core.domain;

public class ContactFournisseur {
	private Integer id;
	private String nomComplet;
	private String fonction;
	private String email;
	private String tel;
	private Fournisseur fournisseur;

	public ContactFournisseur(Integer id, String nomComplet, String fonction, String email, String tel,
			Fournisseur fournisseur) {
		super();
		this.id = id;
		this.nomComplet = nomComplet;
		this.fonction = fonction;
		this.email = email;
		this.tel = tel;
		this.fournisseur = fournisseur;
	}

	public ContactFournisseur() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
