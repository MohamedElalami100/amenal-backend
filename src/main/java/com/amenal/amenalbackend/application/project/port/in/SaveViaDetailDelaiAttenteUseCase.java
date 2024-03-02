package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.domain.BudgetAchatAv;
import com.amenal.amenalbackend.application.project.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.domain.MetreAv;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.BudgetAchatAvDao;
import com.amenal.amenalbackend.application.project.port.out.DetailDelaiAttenteDao;
import com.amenal.amenalbackend.application.project.port.out.LotDao;
import com.amenal.amenalbackend.application.project.port.out.MetreAvDao;
import com.amenal.amenalbackend.application.project.port.out.ProduitDao;
import com.amenal.amenalbackend.application.project.port.out.TacheDao;

public class SaveViaDetailDelaiAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private MetreAvDao metreDao;
	private BudgetAchatAvDao budgetAchatAvDao;
	private DetailDelaiAttenteDao detailDelaiAttenteDao;

	public SaveViaDetailDelaiAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			MetreAvDao metreDao, BudgetAchatAvDao budgetAchatAvDao,
			DetailDelaiAttenteDao detailDelaiAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
		this.metreDao = metreDao;
		this.budgetAchatAvDao = budgetAchatAvDao;
		this.detailDelaiAttenteDao = detailDelaiAttenteDao;
	}

	private void validerDetailDelai(DetailDelaiAttente detailDelaiAttente) {
		String erreur = detailDelaiAttente.getErreur();
		if (erreur == null || erreur.isEmpty()) {
			return;
		}

		if (erreur.length() < 1) {
			return;
		}
		Integer codeErreur = Integer.parseInt(erreur.charAt(1) + "");

		if (codeErreur <= 8) {
			return;
		}

		// if the erreur is RST:
		// Set Produit:
		Avenant avenant = detailDelaiAttente.getAvenant();

		BudgetAchatAv budget = budgetAchatAvDao.getOneBudgetAchatByAvenantId(avenant.getId());
		if (budget == null) {
			BudgetAchatAv rowBudget = new BudgetAchatAv();
			rowBudget.setAvenant(avenant);
			budget = budgetAchatAvDao.saveBudgetAchatAv(rowBudget);
		}

		MetreAv metre = metreDao.getOneMetreByAvenantId(avenant.getId());
		if (metre == null) {
			MetreAv rowMetre = new MetreAv();
			rowMetre.setBudget(budget);
			metre = metreDao.saveMetreAv(metre);
		}

		Produit produit = new Produit();
		produit.setDesignation(detailDelaiAttente.getProduit());
		produit.setMetre(metre);

		// Save produit:
		produit = produitDao.saveProduit(produit);

		// Set Lot:
		Lot lot = new Lot();
		lot.setDesignation(detailDelaiAttente.getLot());
		lot.setProject(produit.getMetre().getBudget().getAvenant().getProject());

		// Save lot:
		lot = lotDao.saveLot(lot);

		// Set Tache:
		Tache tache = new Tache();
		tache.setOrdreMef(detailDelaiAttente.getOrdre());
		tache.setTitreActivite(detailDelaiAttente.getActivite());
		tache.setProduit(produit);
		tache.setLot(lot);
		tache.setUnite(detailDelaiAttente.getUpb());
		if (!detailDelaiAttente.getCle())
			tache.setActivitePrincipale(new Tache());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set DetailDelai:
		tache.setDateDebut(detailDelaiAttente.getDdb());
		tache.setDelai(detailDelaiAttente.getDlb());
		
		// Save DetailDelai:
		tache = tacheDao.updateTache(tache);

	}

	public void valider(Integer id) {
		// get all detailDelaiAttentes:
		List<DetailDelaiAttente> detailDelaiAttentes = detailDelaiAttenteDao.getDetailDelaiAttentesByAvenantId(id);

		for (DetailDelaiAttente detailDelaiAttente : detailDelaiAttentes) {
			try {
				validerDetailDelai(detailDelaiAttente);
			} catch (Exception e) {
			}
			try {
				// delete detailDelaiAttente from the table
				detailDelaiAttenteDao.deleteDetailDelaiAttente(detailDelaiAttente.getId());
			} catch (Exception e) {
			}
		}

	}
}
