package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailCharge;
import com.amenal.amenalbackend.budget.core.domain.DetailChargeAttente;
import com.amenal.amenalbackend.budget.core.domain.Lot;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeDao;
import com.amenal.amenalbackend.budget.core.port.out.LotDao;
import com.amenal.amenalbackend.budget.core.port.out.ProduitDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public class SaveViaDetailChargeAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private DetailChargeDao detailChargeDao;
	private DetailChargeAttenteDao detailChargeAttenteDao;

	public SaveViaDetailChargeAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			DetailChargeDao detailChargeDao, DetailChargeAttenteDao detailChargeAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
		this.detailChargeDao = detailChargeDao;
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
		Produit produit = new Produit();
		produit.setDesignation(detailChargeAttente.getProduit());
		produit.setMetre(detailChargeAttente.getMetre());

		// Save produit:
		produit = produitDao.saveProduit(produit);

		// Set Lot:
		Lot lot = new Lot();
		lot.setDesignation(detailChargeAttente.getLot());
		lot.setProject(produit.getMetre().getAvenant().getProject());

		// Save lot:
		lot = lotDao.saveLot(lot);

		// Set Tache:
		Tache tache = new Tache();
		tache.setOrdre(detailChargeAttente.getOrdre());
		tache.setTitreActivite(detailChargeAttente.getActivite());
		tache.setProduit(produit);
		tache.setLot(lot);
		tache.setUpb(detailChargeAttente.getUpb());
		tache.setCle(detailChargeAttente.getCle());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set DetailCharge:
		DetailCharge detailCharge = new DetailCharge();
		detailCharge.setTache(tache);
		detailCharge.setOrdre(detailChargeAttente.getOrdre());
		detailCharge.setCharge(detailChargeAttente.getCharge());
		detailCharge.setUcb(detailChargeAttente.getUcb());
		detailCharge.setQcb(detailChargeAttente.getQcb());
		detailCharge.setPcb(detailChargeAttente.getPcb());
		detailCharge.setRcb(detailChargeAttente.getRcb());
		detailCharge.setQpb(detailChargeAttente.getQpb());
		detailCharge.setDdb(detailChargeAttente.getDdb());

		// Save DetailCharge:
		try {
			detailChargeDao.saveDetailCharge(detailCharge);
		} catch (DuplicateElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
