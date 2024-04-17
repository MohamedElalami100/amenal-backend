package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.core.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.core.domain.Lot;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteDao;
import com.amenal.amenalbackend.budget.core.port.out.GrpQualiteDao;
import com.amenal.amenalbackend.budget.core.port.out.LotDao;
import com.amenal.amenalbackend.budget.core.port.out.ProduitDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public class SaveViaDetailQualiteAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private DetailQualiteDao detailQualiteDao;
	private GrpQualiteDao grpQualiteDao;

	private DetailQualiteAttenteDao detailQualiteAttenteDao;
	
	public SaveViaDetailQualiteAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			DetailQualiteDao detailQualiteDao, GrpQualiteDao grpQualiteDao, DetailQualiteAttenteDao detailQualiteAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
		this.detailQualiteDao = detailQualiteDao;
		this.grpQualiteDao = grpQualiteDao;
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
		Produit produit = new Produit();
		produit.setDesignation(detailQualiteAttente.getProduit());
		produit.setMetre(detailQualiteAttente.getMetre());

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
		tache.setOrdre(detailQualiteAttente.getOrdre());
		tache.setTitreActivite(detailQualiteAttente.getActivite());
		tache.setProduit(produit);
		tache.setLot(lot);
		tache.setUpb(detailQualiteAttente.getUpb());
		tache.setCle(detailQualiteAttente.getCle());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set GrpQualite:
		GrpQualite groupe = new GrpQualite();
		groupe.setTitre(detailQualiteAttente.getGroupe());
		groupe.setTache(tache);
		
		
		groupe = grpQualiteDao.saveGrpQualiteAndReturnItIfExists(groupe);
		
		// Save DetailQualite:
		DetailQualite detailQualite = new DetailQualite();
		detailQualite.setPointDeControle(detailQualiteAttente.getPointDeControle());
		detailQualite.setGroupe(groupe);

		try {
			// Save DetailQualite:
			detailQualiteDao.saveDetailQualite(detailQualite);
		} catch (DuplicateElementException e) {
		}

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
