package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.core.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;

public class DetailQualiteAttenteUseCase {

	private DetailQualiteAttenteDao detailQualiteAttenteDao;

	private TacheDao tacheDao;

	private DetailQualiteDao detailQualiteDao;

	public DetailQualiteAttenteUseCase(DetailQualiteAttenteDao detailQualiteAttenteDao) {
		this.detailQualiteAttenteDao = detailQualiteAttenteDao;
	}

	public DetailQualiteAttente findDetailQualiteAttenteById(Integer id) {
		return detailQualiteAttenteDao.findDetailQualiteAttenteById(id);
	}

	public List<DetailQualiteAttente> findAllDetailQualiteAttentes() {
		return detailQualiteAttenteDao.findAllDetailQualiteAttentes();
	}

	public List<DetailQualiteAttente> getDetailQualiteAttentesByAvenantId(Integer id) {
		return detailQualiteAttenteDao.getDetailQualiteAttentesByAvenantId(id);
	}

	public DetailQualiteAttente saveDetailQualiteAttente(DetailQualiteAttente detailQualiteAttente) {
		List<DetailQualiteAttente> detailQualiteAttenteList = new ArrayList<>();
		try {
			detailQualiteAttenteList = detailQualiteAttenteDao.
					getDetailQualiteAttentesByAvenantId(detailQualiteAttente.getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		List<DetailQualiteAttente> filteredList = detailQualiteAttenteList.stream().filter((detailQualiteAttenteObject) ->
						detailQualiteAttenteObject.getProduit() != null && detailQualiteAttenteObject.getLot() != null
								&& (
								detailQualiteAttenteObject.getProduit().equalsIgnoreCase(detailQualiteAttente.getProduit())
										|| detailQualiteAttenteObject.getLot().equalsIgnoreCase(detailQualiteAttente.getLot())))
				.collect(Collectors.toList());
		filteredList.add(detailQualiteAttente);

		List<DetailQualiteAttente> updatedList = updateErreurs(filteredList);
		if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
		return null;
	}

	public DetailQualiteAttente updateDetailQualiteAttente(DetailQualiteAttente detailQualiteAttente) {
		DetailQualiteAttente newDetailQualiteAttente = detailQualiteAttenteDao.updateDetailQualiteAttente(detailQualiteAttente);
		List<DetailQualiteAttente> detailQualiteAttenteList = new ArrayList<>();
		try {
			detailQualiteAttenteList = detailQualiteAttenteDao.
					getDetailQualiteAttentesByAvenantId(newDetailQualiteAttente.getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		List<DetailQualiteAttente> filteredList = detailQualiteAttenteList.stream().filter((detailQualiteAttenteObject) ->
						detailQualiteAttenteObject.getProduit() != null && detailQualiteAttenteObject.getLot() != null
								&& (
								detailQualiteAttenteObject.getProduit().equalsIgnoreCase(newDetailQualiteAttente.getProduit())
										|| detailQualiteAttenteObject.getLot().equalsIgnoreCase(newDetailQualiteAttente.getLot())))
				.collect(Collectors.toList());
		filteredList.add(detailQualiteAttente);

		List<DetailQualiteAttente> updatedList = updateErreurs(filteredList);
		if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
		return null;
	}

	public void deleteDetailQualiteAttente(Integer id) {
		detailQualiteAttenteDao.deleteDetailQualiteAttente(id);
	}

	public List<DetailQualiteAttente> saveAllDetailQualiteAttente(List<DetailQualiteAttente> detailQualiteAttentes) {
		List<DetailQualiteAttente> addedDetails = new ArrayList<>();

		for (DetailQualiteAttente detailQualiteAttente : detailQualiteAttentes) {
			try {
				DetailQualiteAttente addedDetail = detailQualiteAttenteDao.saveDetailQualiteAttente(detailQualiteAttente);
				addedDetails.add(addedDetail);
			} catch (Exception e) {
			}
		}

		return updateErreurs(addedDetails);
	}

	private List<DetailQualiteAttente> updateErreurs(List<DetailQualiteAttente> detailQualiteAttentes) {

		if (detailQualiteAttentes.size() == 0) return new ArrayList<>();
		int i = 0;
		List<DetailQualiteAttente> addedDetailsWithErreurs = new ArrayList<>();

		//get data needed for getting error message:
		List<Tache> tachesInOtherAvenants = new ArrayList<>();
		List<Tache> tachesInSameAvenants = new ArrayList<>();
		List<DetailQualite> otherDetailQualites = new ArrayList<>();

		try {
			tachesInOtherAvenants = tacheDao.getTachesInOtherAvenants(
					detailQualiteAttentes.get(0).getMetre().getBudget().getAvenant().getId()
			);
			tachesInSameAvenants = tacheDao.getTachesByAvenantId(
					detailQualiteAttentes.get(0).getMetre().getBudget().getAvenant().getId()
			);
			otherDetailQualites = detailQualiteDao.getDetailQualitesByAvenantId(
					detailQualiteAttentes.get(0).getMetre().getBudget().getAvenant().getId());
		} catch (NullPointerException e) {
		}

		HashMap<String, String> erreursMap = new HashMap<>();

		for (DetailQualiteAttente detailQualiteAttente : detailQualiteAttentes) {
			try {
				//memo errors of similar detailQualiteAttentes:
				boolean calculateError = false;
				boolean skipTacheError = false;
				if (detailQualiteAttente.getProduit() != null && detailQualiteAttente.getLot() != null &&
						detailQualiteAttente.getActivite() != null) {
					String detailKey = detailQualiteAttente.getProduit() + detailQualiteAttente.getLot()
							+ detailQualiteAttente.getActivite();
					if (erreursMap.get(detailKey) != null) {
						if (Integer.parseInt(erreursMap.get(detailKey).charAt(1) + "") <= 6) {
							detailQualiteAttente.setErreur(erreursMap.get(detailKey));
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
					List<DetailQualiteAttente> otherDetailsInAttente = detailQualiteAttentes.stream().filter(detail ->
							detail.getId() != detailQualiteAttente.getId()).collect(Collectors.toList());

					detailQualiteAttente.setErreur(detailQualiteAttente.getAndCalculateErreurMessage(
							tachesInOtherAvenants, tachesInSameAvenants,
							otherDetailsInAttente, otherDetailQualites, skipTacheError
					));
				}

				//save detail with error
				DetailQualiteAttente addedDetail = detailQualiteAttenteDao.saveDetailQualiteAttente(detailQualiteAttente);
				addedDetailsWithErreurs.add(addedDetail);
				i++;
			} catch (Exception e) {
			}
		}

		return addedDetailsWithErreurs;
	}

}
