package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.utils.core.domain.Colorable;
import com.amenal.amenalbackend.utils.core.domain.HasSons;
import com.amenal.amenalbackend.utils.core.domain.HasSons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Project extends Colorable {
	private Integer id;
	private String project;
	private String abreviation;
	private Boolean envoiAlrt;
	private Boolean cloture;
	private String object;
	private String lieu;
	private String personneDeContact;
	private String nomAgent;
	private Boolean valide;
	private String cCode;
	private String vCode;
	private String gsm;
	private String doc;
	private String mCode;
	private String refProject;
	private Boolean demandeValidation;
	private Boolean demandeArchivache;
	private Integer idSupp;
	private Boolean valideSansCnx;
	private Boolean archiveSansCnx;
	private Boolean deValideSansCnx;
	private Boolean deArchiveSansCnx;
	private String contactOfficiel;
	private String gsmOfficiel;
	private LocalDate dateDbtIni;
	private Integer dlaIni;
	private LocalDate dateDbtFin;
	private Integer dlaFin;
	private LocalDate dateDbtCnt;
	private Integer dlaCntr;
	private LocalDate dateSaisie;
	private LocalDate dateOuverture;
	private String observation;
	private String etat;
	private String contrat;
	// Fk objects:
	private Client client;
	private Dossier dossier;
	private Personnel responsable;

	private List<Lot> lots;

	private List<Avenant> avenants;

	public Project(Integer id, String project, String abreviation, Boolean envoiAlrt, Boolean cloture, String object,
			String lieu, String personneDeContact, String nomAgent, Boolean valide, String cCode, String vCode,
			String gsm, String doc, String mCode, String refProject, Boolean demandeValidation,
			Boolean demandeArchivache, Integer idSupp, Boolean valideSansCnx, Boolean archiveSansCnx,
			Boolean deValideSansCnx, Boolean deArchiveSansCnx, String contactOfficiel, String gsmOfficiel,
			LocalDate dateDbtIni, Integer dlaIni, LocalDate dateDbtFin, Integer dlaFin, LocalDate dateDbtCnt,
			Integer dlaCntr, LocalDate dateSaisie, LocalDate dateOuverture, String observation, String etat,
			String contrat, Client client, Dossier dossier, Personnel responsable) {
		super();
		this.id = id;
		this.project = project;
		this.abreviation = abreviation;
		this.envoiAlrt = envoiAlrt;
		this.cloture = cloture;
		this.object = object;
		this.lieu = lieu;
		this.personneDeContact = personneDeContact;
		this.nomAgent = nomAgent;
		this.valide = valide;
		this.cCode = cCode;
		this.vCode = vCode;
		this.gsm = gsm;
		this.doc = doc;
		this.mCode = mCode;
		this.refProject = refProject;
		this.demandeValidation = demandeValidation;
		this.demandeArchivache = demandeArchivache;
		this.idSupp = idSupp;
		this.valideSansCnx = valideSansCnx;
		this.archiveSansCnx = archiveSansCnx;
		this.deValideSansCnx = deValideSansCnx;
		this.deArchiveSansCnx = deArchiveSansCnx;
		this.contactOfficiel = contactOfficiel;
		this.gsmOfficiel = gsmOfficiel;
		this.dateDbtIni = dateDbtIni;
		this.dlaIni = dlaIni;
		this.dateDbtFin = dateDbtFin;
		this.dlaFin = dlaFin;
		this.dateDbtCnt = dateDbtCnt;
		this.dlaCntr = dlaCntr;
		this.dateSaisie = dateSaisie;
		this.dateOuverture = dateOuverture;
		this.observation = observation;
		this.etat = etat;
		this.contrat = contrat;
		this.client = client;
		this.dossier = dossier;
		this.responsable = responsable;
	}

	@Override
	public List<List<HasSons>> getSons() {
		List<List<HasSons>> sons = new ArrayList<>();
		List<HasSons> colorableAvenants = new ArrayList<>();
		List<HasSons> colorableLots = new ArrayList<>();
		if (avenants != null)
			colorableAvenants = avenants.stream()
					.map(avenant -> (HasSons) avenant)
					.collect(Collectors.toList());
		if (lots != null)
			colorableLots = lots.stream()
					.map(lot -> (HasSons) lot)
					.collect(Collectors.toList());
		sons.add(colorableAvenants);
		sons.add(colorableLots);
		return sons;
	}

	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<>();
		errors.add("pas de avenants associé");
		errors.add("pas de lots associé");
		return errors;
	}

	public Project() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public Boolean getEnvoiAlrt() {
		return envoiAlrt;
	}

	public void setEnvoiAlrt(Boolean envoiAlrt) {
		this.envoiAlrt = envoiAlrt;
	}

	public Boolean getCloture() {
		return cloture;
	}

	public void setCloture(Boolean cloture) {
		this.cloture = cloture;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getPersonneDeContact() {
		return personneDeContact;
	}

	public void setPersonneDeContact(String personneDeContact) {
		this.personneDeContact = personneDeContact;
	}

	public String getNomAgent() {
		return nomAgent;
	}

	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
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

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public String getRefProject() {
		return refProject;
	}

	public void setRefProject(String refProject) {
		this.refProject = refProject;
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

	public Boolean getDeArchiveSansCnx() {
		return deArchiveSansCnx;
	}

	public void setDeArchiveSansCnx(Boolean deArchiveSansCnx) {
		this.deArchiveSansCnx = deArchiveSansCnx;
	}

	public String getContactOfficiel() {
		return contactOfficiel;
	}

	public void setContactOfficiel(String contactOfficiel) {
		this.contactOfficiel = contactOfficiel;
	}

	public String getGsmOfficiel() {
		return gsmOfficiel;
	}

	public void setGsmOfficiel(String gsmOfficiel) {
		this.gsmOfficiel = gsmOfficiel;
	}

	public LocalDate getDateDbtIni() {
		return dateDbtIni;
	}

	public void setDateDbtIni(LocalDate dateDbtIni) {
		this.dateDbtIni = dateDbtIni;
	}

	public Integer getDlaIni() {
		return dlaIni;
	}

	public void setDlaIni(Integer dlaIni) {
		this.dlaIni = dlaIni;
	}

	public LocalDate getDateDbtFin() {
		return dateDbtFin;
	}

	public void setDateDbtFin(LocalDate dateDbtFin) {
		this.dateDbtFin = dateDbtFin;
	}

	public Integer getDlaFin() {
		return dlaFin;
	}

	public void setDlaFin(Integer dlaFin) {
		this.dlaFin = dlaFin;
	}

	public LocalDate getDateDbtCnt() {
		return dateDbtCnt;
	}

	public void setDateDbtCnt(LocalDate dateDbtCnt) {
		this.dateDbtCnt = dateDbtCnt;
	}

	public Integer getDlaCntr() {
		return dlaCntr;
	}

	public void setDlaCntr(Integer dlaCntr) {
		this.dlaCntr = dlaCntr;
	}

	public LocalDate getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(LocalDate dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public LocalDate getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getContrat() {
		return contrat;
	}

	public void setContrat(String contrat) {
		this.contrat = contrat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	public Personnel getResponsable() {
		return responsable;
	}

	public void setResponsable(Personnel responsable) {
		this.responsable = responsable;
	}

	public List<Avenant> getAvenants() {
		return avenants;
	}

	public void setAvenants(List<Avenant> avenants) {
		this.avenants = avenants;
	}

	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}
}
