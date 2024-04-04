package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.DetailDelaiAttenteEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.DetailDelaiAttenteRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.budget.application.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.budget.application.domain.Lot;
import com.amenal.amenalbackend.budget.application.domain.Produit;
import com.amenal.amenalbackend.budget.application.domain.Tache;
import com.amenal.amenalbackend.budget.application.port.out.DetailDelaiAttenteDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailDelaiAttenteDaoAdapter implements DetailDelaiAttenteDao {

	@Autowired
	private DetailDelaiAttenteRepository detailDelaiAttenteRepository;

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailDelaiAttente findDetailDelaiAttenteById(Integer id) {
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = detailDelaiAttenteRepository.findById(id).get();
		DetailDelaiAttente detailDelaiAttente = modelMapper.map(detailDelaiAttenteEntity, DetailDelaiAttente.class);
		return detailDelaiAttente;
	}

	@Override
	public List<DetailDelaiAttente> findAllDetailDelaiAttentes() {
		List<DetailDelaiAttenteEntity> detailDelaiAttenteEntities = detailDelaiAttenteRepository.findAll();
		return detailDelaiAttenteEntities.stream()
				.map(detailDelaiAttenteEntity -> modelMapper.map(detailDelaiAttenteEntity, DetailDelaiAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailDelaiAttente saveDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente) {
		
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = modelMapper.map(detailDelaiAttente,
				DetailDelaiAttenteEntity.class);
		DetailDelaiAttenteEntity savedEntity = detailDelaiAttenteRepository.save(detailDelaiAttenteEntity);
		return modelMapper.map(savedEntity, DetailDelaiAttente.class);
	}
	
	@Override
	public DetailDelaiAttente saveDetailDelaiAttenteWithErreur(DetailDelaiAttente detailDelaiAttente) {
		detailDelaiAttente.setErreur(getErreurMessage(detailDelaiAttente));
		
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = modelMapper.map(detailDelaiAttente,
				DetailDelaiAttenteEntity.class);
		DetailDelaiAttenteEntity savedEntity = detailDelaiAttenteRepository.save(detailDelaiAttenteEntity);
		return modelMapper.map(savedEntity, DetailDelaiAttente.class);
	}

	@Override
	public DetailDelaiAttente updateDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente) {
		DetailDelaiAttenteEntity existingEntity = detailDelaiAttenteRepository.findById(detailDelaiAttente.getId())
				.orElseThrow();
		detailDelaiAttente.setErreur(getErreurMessage(detailDelaiAttente));

		// Use ModelMapper to map non-null properties from DetailDelaiAttente to
		// existingEntity
		modelMapper.map(detailDelaiAttente, existingEntity);

		DetailDelaiAttenteEntity updatedEntity = detailDelaiAttenteRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailDelaiAttente.class);
	}

	@Override
	public void deleteDetailDelaiAttente(Integer id) {
		// Check if DetailDelaiAttente with the given ID exists
		DetailDelaiAttenteEntity detailDelaiAttenteEntity = detailDelaiAttenteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailDelaiAttenteRepository.delete(detailDelaiAttenteEntity);
	}

	private String getErreurMessage(DetailDelaiAttente detailDelaiAttente) {
		// set error message:

		// check if a one of the first fields is null or contain an empty string
		String erreur = (detailDelaiAttente.getProduit() == null || detailDelaiAttente.getProduit() == ""
				? "| Produit Vide "
				: "")
				+ (detailDelaiAttente.getLot() == null || detailDelaiAttente.getLot() == "" ? "| Lot Vide " : "")
				+ (detailDelaiAttente.getActivite() == null || detailDelaiAttente.getActivite() == ""
						? "| Activite Vide "
						: "")
				+ (detailDelaiAttente.getUpb() == null || detailDelaiAttente.getUpb() == "" ? "| Unite Vide " : "");
		if (erreur != "")
			return "(1)" + erreur;

		// TACHE EXISTE DANS UN AUTRE AVENANT
		List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
				.getTachesInOtherAvenants(detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
		List<Tache> tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
				.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

		for (Tache tache : tachesInOtherAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())) {
				return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
			}
		}

		// get taches in same avenant:
		List<TacheEntity> tachesInSameAvenantEntities = tacheRepository
				.getTachesByAvenantId(detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
		List<Tache> tachesInSameAvenants = tachesInSameAvenantEntities.stream()
				.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

		List<DetailDelaiAttenteEntity> otherDetailEntities = detailDelaiAttenteRepository
				.getOtherDetailsById(detailDelaiAttente.getId(), detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
		List<DetailDelaiAttente> otherDetailsInAttente = otherDetailEntities.stream()
				.map(detailEntity -> modelMapper.map(detailEntity, DetailDelaiAttente.class))
				.collect(Collectors.toList());

		for (DetailDelaiAttente detail : otherDetailsInAttente) {
			Produit produit = new Produit();
			produit.setDesignation(detail.getProduit());

			Lot lot = new Lot();
			lot.setDesignation(detail.getLot());

			Tache tache = new Tache();
			tache.setOrdreMef(detail.getOrdre());
			tache.setTitreActivite(detail.getActivite());
			tache.setProduit(produit);
			tache.setLot(lot);
			tache.setUnite(detail.getUpb());
			tache.setCleAttachement(detail.getCle());
			tache.setDateDebut(detail.getDdb());
			tache.setDelai(detail.getDlb());

			tachesInSameAvenants.add(tache);
		}
		// TACHE LIEE A DEUX PRODUITS DIFFERENTS
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
					&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())) {
				return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
			}
		}

		// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())
					&& !tache.getUnite().equalsIgnoreCase(detailDelaiAttente.getUpb())) {
				return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
			}
		}

		// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())
					&& tache.getCleAttachement() != detailDelaiAttente.getCle()) {
				return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
			}
		}

		// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
		Boolean activitePrincipaleExist = false;

		if (!detailDelaiAttente.getCle()) {
			for (Tache tache : tachesInSameAvenants) {
				if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())) {
					if (tache.getCleAttachement()) {
						activitePrincipaleExist = true;
					}
				}
			}
		} else {
			activitePrincipaleExist = true;
		}

		if (!activitePrincipaleExist)
			return "(6)TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE";

		// check if a one of the last fields is null or contain an empty string
		erreur = (detailDelaiAttente.getDdb() == null ? "| Ddb Vide " : "")
				+ (detailDelaiAttente.getDlb() == null || detailDelaiAttente.getDlb() == 0 ? "| Delai null " : "");
		if (erreur != "")
			return "(7)" + erreur;

		// DOUBLONS DE LIGNES
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())) {
				return "(8)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
			}
		}

		// RCT
		return "(9)RCT";

	}

	@Override
	public List<DetailDelaiAttente> getDetailDelaiAttentesByAvenantId(Integer id) {
		List<DetailDelaiAttenteEntity> detailDelaiAttenteEntities = detailDelaiAttenteRepository.getDetailDelaiAttentesByAvenantId(id);
		return detailDelaiAttenteEntities.stream()
				.map(detailDelaiAttenteEntity -> modelMapper.map(detailDelaiAttenteEntity, DetailDelaiAttente.class))
				.collect(Collectors.toList());	
		}

}
