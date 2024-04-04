package com.amenal.amenalbackend.achat.application.domain;

public class CompteBanquaire {
	private Integer id;
	private String designation;

	public CompteBanquaire(Integer id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
	}

	public CompteBanquaire() {
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
