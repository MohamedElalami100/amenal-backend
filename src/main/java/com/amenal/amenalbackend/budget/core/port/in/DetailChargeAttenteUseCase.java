package com.amenal.amenalbackend.budget.core.port.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.DetailCharge;
import com.amenal.amenalbackend.budget.core.domain.DetailChargeAttente;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeAttenteDao;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeDao;
import com.amenal.amenalbackend.budget.core.port.out.TacheDao;

public class DetailChargeAttenteUseCase {

    private DetailChargeAttenteDao detailChargeAttenteDao;

    private TacheDao tacheDao;

    private DetailChargeDao detailChargeDao;

    public DetailChargeAttenteUseCase(DetailChargeAttenteDao detailChargeAttenteDao) {
        this.detailChargeAttenteDao = detailChargeAttenteDao;
    }

    public DetailChargeAttente findDetailChargeAttenteById(Integer id) {
        return detailChargeAttenteDao.findDetailChargeAttenteById(id);
    }

    public List<DetailChargeAttente> findAllDetailChargeAttentes() {
        return detailChargeAttenteDao.findAllDetailChargeAttentes();
    }

    public List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id) {
        return detailChargeAttenteDao.getDetailChargeAttentesByAvenantId(id);
    }

    public DetailChargeAttente saveDetailChargeAttente(DetailChargeAttente detailChargeAttente) {
        List<DetailChargeAttente> detailChargeAttenteList = new ArrayList<>();
        try {
            detailChargeAttenteList = detailChargeAttenteDao.
                    getDetailChargeAttentesByAvenantId(detailChargeAttente.getMetre().getAvenant().getId());
        } catch (NullPointerException e) {
        }

        List<DetailChargeAttente> filteredList = detailChargeAttenteList.stream().filter((detailChargeAttenteObject) ->
                detailChargeAttenteObject.getProduit() != null && detailChargeAttenteObject.getLot() != null
                        && (
                        detailChargeAttenteObject.getProduit().equalsIgnoreCase(detailChargeAttente.getProduit())
                                || detailChargeAttenteObject.getLot().equalsIgnoreCase(detailChargeAttente.getLot())))
                .collect(Collectors.toList());
        filteredList.add(detailChargeAttente);

        List<DetailChargeAttente> updatedList = updateErreurs(filteredList);
        if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
        return null;
    }

    public DetailChargeAttente updateDetailChargeAttente(DetailChargeAttente detailChargeAttente) {
        DetailChargeAttente newDetailChargeAttente = detailChargeAttenteDao.updateDetailChargeAttente(detailChargeAttente);
        List<DetailChargeAttente> detailChargeAttenteList = new ArrayList<>();
        try {
            detailChargeAttenteList = detailChargeAttenteDao.
                    getDetailChargeAttentesByAvenantId(newDetailChargeAttente.getMetre().getAvenant().getId());
        } catch (NullPointerException e) {
        }

        List<DetailChargeAttente> filteredList = detailChargeAttenteList.stream().filter((detailChargeAttenteObject) ->
                detailChargeAttenteObject.getProduit() != null && detailChargeAttenteObject.getLot() != null
                        && (
                        detailChargeAttenteObject.getProduit().equalsIgnoreCase(newDetailChargeAttente.getProduit())
                                || detailChargeAttenteObject.getLot().equalsIgnoreCase(newDetailChargeAttente.getLot())))
                .collect(Collectors.toList());
        filteredList.add(detailChargeAttente);

        List<DetailChargeAttente> updatedList = updateErreurs(filteredList);
        if(updatedList.size() > 0) return updatedList.get(updatedList.size()-1);
        return null;
    }

    public void deleteDetailChargeAttente(Integer id) {
        detailChargeAttenteDao.deleteDetailChargeAttente(id);
    }

    public List<DetailChargeAttente> saveAllDetailChargeAttente(List<DetailChargeAttente> detailChargeAttentes) {
        List<DetailChargeAttente> addedDetails = new ArrayList<>();

        for (DetailChargeAttente detailChargeAttente : detailChargeAttentes) {
            try {
                DetailChargeAttente addedDetail = detailChargeAttenteDao.saveDetailChargeAttente(detailChargeAttente);
                addedDetails.add(addedDetail);
            } catch (Exception e) {
            }
        }

        return updateErreurs(addedDetails);
    }

    private List<DetailChargeAttente> updateErreurs(List<DetailChargeAttente> detailChargeAttentes) {

        if (detailChargeAttentes.size() == 0) return new ArrayList<>();
        int i = 0;
        List<DetailChargeAttente> addedDetailsWithErreurs = new ArrayList<>();

        //get data needed for getting error message:
        List<Tache> tachesInOtherAvenants = new ArrayList<>();
        List<Tache> tachesInSameAvenants = new ArrayList<>();
        List<DetailCharge> otherDetailCharges = new ArrayList<>();

        try {
            tachesInOtherAvenants = tacheDao.getTachesInOtherAvenants(
                    detailChargeAttentes.get(0).getMetre().getAvenant().getId()
            );
            tachesInSameAvenants = tacheDao.getTachesByAvenantId(
                    detailChargeAttentes.get(0).getMetre().getAvenant().getId()
            );
            otherDetailCharges = detailChargeDao.getDetailChargesByAvenantId(
                    detailChargeAttentes.get(0).getMetre().getAvenant().getId());
        } catch (NullPointerException e) {
        }

        HashMap<String, String> erreursMap = new HashMap<>();

        for (DetailChargeAttente detailChargeAttente : detailChargeAttentes) {
            try {
                //memo errors of similar detailChargeAttentes:
                boolean calculateError = false;
                boolean skipTacheError = false;
                if (detailChargeAttente.getProduit() != null && detailChargeAttente.getLot() != null &&
                        detailChargeAttente.getActivite() != null) {
                    String detailKey = detailChargeAttente.getProduit() + detailChargeAttente.getLot()
                            + detailChargeAttente.getActivite();
                    if (erreursMap.get(detailKey) != null) {
                        if (Integer.parseInt(erreursMap.get(detailKey).charAt(1) + "") <= 6) {
                            detailChargeAttente.setErreur(erreursMap.get(detailKey));
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
                    List<DetailChargeAttente> otherDetailsInAttente = detailChargeAttentes.stream().filter(detail ->
                            detail.getId() != detailChargeAttente.getId()).collect(Collectors.toList());

                    detailChargeAttente.setErreur(detailChargeAttente.getAndCalculateErreurMessage(
                            tachesInOtherAvenants, tachesInSameAvenants,
                            otherDetailsInAttente, otherDetailCharges, skipTacheError
                    ));
                }

                //save detail with error
                DetailChargeAttente addedDetail = detailChargeAttenteDao.saveDetailChargeAttente(detailChargeAttente);
                addedDetailsWithErreurs.add(addedDetail);
                i++;
            } catch (Exception e) {
            }
        }

        return addedDetailsWithErreurs;
    }


}
