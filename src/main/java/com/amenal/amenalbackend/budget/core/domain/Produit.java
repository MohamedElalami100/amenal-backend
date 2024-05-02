package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.utils.core.domain.HasSons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Produit implements HasSons{
	private Integer id;
	private String art;
	private String designation;
	private String upb;
	private Double ppm;
	private Double qpm;
	// Fk objects:
	private MetreAv metre;
	private List<Tache> taches;

	public Produit(Integer id, String art, String designation, String upb, Double ppm, Double qpm, MetreAv metre,
			List<Tache> taches) {
		this.id = id;
		this.art = art;
		this.designation = designation;
		this.upb = upb;
		this.ppm = ppm;
		this.qpm = qpm;
		this.metre = metre;
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

	public Produit() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Double getPpm() {
		return ppm;
	}

	public void setPpm(Double ppm) {
		this.ppm = ppm;
	}

	public Double getQpm() {
		return qpm;
	}

	public void setQpm(Double qpm) {
		this.qpm = qpm;
	}

	public MetreAv getMetre() {
		return metre;
	}

	public void setMetre(MetreAv metre) {
		this.metre = metre;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	// business methods:
	public Double getPucBdg() {
		try {
			Double qpmCum = getQteCum();
			return (qpmCum == 0 || qpmCum == null) ? 0 : getMncBdg() / qpmCum;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntRef() {
		try {
			return qpm * ppm;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRef() {
		try {
			return (getMntRef() == null ? 0 : getMntRef()) - (getMncBdg() == null ? 0 : getMncBdg());
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpRef() {
		try {
			double result;
			if (getMntRef() == null || getMntRef() == 0) {
				result = 0;
			} else {
				result = (getMntRef() - getMncBdg()) / getMntRef();
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getQteCum() {
		try {
			Double qpmCumCalcule = 0.0;
			for (Tache tache : taches) {
				if (tache != null && tache.getCle() != null && tache.getCle()) {
					qpmCumCalcule += tache.getQtePBdg();
				}
			}
			return qpmCumCalcule;
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
