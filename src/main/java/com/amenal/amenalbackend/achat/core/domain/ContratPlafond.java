package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;

public class ContratPlafond {
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Integer delaiReglement;
	private String lienContrat;

	public ContratPlafond(Integer id, LocalDate dateDebut, LocalDate dateFin, Integer delaiReglement,
			String lienContrat) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.delaiReglement = delaiReglement;
		this.lienContrat = lienContrat;
	}

	public ContratPlafond() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getDelaiReglement() {
		return delaiReglement;
	}

	public void setDelaiReglement(Integer delaiReglement) {
		this.delaiReglement = delaiReglement;
	}

}
