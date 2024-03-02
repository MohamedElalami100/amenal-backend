package com.amenal.amenalbackend.application.project.domain;

public class Utilisateur {
	private Integer id;
	private String nomUtilisateur;
	private String motDePasse;
	private String tel;
	private String email;
	private Boolean active;
	//Fk objects:
	private Personnel personnel;
	
	public Utilisateur(Integer id, String nomUtilisateur, String motDePasse, String tel, String email, Boolean active,
			Personnel personnel) {
		super();
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
		this.tel = tel;
		this.email = email;
		this.active = active;
		this.personnel = personnel;
	}

	public Utilisateur() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

}
