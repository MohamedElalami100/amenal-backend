package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.domain.DetailProduit;
import com.amenal.amenalbackend.budget.application.domain.DetailProduitAttente;
import com.amenal.amenalbackend.budget.application.domain.Lot;
import com.amenal.amenalbackend.budget.application.domain.Produit;
import com.amenal.amenalbackend.budget.application.domain.Tache;
import com.amenal.amenalbackend.budget.application.port.out.DetailProduitAttenteDao;
import com.amenal.amenalbackend.budget.application.port.out.DetailProduitDao;
import com.amenal.amenalbackend.budget.application.port.out.LotDao;
import com.amenal.amenalbackend.budget.application.port.out.ProduitDao;
import com.amenal.amenalbackend.budget.application.port.out.TacheDao;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public class SaveViaDetailProduitAttenteUseCase {
	private TacheDao tacheDao;
	private LotDao lotDao;
	private ProduitDao produitDao;
	private DetailProduitDao detailProduitDao;

	private DetailProduitAttenteDao detailProduitAttenteDao;

	public SaveViaDetailProduitAttenteUseCase(TacheDao tacheDao, LotDao lotDao, ProduitDao produitDao,
			DetailProduitDao detailProduitDao, DetailProduitAttenteDao detailProduitAttenteDao) {
		super();
		this.tacheDao = tacheDao;
		this.lotDao = lotDao;
		this.produitDao = produitDao;
		this.detailProduitDao = detailProduitDao;
		this.detailProduitAttenteDao = detailProduitAttenteDao;
	}

	private void validerDetailProduit(DetailProduitAttente detailProduitAttente) {
		String erreur = detailProduitAttente.getErreur();
		if (erreur == null || erreur.isEmpty()) {
			return;
		}

		if (erreur.length() < 1) {
			return;
		}
		Integer codeErreur = Integer.parseInt(erreur.charAt(1) + "");

		System.out.println(codeErreur);
		if (codeErreur <= 8) {
			return;
		}

		// if the erreur is RST:
		// Set Produit:
		Produit produit = new Produit();
		produit.setDesignation(detailProduitAttente.getProduit());
		produit.setMetre(detailProduitAttente.getMetre());

		// Save produit:
		produit = produitDao.saveProduit(produit);

		// Set Lot:
		Lot lot = new Lot();
		lot.setDesignation(detailProduitAttente.getLot());
		lot.setProject(produit.getMetre().getBudget().getAvenant().getProject());

		// Save lot:
		lot = lotDao.saveLot(lot);

		// Set Tache:
		Tache tache = new Tache();
		tache.setOrdreMef(detailProduitAttente.getOrdre());
		tache.setTitreActivite(detailProduitAttente.getActivite());
		tache.setProduit(produit);
		tache.setLot(lot);
		tache.setUnite(detailProduitAttente.getUpb());
		tache.setCleAttachement(detailProduitAttente.getCle());

		// Save Tache:
		tache = tacheDao.saveTache(tache);

		// Set DetailProduit:
		DetailProduit detailProduit = new DetailProduit();
		detailProduit.setTache(tache);
		detailProduit.setReference(detailProduitAttente.getReference());
		detailProduit.setNbr(detailProduitAttente.getNbr());
		detailProduit.setDim1(detailProduitAttente.getDim1());
		detailProduit.setDim2(detailProduitAttente.getDim2());
		detailProduit.setDim3(detailProduitAttente.getDim3());

		// Save DetailProduit:
		try {
			detailProduitDao.saveDetailProduit(detailProduit);
		} catch (DuplicateElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void valider(Integer id) {
		// get all detailProduitAttentes:
		List<DetailProduitAttente> detailProduitAttentes = detailProduitAttenteDao
				.getDetailProduitAttentesByAvenantId(id);

		for (DetailProduitAttente detailProduitAttente : detailProduitAttentes) {
			try {
				validerDetailProduit(detailProduitAttente);
			} catch (Exception e) {
			}
			try {
				// delete detailProduitAttente from the table
				detailProduitAttenteDao.deleteDetailProduitAttente(detailProduitAttente.getId());
			} catch (Exception e) {
			}
		}

	}
}
