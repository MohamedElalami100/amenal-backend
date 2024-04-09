package com.amenal.amenalbackend.achat.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compte_banquaire")
public class CompteBanquaireEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compte_banquaire")
    private Integer id;

    @Column(name = "designation")
    private String designation;

	public CompteBanquaireEntity(Integer id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
	}

	public CompteBanquaireEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
