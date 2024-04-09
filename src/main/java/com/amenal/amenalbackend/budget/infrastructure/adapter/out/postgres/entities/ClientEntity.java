package com.amenal.amenalbackend.budget.adapter.out.postgres.entities;

import java.math.BigInteger;
import java.time.LocalDate;

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
@Table(name = "client")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Integer id;

	@Column(name = "nom_client")
	private String nom;

	private String adresse;
	private String observation;
	private String idFiscale;
	private String patenten;
	private String activite;
	private LocalDate dateOuvertureCompte;
	private BigInteger compteComptable;
	private BigInteger codeComptable;
	private Boolean compteAutre;
	private BigInteger comptePaiementParDeffauts;
	private Integer delaiPaiementParDeffauts;
	private LocalDate dateSaise;
	private String ice;
	private Boolean compteCloture;
	private String rib;
	private String cCode;
	private String vCode;
	private Boolean valide;
	private Boolean demandeValidation;
	private Boolean demandeArchivache;
	private String aCode;
	private String genre;
	private String doc;
	private Integer responsableCompte;
	private String mCode;
	private String reference;
	private Integer idSupp;
	private Boolean valideSansCnx;
	private Boolean archiveSansCnx;
	private Boolean deValideSansCnx;
	private Integer plafond;
	private String rc;
	private String villeRc;
	private String patente;

	// Fk objects:
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_banque")
	private BanqueEntity banque;

	public ClientEntity(Integer id, String nom, String adresse, String observation, String idFiscale, String patenten,
				String activite, LocalDate dateOuvertureCompte, BigInteger compteComptable, BigInteger codeComptable,
				Boolean compteAutre, BigInteger comptePaiementParDeffauts, Integer delaiPaiementParDeffauts,
				LocalDate dateSaise, String ice, Boolean compteCloture, String rib, String cCode, String vCode,
				Boolean valide, Boolean demandeValidation, Boolean demandeArchivache, String aCode, String genre,
				String doc, Integer responsableCompte, String mCode, String reference, Integer idSupp,
				Boolean valideSansCnx, Boolean archiveSansCnx, Boolean deValideSansCnx, Integer plafond, String rc,
				String villeRc, String patente, BanqueEntity banque) {
			super();
			this.id = id;
			this.nom = nom;
			this.adresse = adresse;
			this.observation = observation;
			this.idFiscale = idFiscale;
			this.patenten = patenten;
			this.activite = activite;
			this.dateOuvertureCompte = dateOuvertureCompte;
			this.compteComptable = compteComptable;
			this.codeComptable = codeComptable;
			this.compteAutre = compteAutre;
			this.comptePaiementParDeffauts = comptePaiementParDeffauts;
			this.delaiPaiementParDeffauts = delaiPaiementParDeffauts;
			this.dateSaise = dateSaise;
			this.ice = ice;
			this.compteCloture = compteCloture;
			this.rib = rib;
			this.cCode = cCode;
			this.vCode = vCode;
			this.valide = valide;
			this.demandeValidation = demandeValidation;
			this.demandeArchivache = demandeArchivache;
			this.aCode = aCode;
			this.genre = genre;
			this.doc = doc;
			this.responsableCompte = responsableCompte;
			this.mCode = mCode;
			this.reference = reference;
			this.idSupp = idSupp;
			this.valideSansCnx = valideSansCnx;
			this.archiveSansCnx = archiveSansCnx;
			this.deValideSansCnx = deValideSansCnx;
			this.plafond = plafond;
			this.rc = rc;
			this.villeRc = villeRc;
			this.patente = patente;
			this.banque = banque;
		}

	public  ClientEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getIdFiscale() {
		return idFiscale;
	}

	public void setIdFiscale(String idFiscale) {
		this.idFiscale = idFiscale;
	}

	public String getPatenten() {
		return patenten;
	}

	public void setPatenten(String patenten) {
		this.patenten = patenten;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public LocalDate getDateOuvertureCompte() {
		return dateOuvertureCompte;
	}

	public void setDateOuvertureCompte(LocalDate dateOuvertureCompte) {
		this.dateOuvertureCompte = dateOuvertureCompte;
	}

	public BigInteger getCompteComptable() {
		return compteComptable;
	}

	public void setCompteComptable(BigInteger compteComptable) {
		this.compteComptable = compteComptable;
	}

	public BigInteger getCodeComptable() {
		return codeComptable;
	}

	public void setCodeComptable(BigInteger codeComptable) {
		this.codeComptable = codeComptable;
	}

	public Boolean getCompteAutre() {
		return compteAutre;
	}

	public void setCompteAutre(Boolean compteAutre) {
		this.compteAutre = compteAutre;
	}

	public BigInteger getComptePaiementParDeffauts() {
		return comptePaiementParDeffauts;
	}

	public void setComptePaiementParDeffauts(BigInteger comptePaiementParDeffauts) {
		this.comptePaiementParDeffauts = comptePaiementParDeffauts;
	}

	public Integer getDelaiPaiementParDeffauts() {
		return delaiPaiementParDeffauts;
	}

	public void setDelaiPaiementParDeffauts(Integer delaiPaiementParDeffauts) {
		this.delaiPaiementParDeffauts = delaiPaiementParDeffauts;
	}

	public LocalDate getDateSaise() {
		return dateSaise;
	}

	public void setDateSaise(LocalDate dateSaise) {
		this.dateSaise = dateSaise;
	}

	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public Boolean getCompteCloture() {
		return compteCloture;
	}

	public void setCompteCloture(Boolean compteCloture) {
		this.compteCloture = compteCloture;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public Boolean getDemandeValidation() {
		return demandeValidation;
	}

	public void setDemandeValidation(Boolean demandeValidation) {
		this.demandeValidation = demandeValidation;
	}

	public Boolean getDemandeArchivache() {
		return demandeArchivache;
	}

	public void setDemandeArchivache(Boolean demandeArchivache) {
		this.demandeArchivache = demandeArchivache;
	}

	public String getaCode() {
		return aCode;
	}

	public void setaCode(String aCode) {
		this.aCode = aCode;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Integer getResponsableCompte() {
		return responsableCompte;
	}

	public void setResponsableCompte(Integer responsableCompte) {
		this.responsableCompte = responsableCompte;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getIdSupp() {
		return idSupp;
	}

	public void setIdSupp(Integer idSupp) {
		this.idSupp = idSupp;
	}

	public Boolean getValideSansCnx() {
		return valideSansCnx;
	}

	public void setValideSansCnx(Boolean valideSansCnx) {
		this.valideSansCnx = valideSansCnx;
	}

	public Boolean getArchiveSansCnx() {
		return archiveSansCnx;
	}

	public void setArchiveSansCnx(Boolean archiveSansCnx) {
		this.archiveSansCnx = archiveSansCnx;
	}

	public Boolean getDeValideSansCnx() {
		return deValideSansCnx;
	}

	public void setDeValideSansCnx(Boolean deValideSansCnx) {
		this.deValideSansCnx = deValideSansCnx;
	}

	public Integer getPlafond() {
		return plafond;
	}

	public void setPlafond(Integer plafond) {
		this.plafond = plafond;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public String getVilleRc() {
		return villeRc;
	}

	public void setVilleRc(String villeRc) {
		this.villeRc = villeRc;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public BanqueEntity getBanque() {
		return banque;
	}

	public void setBanque(BanqueEntity banque) {
		this.banque = banque;
	}

}
