package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.utils.core.domain.HasSons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//SousProduitAv in Microsoft access database
public class Tache implements HasSons{
	private Integer id;
	private String titreActivite;
	private String upb;
	private Boolean cle;
	private Integer dlb;
	private LocalDate ddb;
	private String ordre;

	// Fk objects:
	private Produit produit;
	private Lot lot;
	private List<DetailProduit> detailProduits;
	private List<DetailCharge> detailCharges;

	public Tache(Integer id, String titreActivite, String upb, Boolean cle, Integer dlb, LocalDate ddb,
			String ordre, Produit produit, Lot lot, List<DetailProduit> detailProduits,
			List<DetailCharge> detailCharges) {
		this.id = id;
		this.titreActivite = titreActivite;
		this.upb = upb;
		this.cle = cle;
		this.dlb = dlb;
		this.ddb = ddb;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.detailProduits = detailProduits;
		this.detailCharges = detailCharges;
	}

	@Override
	public List<List<HasSons>> getSons() {
		List<List<HasSons>> sons = new ArrayList<>();
		List<HasSons> colorableDetailProduits = new ArrayList<>();
		List<HasSons> colorableDetailCharges = new ArrayList<>();
		if (detailProduits != null)
			colorableDetailProduits = detailProduits.stream()
					.map(detailProduit -> (HasSons) detailProduit)
					.collect(Collectors.toList());
		if (detailCharges != null)
			colorableDetailCharges = detailCharges.stream()
					.map(detailCharge -> (HasSons) detailCharge)
					.collect(Collectors.toList());
		sons.add(colorableDetailProduits);
		sons.add(colorableDetailCharges);
		return sons;
	}

	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<>();
		errors.add("pas de detailProduits associé");
		errors.add("pas de detailCharges associé");
		return errors;
	}

	public Tache() {
		super();
	}

	public List<DetailProduit> getDetailProduits() {
		return detailProduits;
	}

	public void setDetailProduits(List<DetailProduit> detailProduits) {
		this.detailProduits = detailProduits;
	}

	public List<DetailCharge> getDetailCharges() {
		return detailCharges;
	}

	public void setDetailCharges(List<DetailCharge> detailCharges) {
		this.detailCharges = detailCharges;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitreActivite() {
		return titreActivite;
	}

	public void setTitreActivite(String titreActivite) {
		this.titreActivite = titreActivite;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Boolean getCle() {
		return cle;
	}

	public void setCle(Boolean cle) {
		this.cle = cle;
	}

	public Integer getDlb() {
		return dlb;
	}

	public void setDlb(Integer dlb) {
		this.dlb = dlb;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	// business methods:

	public Double getPuRef() {
		try {
			return produit.getPpm();
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateFin() {
		try {
			return (dlb < 0) ? null : ddb.plusDays(dlb - 1);
		} catch (Exception e) {
			return null;
		}
	}

	public Double getPucBdg() {
		try {
			return getMncBdg() / getQtePBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMntRefB() {
		try {
			return getPuRef() * getQtePBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrgRefB() {
		try {
			return getMntRefB() - getMncBdg();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMrpRefB() {
		try {
			return (getMntRefB() == 0 || getMntRefB() == null) ? 0 : (getMntRefB() - getMncBdg()) / getMntRefB();
		} catch (Exception e) {
			return null;
		}
	}

	public Double getQtePBdg() {
		try {
			Double qteRefBCalcule = 0.0;
			for (DetailProduit detail : detailProduits) {
				qteRefBCalcule += detail.getQte();
			}
			return qteRefBCalcule;
		} catch (Exception e) {
			return null;
		}
	}

	public Double getMncBdg() {
		try {
			Double qteRefBCalcule = 0.0;
			for (DetailCharge detail : detailCharges) {
				qteRefBCalcule += detail.getMontant();
			}
			return qteRefBCalcule;
		} catch (Exception e) {
			return null;
		}
	}

}
