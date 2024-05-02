package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.utils.core.domain.HasSons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lot implements HasSons{
	private Integer id;
	private String designation;
	private Integer ordre;

	// Fk objects:
	private Project project;
	private List<Tache> taches;

	public Lot(Integer id, String designation, Integer ordre, Project project, List<Tache> taches) {
		this.id = id;
		this.designation = designation;
		this.ordre = ordre;
		this.project = project;
		this.taches = taches;
	}

	@Override
	public List<List<HasSons>> getSons() {
		return new ArrayList<>();
	}

	@Override
	public List<String> getErrors() {
		return new ArrayList<>();
	}

	public Lot() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	// business methods:
	public Double getMntRefB() {
		try {
			Double mntRefCalcule = 0.0;
			for (Tache tache : taches) {
				if (tache.getCle()) {
					mntRefCalcule += tache.getMntRefB();
				}
			}
			return mntRefCalcule;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMncBdg() {
		try {
			Double mncBdgCalcule = 0.0;
			for (Tache tache : taches) {
				if (tache != null && tache.getCle() != null && tache.getCle()) {
					mncBdgCalcule += tache.getMncBdg();
				}
			}
			return mncBdgCalcule;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRefB() {
		return (getMntRefB() == null ? 0 : getMntRefB()) - (getMncBdg() == null ? 0 : getMncBdg());
	}

	public Double getMrpRefB() {
		try {
			double result;
			if (getMntRefB() == null || getMntRefB() == 0) {
				result = 0;
			} else {
				result = getMrgRefB() / getMntRefB();
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateDbtIni() {
		try {
			return taches.stream()
					.map(Tache::getDdb)
					.filter(date -> date != null) // Filter out null dates
					.min(LocalDate::compareTo).get();
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateFinIni() {
		try {
			return taches.stream()
					.map(Tache::getDateFin)
					.filter(date -> date != null) // Filter out null dates
					.max(LocalDate::compareTo).get();
		} catch (Exception e) {
			return null;
		}
	}

	public Integer getDlaIni() {
		try {
			if (getDateDbtIni() == null && getDateFinIni() == null)
				return 1;
			else
				return (int) (getDateFinIni().toEpochDay() - getDateDbtIni().toEpochDay() + 1);
		} catch (Exception e) {
			return null;
		}
	}
}
