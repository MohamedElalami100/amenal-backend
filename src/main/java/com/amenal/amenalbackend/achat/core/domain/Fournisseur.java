package com.amenal.amenalbackend.achat.core.domain;

import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Banque;

public class Fournisseur {
	private Integer id;
	private LocalDate dateFournisseur;
	private String nom;
	private String denominationSocial;
	private String adresse;
	private String ville;
	private String ice;
	private String idFiscale;
	private String villeRegistreCommerce;
	private String pattente;
	private String rib;
	private Boolean isBloque;
	
	private Banque banque;

	private List<EvaluationFournisseur> evaluations;
	private List<ContratPlafond> contratPlafonds;
	private List<ContratDlp> contratDlps;
	private List<ContactFournisseur> contacts;
	private List<AttestationRgf> attestationRgfs;
	
	public Fournisseur() {
		super();
	}
	
	public Fournisseur(Integer id, LocalDate dateFournisseur, String nom, String denominationSocial, String adresse,
			String ville, String ice, String idFiscale, String villeRegistreCommerce, String pattente, String rib,
			Boolean isBloque, Banque banque, List<EvaluationFournisseur> evaluations,
			List<ContratPlafond> contratPlafonds, List<ContratDlp> contratDlps, List<ContactFournisseur> contacts,
			List<AttestationRgf> attestationRgfs) {
		super();
		this.id = id;
		this.dateFournisseur = dateFournisseur;
		this.nom = nom;
		this.denominationSocial = denominationSocial;
		this.adresse = adresse;
		this.ville = ville;
		this.ice = ice;
		this.idFiscale = idFiscale;
		this.villeRegistreCommerce = villeRegistreCommerce;
		this.pattente = pattente;
		this.rib = rib;
		this.isBloque = isBloque;
		this.banque = banque;
		this.evaluations = evaluations;
		this.contratPlafonds = contratPlafonds;
		this.contratDlps = contratDlps;
		this.contacts = contacts;
		this.attestationRgfs = attestationRgfs;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDateFournisseur() {
		return dateFournisseur;
	}


	public void setDateFournisseur(LocalDate dateFournisseur) {
		this.dateFournisseur = dateFournisseur;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDenominationSocial() {
		return denominationSocial;
	}


	public void setDenominationSocial(String denominationSocial) {
		this.denominationSocial = denominationSocial;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getIce() {
		return ice;
	}


	public void setIce(String ice) {
		this.ice = ice;
	}


	public String getIdFiscale() {
		return idFiscale;
	}


	public void setIdFiscale(String idFiscale) {
		this.idFiscale = idFiscale;
	}


	public String getVilleRegistreCommerce() {
		return villeRegistreCommerce;
	}


	public void setVilleRegistreCommerce(String villeRegistreCommerce) {
		this.villeRegistreCommerce = villeRegistreCommerce;
	}


	public String getPattente() {
		return pattente;
	}


	public void setPattente(String pattente) {
		this.pattente = pattente;
	}


	public String getRib() {
		return rib;
	}


	public void setRib(String rib) {
		this.rib = rib;
	}


	public Boolean getIsBloque() {
		return isBloque;
	}


	public void setIsBloque(Boolean isBloque) {
		this.isBloque = isBloque;
	}


	public Banque getBanque() {
		return banque;
	}


	public void setBanque(Banque banque) {
		this.banque = banque;
	}


	public List<EvaluationFournisseur> getEvaluations() {
		return evaluations;
	}


	public void setEvaluations(List<EvaluationFournisseur> evaluations) {
		this.evaluations = evaluations;
	}


	public List<ContratPlafond> getContratPlafonds() {
		return contratPlafonds;
	}


	public void setContratPlafonds(List<ContratPlafond> contratPlafonds) {
		this.contratPlafonds = contratPlafonds;
	}


	public List<ContratDlp> getContratDlps() {
		return contratDlps;
	}


	public void setContratDlps(List<ContratDlp> contratDlps) {
		this.contratDlps = contratDlps;
	}


	public List<ContactFournisseur> getContacts() {
		return contacts;
	}


	public void setContacts(List<ContactFournisseur> contacts) {
		this.contacts = contacts;
	}


	public List<AttestationRgf> getAttestationRgfs() {
		return attestationRgfs;
	}


	public void setAttestationRgfs(List<AttestationRgf> attestationRgfs) {
		this.attestationRgfs = attestationRgfs;
	}


	

	
}
