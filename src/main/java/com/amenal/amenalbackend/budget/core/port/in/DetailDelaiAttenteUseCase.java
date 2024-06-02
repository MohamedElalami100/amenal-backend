package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;

public class DetailDelaiAttenteUseCase {

	private DetailDelaiAttenteDao detailDelaiAttenteDao;

	private TacheDao tacheDao;

	public DetailDelaiAttenteUseCase(DetailDelaiAttenteDao detailDelaiAttenteDao) {
		this.detailDelaiAttenteDao = detailDelaiAttenteDao;
	}

	public DetailDelaiAttente findDetailDelaiAttenteById(Integer id) {
		return detailDelaiAttenteDao.findDetailDelaiAttenteById(id);
	}

	public List<DetailDelaiAttente> findAllDetailDelaiAttentes() {
		return detailDelaiAttenteDao.findAllDetailDelaiAttentes();
	}

	public List<DetailDelaiAttente> getDetailDelaiAttentesByAvenantId(Integer id) {
		return detailDelaiAttenteDao.getDetailDelaiAttentesByAvenantId(id);
	}

	public DetailDelaiAttente saveDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente) {
		List<DetailDelaiAttente> detailDelaiAttenteList = new ArrayList<>();
		try {
			detailDelaiAttenteList = detailDelaiAttenteDao.
					getDetailDelaiAttentesByAvenantId(detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		List<DetailDelaiAttente> filteredList = detailDelaiAttenteList.stream().filter((detailDelaiAttenteObject) ->
						detailDelaiAttenteObject.getProduit() != null && detailDelaiAttenteObject.getLot() != null
								&& (
								detailDelaiAttenteObject.getProduit().equalsIgnoreCase(detailDelaiAttente.getProduit())
										|| detailDelaiAttenteObject.getLot().equalsIgnoreCase(detailDelaiAttente.getLot())))
				.collect(Collectors.toList());
		filteredList.add(detailDelaiAttente);

		List<DetailDelaiAttente> updatedList = updateErreurs(filteredList);
		if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
		return null;
	}

	public DetailDelaiAttente updateDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente) {
		DetailDelaiAttente newDetailDelaiAttente = detailDelaiAttenteDao.updateDetailDelaiAttente(detailDelaiAttente);
		List<DetailDelaiAttente> detailDelaiAttenteList = new ArrayList<>();
		try {
			detailDelaiAttenteList = detailDelaiAttenteDao.
					getDetailDelaiAttentesByAvenantId(newDetailDelaiAttente.getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		List<DetailDelaiAttente> filteredList = detailDelaiAttenteList.stream().filter((detailDelaiAttenteObject) ->
						detailDelaiAttenteObject.getProduit() != null && detailDelaiAttenteObject.getLot() != null
								&& (
								detailDelaiAttenteObject.getProduit().equalsIgnoreCase(newDetailDelaiAttente.getProduit())
										|| detailDelaiAttenteObject.getLot().equalsIgnoreCase(newDetailDelaiAttente.getLot())))
				.collect(Collectors.toList());
		filteredList.add(detailDelaiAttente);

		List<DetailDelaiAttente> updatedList = updateErreurs(filteredList);
		if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
		return null;
	}

	public void deleteDetailDelaiAttente(Integer id) {
		detailDelaiAttenteDao.deleteDetailDelaiAttente(id);
	}

	public List<DetailDelaiAttente> saveAllDetailDelaiAttente(List<DetailDelaiAttente> detailDelaiAttentes) {
		List<DetailDelaiAttente> addedDetails = new ArrayList<>();

		for (DetailDelaiAttente detailDelaiAttente : detailDelaiAttentes) {
			try {
				DetailDelaiAttente addedDetail = detailDelaiAttenteDao.saveDetailDelaiAttente(detailDelaiAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}

		return updateErreurs(addedDetails);
	}

	private List<DetailDelaiAttente> updateErreurs(List<DetailDelaiAttente> detailDelaiAttentes) {

		if (detailDelaiAttentes.size() == 0) return new ArrayList<>();
		int i = 0;
		List<DetailDelaiAttente> addedDetailsWithErreurs = new ArrayList<>();

		//get data needed for getting error message:
		List<Tache> tachesInOtherAvenants = new ArrayList<>();
		List<Tache> tachesInSameAvenants = new ArrayList<>();

		try {
			tachesInOtherAvenants = tacheDao.getTachesInOtherAvenants(
					detailDelaiAttentes.get(0).getMetre().getBudget().getAvenant().getId()
			);
			tachesInSameAvenants = tacheDao.getTachesByAvenantId(
					detailDelaiAttentes.get(0).getMetre().getBudget().getAvenant().getId()
			);
		} catch (NullPointerException e) {
		}

		HashMap<String, String> erreursMap = new HashMap<>();

		for (DetailDelaiAttente detailDelaiAttente : detailDelaiAttentes) {
			try {
				//memo errors of similar detailDelaiAttentes:
				boolean calculateError = false;
				boolean skipTacheError = false;
				if (detailDelaiAttente.getProduit() != null && detailDelaiAttente.getLot() != null &&
						detailDelaiAttente.getActivite() != null) {
					String detailKey = detailDelaiAttente.getProduit() + detailDelaiAttente.getLot()
							+ detailDelaiAttente.getActivite();
					if (erreursMap.get(detailKey) != null) {
						if (Integer.parseInt(erreursMap.get(detailKey).charAt(1) + "") <= 6) {
							detailDelaiAttente.setErreur(erreursMap.get(detailKey));
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
					List<DetailDelaiAttente> otherDetailsInAttente = detailDelaiAttentes.stream().filter(detail ->
							detail.getId() != detailDelaiAttente.getId()).collect(Collectors.toList());

					detailDelaiAttente.setErreur(detailDelaiAttente.getAndCalculateErreurMessage(
							tachesInOtherAvenants, tachesInSameAvenants,
							otherDetailsInAttente, skipTacheError
					));
				}

				//save detail with error
				DetailDelaiAttente addedDetail = detailDelaiAttenteDao.saveDetailDelaiAttente(detailDelaiAttente);
				addedDetailsWithErreurs.add(addedDetail);
				i++;
			} catch (Exception e) {
			}
		}

		return addedDetailsWithErreurs;
	}


}
