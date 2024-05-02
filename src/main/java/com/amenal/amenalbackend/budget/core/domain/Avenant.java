package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.utils.core.domain.HasSons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Avenant implements HasSons{
	private Integer id;
	private String titre;
	private LocalDate dateDebut;
	private Integer delai;
	private Boolean valide;
	private String reference;
	private Integer idCmc;
	private LocalDate dateAvenant;
	// Fk objects:
	private Project project;

	private List<Produit> produits;

	private List<Tache> taches;

	public Avenant(Integer id, String titre, LocalDate dateDebut, Integer delai, Boolean valide, String reference,
			Integer idCmc, LocalDate dateAvenant, Project project) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.delai = delai;
		this.valide = valide;
		this.reference = reference;
		this.idCmc = idCmc;
		this.dateAvenant = dateAvenant;
		this.project = project;
	}

	@Override
	public List<List<HasSons>> getSons() {
		List<List<HasSons>> sons = new ArrayList<>();
		List<HasSons> colorableProduits = new ArrayList<>();
		List<HasSons> colorableTaches = new ArrayList<>();
		if (produits != null)
			colorableProduits = produits.stream()
					.map(produit -> (HasSons) produit)
					.collect(Collectors.toList());
		if (taches != null)
			colorableTaches = taches.stream()
					.map(tache -> (HasSons) tache)
					.collect(Collectors.toList());
		sons.add(colorableProduits);
		sons.add(colorableTaches);
		return sons;
	}
	// "pas de produits associé"
	//

	public List<String> getErrors() {
		List<String> errors = new ArrayList<>();

		errors.add("pas de produits associé");
		errors.add("pas de taches associé");
		return errors;
	}

	public Avenant() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Integer getDelai() {
		return delai;
	}

	public void setDelai(Integer delai) {
		this.delai = delai;
	}

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getIdCmc() {
		return idCmc;
	}

	public void setIdCmc(Integer idCmc) {
		this.idCmc = idCmc;
	}

	public LocalDate getDateAvenant() {
		return dateAvenant;
	}

	public void setDateAvenant(LocalDate dateAvenant) {
		this.dateAvenant = dateAvenant;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	// business methods:
	public LocalDate getDateFin() {
		try {
			return dateDebut.plusDays(delai);
		} catch (Exception e) {
			return null;
		}
	}
}
