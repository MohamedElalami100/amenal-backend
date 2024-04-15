package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.core.domain.DetailProduitAttente;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;

public class DetailProduitAttenteUseCase {

	private DetailProduitAttenteDao detailProduitAttenteDao;

	private TacheDao tacheDao;

	private DetailProduitDao detailProduitDao;

	public DetailProduitAttenteUseCase(DetailProduitAttenteDao detailProduitAttenteDao) {
		this.detailProduitAttenteDao = detailProduitAttenteDao;
	}

	public DetailProduitAttente findDetailProduitAttenteById(Integer id) {
		return detailProduitAttenteDao.findDetailProduitAttenteById(id);
	}

	public List<DetailProduitAttente> findAllDetailProduitAttentes() {
		return detailProduitAttenteDao.findAllDetailProduitAttentes();
	}

	public List<DetailProduitAttente> getDetailProduitAttentesByAvenantId(Integer id) {
		return detailProduitAttenteDao.getDetailProduitAttentesByAvenantId(id);
	}

	public DetailProduitAttente saveDetailProduitAttente(DetailProduitAttente detailProduitAttente) {
		List<DetailProduitAttente> detailProduitAttenteList = new ArrayList<>();
		try {
			detailProduitAttenteList = detailProduitAttenteDao.
					getDetailProduitAttentesByAvenantId(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		List<DetailProduitAttente> filteredList = detailProduitAttenteList.stream().filter((detailProduitAttenteObject) ->
						detailProduitAttenteObject.getProduit() != null && detailProduitAttenteObject.getLot() != null
								&& (
								detailProduitAttenteObject.getProduit().equalsIgnoreCase(detailProduitAttente.getProduit())
										|| detailProduitAttenteObject.getLot().equalsIgnoreCase(detailProduitAttente.getLot())))
				.collect(Collectors.toList());
		filteredList.add(detailProduitAttente);

		List<DetailProduitAttente> updatedList = updateErreurs(filteredList);
		if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
		return null;
	}

	public DetailProduitAttente updateDetailProduitAttente(DetailProduitAttente detailProduitAttente) {
		DetailProduitAttente newDetailProduitAttente = detailProduitAttenteDao.updateDetailProduitAttente(detailProduitAttente);
		List<DetailProduitAttente> detailProduitAttenteList = new ArrayList<>();
		try {
			detailProduitAttenteList = detailProduitAttenteDao.
					getDetailProduitAttentesByAvenantId(newDetailProduitAttente.getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		List<DetailProduitAttente> filteredList = detailProduitAttenteList.stream().filter((detailProduitAttenteObject) ->
						detailProduitAttenteObject.getProduit() != null && detailProduitAttenteObject.getLot() != null
								&& (
								detailProduitAttenteObject.getProduit().equalsIgnoreCase(newDetailProduitAttente.getProduit())
										|| detailProduitAttenteObject.getLot().equalsIgnoreCase(newDetailProduitAttente.getLot())))
				.collect(Collectors.toList());
		filteredList.add(detailProduitAttente);

		List<DetailProduitAttente> updatedList = updateErreurs(filteredList);
		if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
		return null;
	}

	public void deleteDetailProduitAttente(Integer id) {
		detailProduitAttenteDao.deleteDetailProduitAttente(id);
	}

	public List<DetailProduitAttente> saveAllDetailProduitAttente(List<DetailProduitAttente> detailProduitAttentes) {
		List<DetailProduitAttente> addedDetails = new ArrayList<>();

		for (DetailProduitAttente detailProduitAttente : detailProduitAttentes) {
			try {
				DetailProduitAttente addedDetail = detailProduitAttenteDao.saveDetailProduitAttente(detailProduitAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}

		return updateErreurs(addedDetails);
	}

	private List<DetailProduitAttente> updateErreurs(List<DetailProduitAttente> detailProduitAttentes) {

		if (detailProduitAttentes.size() == 0) return new ArrayList<>();
		int i = 0;
		List<DetailProduitAttente> addedDetailsWithErreurs = new ArrayList<>();

		//get data needed for getting error message:
		List<Tache> tachesInOtherAvenants = new ArrayList<>();
		List<Tache> tachesInSameAvenants = new ArrayList<>();
		List<DetailProduit> otherDetailProduits = new ArrayList<>();

		try {
			tachesInOtherAvenants = tacheDao.getTachesInOtherAvenants(
					detailProduitAttentes.get(0).getMetre().getBudget().getAvenant().getId()
			);
			tachesInSameAvenants = tacheDao.getTachesByAvenantId(
					detailProduitAttentes.get(0).getMetre().getBudget().getAvenant().getId()
			);
			otherDetailProduits = detailProduitDao.getDetailProduitsByAvenantId(
					detailProduitAttentes.get(0).getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		HashMap<String, String> erreursMap = new HashMap<>();

		for (DetailProduitAttente detailProduitAttente : detailProduitAttentes) {
			try {
				//memo errors of similar detailProduitAttentes:
				boolean calculateError = false;
				boolean skipTacheError = false;
				if (detailProduitAttente.getProduit() != null && detailProduitAttente.getLot() != null &&
						detailProduitAttente.getActivite() != null) {
					String detailKey = detailProduitAttente.getProduit() + detailProduitAttente.getLot()
							+ detailProduitAttente.getActivite();
					if (erreursMap.get(detailKey) != null) {
						if (Integer.parseInt(erreursMap.get(detailKey).charAt(1) + "") <= 6) {
							detailProduitAttente.setErreur(erreursMap.get(detailKey));
						} else {
							skipTacheError = true;
							calculateError = true;
						}
					}
					else{
						calculateError = true;
					}
				}

				//only if the error is not already in the map
				if (calculateError) {
					List<DetailProduitAttente> otherDetailsInAttente = detailProduitAttentes.stream().filter(detail ->
							detail.getId() != detailProduitAttente.getId()).collect(Collectors.toList());

					detailProduitAttente.setErreur(detailProduitAttente.getAndCalculateErreurMessage(
							tachesInOtherAvenants, tachesInSameAvenants,
							otherDetailsInAttente, otherDetailProduits, skipTacheError
					));
				}

				//save detail with error
				DetailProduitAttente addedDetail = detailProduitAttenteDao.saveDetailProduitAttente(detailProduitAttente);
				addedDetailsWithErreurs.add(addedDetail);
				System.out.println(i);
				i++;
			} catch (Exception e) {
			}
		}

		return addedDetailsWithErreurs;
	}


}
