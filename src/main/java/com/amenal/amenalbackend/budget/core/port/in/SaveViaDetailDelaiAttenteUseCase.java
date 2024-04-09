package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.budget.core.domain.Lot;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.LotDao;
import com.amenal.amenalbackend.budget.core.port.out.ProduitDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public class SaveViaDetailDelaiAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private DetailDelaiAttenteDao detailDelaiAttenteDao;

	public SaveViaDetailDelaiAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			
			DetailDelaiAttenteDao detailDelaiAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
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
		Produit produit = new Produit();
		produit.setDesignation(detailDelaiAttente.getProduit());
		produit.setMetre(detailDelaiAttente.getMetre());

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
		tache.setCleAttachement(detailDelaiAttente.getCle());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set DetailDelai:
		tache.setDateDebut(detailDelaiAttente.getDdb());
		tache.setDelai(detailDelaiAttente.getDlb());
		
		// Save DetailDelai:
		try {
			tache = tacheDao.updateTache(tache);
		} catch (DuplicateElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
