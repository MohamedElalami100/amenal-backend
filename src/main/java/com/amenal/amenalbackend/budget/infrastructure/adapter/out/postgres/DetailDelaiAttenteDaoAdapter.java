package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.*;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailDelaiAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.port.out.DetailDelaiAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailDelaiAttenteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;

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
		detailDelaiAttenteRepository.findById(detailDelaiAttente.getId())
				.orElseThrow();
		detailDelaiAttente.setErreur(getErreurMessage(detailDelaiAttente));
		// Use ModelMapper to map non-null properties from DetailDelaiAttente to
		// existingEntity
		DetailDelaiAttenteEntity newEntity = modelMapper.map(detailDelaiAttente, DetailDelaiAttenteEntity.class);

		DetailDelaiAttenteEntity updatedEntity = detailDelaiAttenteRepository.save(newEntity);
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
		String erreur = "";
		try {
			erreur = (detailDelaiAttente.getProduit() == null || detailDelaiAttente.getProduit().isEmpty()
					? "| Produit Vide "
					: "")
					+ (detailDelaiAttente.getLot() == null || detailDelaiAttente.getLot().isEmpty() ? "| Lot Vide "
							: "")
					+ (detailDelaiAttente.getActivite() == null || detailDelaiAttente.getActivite().isEmpty()
							? "| Activite Vide "
							: "")
					+ (detailDelaiAttente.getUpb() == null || detailDelaiAttente.getUpb().isEmpty() ? "| Unite Vide "
							: "");
			if (!erreur.isEmpty())
				return "(1)" + erreur;
		} catch (NullPointerException e) {
		}
		
		List<Tache> tachesInOtherAvenants = new ArrayList<>();
		try {
			// TACHE EXISTE DANS UN AUTRE AVENANT
			List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
					.getTachesInOtherAvenants(detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
			tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (Tache tache : tachesInOtherAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())) {
					return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
				} 
			} catch (NullPointerException e) {
				
			}
		}

		List<Tache> tachesInSameAvenants = new ArrayList<>();

		List<DetailDelaiAttente> otherDetailsInAttente = new ArrayList<>();
		try {
			// get taches in same avenant:
			List<TacheEntity> tachesInSameAvenantEntities = tacheRepository
					.getTachesByAvenantId(detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
			tachesInSameAvenants = tachesInSameAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
			List<DetailDelaiAttenteEntity> otherDetailEntities = detailDelaiAttenteRepository.getOtherDetailsById(
					detailDelaiAttente.getId(), detailDelaiAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailsInAttente = otherDetailEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailDelaiAttente.class))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

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
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
						&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())) {
					return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
				} 
			} catch (NullPointerException e) {
				
			}
		}

		// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())
						&& !tache.getUnite().equalsIgnoreCase(detailDelaiAttente.getUpb())) {
					return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
				} 
			} catch (NullPointerException e) {
				
			}
		}

		// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())
						&& tache.getCleAttachement() != detailDelaiAttente.getCle()) {
					return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
				} 
			} catch (NullPointerException e) {
				
			}
		}

		try {
			// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
			Boolean activitePrincipaleExist = false;
			if (!detailDelaiAttente.getCle()) {
				for (Tache tache : tachesInSameAvenants) {
					try {
						if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
								&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
								&& tache.getProduit().getDesignation()
										.equalsIgnoreCase(detailDelaiAttente.getProduit())) {
							if (tache.getCleAttachement()) {
								activitePrincipaleExist = true;
							}
						} 
					} catch (NullPointerException e) {
						
					}
				}
			} else {
				activitePrincipaleExist = true;
			}
			if (!activitePrincipaleExist)
				return "(6)TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE";
		} catch (NullPointerException e) {
			
		}
		try {
			// check if a one of the last fields is null or contain an empty string
			erreur = (detailDelaiAttente.getDdb() == null ? "| Ddb Vide " : "")
					+ (detailDelaiAttente.getDlb() == null || detailDelaiAttente.getDlb() == 0 ? "| Delai null " : "");
			if (!erreur.isEmpty())
				return "(7)" + erreur;
		} catch (NullPointerException e) {
			
		}
		// DOUBLONS DE LIGNES
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailDelaiAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailDelaiAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailDelaiAttente.getProduit())) {
					return "(8)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
				} 
			} catch (NullPointerException e) {
				
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
