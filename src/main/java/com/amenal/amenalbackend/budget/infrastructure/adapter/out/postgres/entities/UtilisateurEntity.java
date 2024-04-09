package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Integer id;

    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Boolean active;

    // Fk objects:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricule")
    private PersonnelEntity personnel;
	
	public UtilisateurEntity(Integer id, String nomUtilisateur, String motDePasse, String tel, String email, Boolean active,
			PersonnelEntity personnel) {
		super();
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
		this.tel = tel;
		this.email = email;
		this.active = active;
		this.personnel = personnel;
	}

	public UtilisateurEntity() {
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

	public PersonnelEntity getPersonnel() {
		return personnel;
	}

	public void setPersonnel(PersonnelEntity personnel) {
		this.personnel = personnel;
	}

}
