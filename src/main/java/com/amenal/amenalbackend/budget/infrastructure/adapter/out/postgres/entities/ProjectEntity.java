package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_project")
	private Integer id;

	@Column(name = "project")
	private String project;

	@Column(name = "abreviation")
	private String abreviation;

	@Column(name = "envoi_alrt")
	private Boolean envoiAlrt;

	@Column(name = "cloture")
	private Boolean cloture;

	@Column(name = "object")
	private String object;

	@Column(name = "lieu")
	private String lieu;

	@Column(name = "personne_de_contact")
	private String personneDeContact;

	@Column(name = "nom_agent")
	private String nomAgent;

	@Column(name = "valide")
	private Boolean valide;

	@Column(name = "c_code")
	private String cCode;

	@Column(name = "v_code")
	private String vCode;

	@Column(name = "gsm")
	private String gsm;

	@Column(name = "doc")
	private String doc;

	@Column(name = "m_code")
	private String mCode;

	@Column(name = "ref_project")
	private String refProject;

	@Column(name = "demande_validation")
	private Boolean demandeValidation;

	@Column(name = "demande_archivache")
	private Boolean demandeArchivache;

	@Column(name = "id_supp")
	private Integer idSupp;

	@Column(name = "valide_sans_cnx")
	private Boolean valideSansCnx;

	@Column(name = "archive_sans_cnx")
	private Boolean archiveSansCnx;

	@Column(name = "de_valide_sans_cnx")
	private Boolean deValideSansCnx;

	@Column(name = "de_archive_sans_cnx")
	private Boolean deArchiveSansCnx;

	@Column(name = "contact_officiel")
	private String contactOfficiel;

	@Column(name = "gsm_officiel")
	private String gsmOfficiel;

	@Column(name = "date_dbt_ini")
	private LocalDate dateDbtIni;

	@Column(name = "dla_ini")
	private Integer dlaIni;

	@Column(name = "date_dbt_fin")
	private LocalDate dateDbtFin;

	@Column(name = "dla_fin")
	private Integer dlaFin;

	@Column(name = "date_dbt_cnt")
	private LocalDate dateDbtCnt;

	@Column(name = "dla_cntr")
	private Integer dlaCntr;

	@Column(name = "date_saisie")
	private LocalDate dateSaisie;

	@Column(name = "date_ouverture")
	private LocalDate dateOuverture;

	@Column(name = "observation")
	private String observation;

	@Column(name = "etat")
	private String etat;

	@Column(name = "contrat")
	private String contrat;

	// Fk objects:
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client")
	private ClientEntity client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier")
	private DossierEntity dossier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_responsable")
	private PersonnelEntity responsable;

	public ProjectEntity(Integer id, String project, String abreviation, Boolean envoiAlrt, Boolean cloture,
			String object, String lieu, String personneDeContact, String nomAgent, Boolean valide, String cCode,
			String vCode, String gsm, String doc, String mCode, String refProject, Boolean demandeValidation,
			Boolean demandeArchivache, Integer idSupp, Boolean valideSansCnx, Boolean archiveSansCnx,
			Boolean deValideSansCnx, Boolean deArchiveSansCnx, String contactOfficiel, String gsmOfficiel,
			LocalDate dateDbtIni, Integer dlaIni, LocalDate dateDbtFin, Integer dlaFin, LocalDate dateDbtCnt,
			Integer dlaCntr, LocalDate dateSaisie, LocalDate dateOuverture, String observation, String etat,
			String contrat, ClientEntity client, DossierEntity dossier, PersonnelEntity responsable) {
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

	public ProjectEntity() {
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

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public DossierEntity getDossier() {
		return dossier;
	}

	public void setDossier(DossierEntity dossier) {
		this.dossier = dossier;
	}

	public PersonnelEntity getResponsable() {
		return responsable;
	}

	public void setResponsable(PersonnelEntity responsable) {
		this.responsable = responsable;
	}

	// business methods:
	public LocalDate getDateFinIni() {
		try {
			return dateDbtIni.plusDays(dlaIni - 1);
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateFinFin() {
		try {
			return dateDbtFin.plusDays(dlaFin - 1);
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDate getDateFinCntr() {
		try {
			return dateDbtCnt.plusDays(dlaCntr - 1);
		} catch (Exception e) {
			return null;
		}
	}
}
