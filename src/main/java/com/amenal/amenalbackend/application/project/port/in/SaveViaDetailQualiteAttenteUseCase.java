package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.domain.BudgetAchatAv;
import com.amenal.amenalbackend.application.project.domain.DetailQualite;
import com.amenal.amenalbackend.application.project.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.application.project.domain.GrpQualite;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.domain.MetreAv;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.BudgetAchatAvDao;
import com.amenal.amenalbackend.application.project.port.out.DetailQualiteAttenteDao;
import com.amenal.amenalbackend.application.project.port.out.DetailQualiteDao;
import com.amenal.amenalbackend.application.project.port.out.GrpQualiteDao;
import com.amenal.amenalbackend.application.project.port.out.LotDao;
import com.amenal.amenalbackend.application.project.port.out.MetreAvDao;
import com.amenal.amenalbackend.application.project.port.out.ProduitDao;
import com.amenal.amenalbackend.application.project.port.out.TacheDao;

public class SaveViaDetailQualiteAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private DetailQualiteDao detailQualiteDao;
	private GrpQualiteDao grpQualiteDao;
	private MetreAvDao metreDao;
	private BudgetAchatAvDao budgetAchatAvDao;
	private DetailQualiteAttenteDao detailQualiteAttenteDao;
	
	public SaveViaDetailQualiteAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			DetailQualiteDao detailQualiteDao, GrpQualiteDao grpQualiteDao, MetreAvDao metreDao,
			BudgetAchatAvDao budgetAchatAvDao, DetailQualiteAttenteDao detailQualiteAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
		this.detailQualiteDao = detailQualiteDao;
		this.grpQualiteDao = grpQualiteDao;
		this.metreDao = metreDao;
		this.budgetAchatAvDao = budgetAchatAvDao;
		this.detailQualiteAttenteDao = detailQualiteAttenteDao;
	}

	private void validerDetailQualite(DetailQualiteAttente detailQualiteAttente) {
		String erreur = detailQualiteAttente.getErreur();
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
		Avenant avenant = detailQualiteAttente.getAvenant();

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
		produit.setDesignation(detailQualiteAttente.getProduit());
		produit.setMetre(metre);

		// Save produit:
		produit = produitDao.saveProduit(produit);

		// Set Lot:
		Lot lot = new Lot();
		lot.setDesignation(detailQualiteAttente.getLot());
		lot.setProject(produit.getMetre().getBudget().getAvenant().getProject());

		// Save lot:
		lot = lotDao.saveLot(lot);

		// Set Tache:
		Tache tache = new Tache();
		tache.setOrdreMef(detailQualiteAttente.getOrdre());
		tache.setTitreActivite(detailQualiteAttente.getActivite());
		tache.setProduit(produit);
		tache.setLot(lot);
		tache.setUnite(detailQualiteAttente.getUpb());
		tache.setCleAttachement(detailQualiteAttente.getCle());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set GrpQualite:
		GrpQualite groupe = new GrpQualite();
		groupe.setTitre(detailQualiteAttente.getGroupe());
		groupe.setTache(tache);
		
		// Save GrpQualite
		groupe = grpQualiteDao.saveGrpQualite(groupe);

		// Save DetailQualite:
		DetailQualite detailQualite = new DetailQualite();
		detailQualite.setAffaire(detailQualiteAttente.getPointDeControle());
		detailQualite.setGroupe(groupe);

		// Save DetailQualite:
		detailQualiteDao.saveDetailQualite(detailQualite);

	}

	public void valider(Integer id) {
		// get all detailQualiteAttentes:
		List<DetailQualiteAttente> detailQualiteAttentes = detailQualiteAttenteDao.getDetailQualiteAttentesByAvenantId(id);

		for (DetailQualiteAttente detailQualiteAttente : detailQualiteAttentes) {
			try {
				validerDetailQualite(detailQualiteAttente);
			} catch (Exception e) {
			}
			try {
				// delete detailQualiteAttente from the table
				detailQualiteAttenteDao.deleteDetailQualiteAttente(detailQualiteAttente.getId());
			} catch (Exception e) {
			}
		}

	}
}
