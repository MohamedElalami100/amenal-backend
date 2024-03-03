package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.domain.BudgetAchatAv;
import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.domain.DetailChargeAttente;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.domain.MetreAv;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.BudgetAchatAvDao;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeAttenteDao;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeDao;
import com.amenal.amenalbackend.application.project.port.out.LotDao;
import com.amenal.amenalbackend.application.project.port.out.MetreAvDao;
import com.amenal.amenalbackend.application.project.port.out.ProduitDao;
import com.amenal.amenalbackend.application.project.port.out.TacheDao;

public class SaveViaDetailChargeAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private DetailChargeDao detailChargeDao;
	private MetreAvDao metreDao;
	private BudgetAchatAvDao budgetAchatAvDao;
	private DetailChargeAttenteDao detailChargeAttenteDao;

	public SaveViaDetailChargeAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			DetailChargeDao detailChargeDao, MetreAvDao metreDao, BudgetAchatAvDao budgetAchatAvDao,
			DetailChargeAttenteDao detailChargeAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
		this.detailChargeDao = detailChargeDao;
		this.metreDao = metreDao;
		this.budgetAchatAvDao = budgetAchatAvDao;
		this.detailChargeAttenteDao = detailChargeAttenteDao;
	}

	private void validerDetailCharge(DetailChargeAttente detailChargeAttente) {
		String erreur = detailChargeAttente.getErreur();
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
		Avenant avenant = detailChargeAttente.getAvenant();

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
		produit.setDesignation(detailChargeAttente.getProduit());
		produit.setMetre(metre);

		// Save produit:
		produit = produitDao.saveProduit(produit);

		// Set Lot:
		Lot lot = new Lot();
		lot.setDesignation(detailChargeAttente.getLot());
		lot.setProject(produit.getMetre().getBudget().getAvenant().getProject());

		// Save lot:
		lot = lotDao.saveLot(lot);

		// Set Tache:
		Tache tache = new Tache();
		tache.setOrdreMef(detailChargeAttente.getOrdre());
		tache.setTitreActivite(detailChargeAttente.getActivite());
		tache.setProduit(produit);
		tache.setLot(lot);
		tache.setUnite(detailChargeAttente.getUpb());
		tache.setCleAttachement(detailChargeAttente.getCle());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set DetailCharge:
		DetailCharge detailCharge = new DetailCharge();
		detailCharge.setTache(tache);
		detailCharge.setDesignation(detailChargeAttente.getCharge());
		detailCharge.setUnite(detailChargeAttente.getUcb());
		detailCharge.setQte(detailChargeAttente.getQcb());
		detailCharge.setPrix(detailChargeAttente.getPcb());
		detailCharge.setLaUnitePrd(detailChargeAttente.getRcb());
		detailCharge.setLaQtePrd(detailChargeAttente.getQpb());
		detailCharge.setDateSaisie(detailChargeAttente.getDdb());

		// Save DetailCharge:
		detailChargeDao.saveDetailCharge(detailCharge);

	}

	public void valider(Integer id) {
		// get all detailChargeAttentes:
		List<DetailChargeAttente> detailChargeAttentes = detailChargeAttenteDao.getDetailChargeAttentesByAvenantId(id);

		for (DetailChargeAttente detailChargeAttente : detailChargeAttentes) {
			try {
				validerDetailCharge(detailChargeAttente);
			} catch (Exception e) {
			}
			try {
				// delete detailChargeAttent from the table
				detailChargeAttenteDao.deleteDetailChargeAttente(detailChargeAttente.getId());
			} catch (Exception e) {
				
			}
		}

	}
}
