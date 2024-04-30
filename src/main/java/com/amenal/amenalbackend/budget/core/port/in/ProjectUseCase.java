package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.*;
import com.amenal.amenalbackend.budget.core.port.out.*;

public class ProjectUseCase {

	private ProjectDao projectDao;

	private AvenantDao avenantDao;

	private LotDao lotDao;

	private TacheDao tacheDao;

	private ProduitDao produitDao;

	private DetailProduitDao detailProduitDao;

	private DetailChargeDao detailChargeDao;

	public ProjectUseCase(ProjectDao projectDao, AvenantDao avenantDao, LotDao lotDao, TacheDao tacheDao,
			ProduitDao produitDao, DetailProduitDao detailProduitDao, DetailChargeDao detailChargeDao) {
		this.projectDao = projectDao;
		this.avenantDao = avenantDao;
		this.lotDao = lotDao;
		this.tacheDao = tacheDao;
		this.produitDao = produitDao;
		this.detailProduitDao = detailProduitDao;
		this.detailChargeDao = detailChargeDao;
	}

	public Project findProjectById(Integer id) {
		Project project = projectDao.findProjectById(id);
		// set status of project to 1 if it can be figer
		if (project.getStatus() != null && project.getStatus() == 0) {
			// set project sons:
			project = setProjectSons(project);

			project.peutEtreFiger();
		}
		return project;
	}

	public List<Project> findAllProjects() {
		List<Project> projects = projectDao.findAllProjects();
		for (Project project : projects) {
			// set status of project to 1 if it can be figer
			if (project.getStatus() != null && project.getStatus() == 0) {
				// set project sons:
				project = setProjectSons(project);

				project.peutEtreFiger();
			}

		}
		return projects;
	}

	public Project saveProject(Project project) {
		project.setStatus(0);
		return projectDao.saveProject(project);
	}

	public Project updateProject(Project project) {
		return projectDao.updateProject(project);
	}

	public void deleteProject(Integer id) {
		projectDao.deleteProject(id);
	}

	public String figer(Integer id) {
		// get project by id
		Project project = projectDao.findProjectById(id);

		// set project sons:
		project = setProjectSons(project);

		// figer project if it can be
		String erreur = project.figer();

		return erreur;
	}

	public Boolean valider(Integer id) {
		// get project by id
		Project project = projectDao.findProjectById(id);

		Boolean isValider = project.valider();
		if (isValider)
			projectDao.saveProject(project);

		return isValider;

	}

	private Project setProjectSons(Project project) {
		List<Avenant> avenants = avenantDao.getAvenantsByProjectId(project.getId());
		List<Lot> lots = lotDao.getLotsByProjectId(project.getId());

		// get avenants details:
		for (var avenant : avenants) {
			List<Tache> taches = tacheDao.getTachesByAvenantId(avenant.getId());
			List<Produit> produits = produitDao.getProduitsByAvenantId(avenant.getId());

			// get taches details:
			for (var tache : taches) {
				List<DetailProduit> detailProduits = detailProduitDao.getDetailProduitsByTacheId(tache.getId());
				List<DetailCharge> detailCharges = detailChargeDao.getDetailChargesByTacheId(tache.getId());
				tache.setDetailProduits(detailProduits);
				tache.setDetailCharges(detailCharges);
			}

			avenant.setTaches(taches);
			avenant.setProduits(produits);
		}

		project.setLots(lots);
		project.setAvenants(avenants);

		return project;
	}

}
