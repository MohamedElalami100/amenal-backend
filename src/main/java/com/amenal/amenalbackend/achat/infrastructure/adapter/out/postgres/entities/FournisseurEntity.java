package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.BanqueEntity;

@Entity
@Table(name = "fournisseur")
public class FournisseurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fournisseur")
    private Integer id;

    @Column(name = "date_fournisseur")
    private LocalDate dateFournisseur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "denomination_social")
    private String denominationSocial;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "ice")
    private String ice;

    @Column(name = "id_fiscale")
    private String idFiscale;

    @Column(name = "ville_registre_commerce")
    private String villeRegistreCommerce;

    @Column(name = "pattente")
    private String pattente;

    @Column(name = "rib")
    private String rib;

    @Column(name = "is_bloque")
    private Boolean isBloque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_banque")
    private BanqueEntity banque;

    @OneToMany(mappedBy = "fournisseur")
    private List<EvaluationFournisseurEntity> evaluations;

    @OneToMany(mappedBy = "fournisseur")
    private List<ContratPlafondEntity> contratPlafonds;

    @OneToMany(mappedBy = "fournisseur")
    private List<ContratDlpEntity> contratDlps;

    @OneToMany(mappedBy = "fournisseur")
    private List<ContactFournisseurEntity> contacts;

    @OneToMany(mappedBy = "fournisseur")
    private List<AttestationRgfEntity> attestationRgfs;

	public FournisseurEntity() {
		super();
	}
	
	public FournisseurEntity(Integer id, LocalDate dateFournisseur, String nom, String denominationSocial, String adresse,
			String ville, String ice, String idFiscale, String villeRegistreCommerce, String pattente, String rib,
			Boolean isBloque, BanqueEntity banque, List<EvaluationFournisseurEntity> evaluations,
			List<ContratPlafondEntity> contratPlafonds, List<ContratDlpEntity> contratDlps, List<ContactFournisseurEntity> contacts,
			List<AttestationRgfEntity> attestationRgfs) {
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


	public BanqueEntity getBanque() {
		return banque;
	}


	public void setBanque(BanqueEntity banque) {
		this.banque = banque;
	}


	public List<EvaluationFournisseurEntity> getEvaluations() {
		return evaluations;
	}


	public void setEvaluations(List<EvaluationFournisseurEntity> evaluations) {
		this.evaluations = evaluations;
	}


	public List<ContratPlafondEntity> getContratPlafonds() {
		return contratPlafonds;
	}


	public void setContratPlafonds(List<ContratPlafondEntity> contratPlafonds) {
		this.contratPlafonds = contratPlafonds;
	}


	public List<ContratDlpEntity> getContratDlps() {
		return contratDlps;
	}


	public void setContratDlps(List<ContratDlpEntity> contratDlps) {
		this.contratDlps = contratDlps;
	}


	public List<ContactFournisseurEntity> getContacts() {
		return contacts;
	}


	public void setContacts(List<ContactFournisseurEntity> contacts) {
		this.contacts = contacts;
	}


	public List<AttestationRgfEntity> getAttestationRgfs() {
		return attestationRgfs;
	}


	public void setAttestationRgfs(List<AttestationRgfEntity> attestationRgfs) {
		this.attestationRgfs = attestationRgfs;
	}


	

	
}
