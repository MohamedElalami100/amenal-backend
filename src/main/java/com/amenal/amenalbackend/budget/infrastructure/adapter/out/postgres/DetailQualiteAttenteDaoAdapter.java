package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.*;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteAttenteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailQualiteAttenteDaoAdapter implements DetailQualiteAttenteDao {

	@Autowired
	private DetailQualiteAttenteRepository detailQualiteAttenteRepository;

	@Autowired
	private DetailQualiteRepository detailQualiteRepository;

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailQualiteAttente findDetailQualiteAttenteById(Integer id) {
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = detailQualiteAttenteRepository.findById(id).get();
		DetailQualiteAttente detailQualiteAttente = modelMapper.map(detailQualiteAttenteEntity,
				DetailQualiteAttente.class);
		return detailQualiteAttente;
	}

	@Override
	public List<DetailQualiteAttente> findAllDetailQualiteAttentes() {
		List<DetailQualiteAttenteEntity> detailQualiteAttenteEntities = detailQualiteAttenteRepository.findAll();
		return detailQualiteAttenteEntities.stream().map(
				detailQualiteAttenteEntity -> modelMapper.map(detailQualiteAttenteEntity, DetailQualiteAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailQualiteAttente saveDetailQualiteAttente(DetailQualiteAttente detailQualiteAttente) {
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = modelMapper.map(detailQualiteAttente,
				DetailQualiteAttenteEntity.class);
		DetailQualiteAttenteEntity savedEntity = detailQualiteAttenteRepository.save(detailQualiteAttenteEntity);
		return modelMapper.map(savedEntity, DetailQualiteAttente.class);
	}

	@Override
	public DetailQualiteAttente saveDetailQualiteAttenteWithErreur(DetailQualiteAttente detailQualiteAttente) {
		detailQualiteAttente.setErreur(getErreurMessage(detailQualiteAttente));

		DetailQualiteAttenteEntity detailQualiteAttenteEntity = modelMapper.map(detailQualiteAttente,
				DetailQualiteAttenteEntity.class);
		DetailQualiteAttenteEntity savedEntity = detailQualiteAttenteRepository.save(detailQualiteAttenteEntity);
		return modelMapper.map(savedEntity, DetailQualiteAttente.class);
	}

	@Override
	public DetailQualiteAttente updateDetailQualiteAttente(DetailQualiteAttente detailQualiteAttente) {
		detailQualiteAttenteRepository.findById(detailQualiteAttente.getId())
				.orElseThrow();
		detailQualiteAttente.setErreur(getErreurMessage(detailQualiteAttente));
		// Use ModelMapper to map non-null properties from DetailQualiteAttente to
		// existingEntity
		DetailQualiteAttenteEntity newEntity = modelMapper.map(detailQualiteAttente, DetailQualiteAttenteEntity.class);

		DetailQualiteAttenteEntity updatedEntity = detailQualiteAttenteRepository.save(newEntity);
		return modelMapper.map(updatedEntity, DetailQualiteAttente.class);
	}
	
	@Override
	public void deleteDetailQualiteAttente(Integer id) {
		// Check if DetailQualiteAttente with the given ID exists
		DetailQualiteAttenteEntity detailQualiteAttenteEntity = detailQualiteAttenteRepository.findById(id)
				.orElseThrow();

		// Delete the entity
		detailQualiteAttenteRepository.delete(detailQualiteAttenteEntity);
	}

	private String getErreurMessage(DetailQualiteAttente detailQualiteAttente) {
		// set error message:

		// check if a one of the first fields is null or contain an empty string
		String erreur = "";
		try {
			erreur = (detailQualiteAttente.getProduit() == null || detailQualiteAttente.getProduit().isEmpty()
					? "| Produit Vide "
					: "")
					+ (detailQualiteAttente.getLot() == null || detailQualiteAttente.getLot().isEmpty() ? "| Lot Vide "
							: "")
					+ (detailQualiteAttente.getActivite() == null || detailQualiteAttente.getActivite().isEmpty()
							? "| Activite Vide "
							: "")
					+ (detailQualiteAttente.getUpb() == null || detailQualiteAttente.getUpb().isEmpty()
							? "| Unite Vide "
							: "");
			if (!erreur.isEmpty())
				return "(1)" + erreur;
		} catch (NullPointerException e) {
		}
		
		List<Tache> tachesInOtherAvenants = new ArrayList<>();
		try {
			// TACHE EXISTE DANS UN AUTRE AVENANT
			List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
					.getTachesInOtherAvenants(detailQualiteAttente.getMetre().getBudget().getAvenant().getId());
			tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (Tache tache : tachesInOtherAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailQualiteAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailQualiteAttente.getLot())) {
					return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
				} 
			} catch (NullPointerException e) {
			}
		}

		List<Tache> tachesInSameAvenants = new ArrayList<>();

		List<DetailQualiteAttente> otherDetailsInAttente = new ArrayList<>();
		try {
			// get taches in same avenant:
			List<TacheEntity> tachesInSameAvenantEntities = tacheRepository
					.getTachesByAvenantId(detailQualiteAttente.getMetre().getBudget().getAvenant().getId());
			tachesInSameAvenants = tachesInSameAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
			List<DetailQualiteAttenteEntity> otherDetailEntities = detailQualiteAttenteRepository.getOtherDetailsById(
					detailQualiteAttente.getId(), detailQualiteAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailsInAttente = otherDetailEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailQualiteAttente.class))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (DetailQualiteAttente detail : otherDetailsInAttente) {
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

			tachesInSameAvenants.add(tache);
		}
		// TACHE LIEE A DEUX PRODUITS DIFFERENTS
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailQualiteAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailQualiteAttente.getLot())
						&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailQualiteAttente.getProduit())) {
					return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
				} 
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}

		// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailQualiteAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailQualiteAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailQualiteAttente.getProduit())
						&& !tache.getUnite().equalsIgnoreCase(detailQualiteAttente.getUpb())) {
					return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
				} 
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}

		// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailQualiteAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailQualiteAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailQualiteAttente.getProduit())
						&& tache.getCleAttachement() != detailQualiteAttente.getCle()) {
					return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
				} 
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}

		try {
			// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
			Boolean activitePrincipaleExist = false;
			if (!detailQualiteAttente.getCle()) {
				for (Tache tache : tachesInSameAvenants) {
					try {
						if (tache.getTitreActivite().equalsIgnoreCase(detailQualiteAttente.getActivite())
								&& tache.getLot().getDesignation().equalsIgnoreCase(detailQualiteAttente.getLot())
								&& tache.getProduit().getDesignation()
										.equalsIgnoreCase(detailQualiteAttente.getProduit())) {
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
		
		// check if a one of the last fields is null or contain an empty string
		erreur = (detailQualiteAttente.getGroupe() == null || detailQualiteAttente.getGroupe().isEmpty() ? "| Groupe Vide "
				: "")
				+ (detailQualiteAttente.getPointDeControle() == null || detailQualiteAttente.getPointDeControle().isEmpty()
						? "| Point de controle Vide "
						: "");
		if (!erreur.isEmpty())
			return "(7)" + erreur;

		List<DetailQualite> otherDetailQualites = new ArrayList<>();
		try {
			// get detailQualites in same avenant:
			List<DetailQualiteEntity> otherDetailQualiteEntities = detailQualiteRepository
					.getDetailQualitesByAvenantId(detailQualiteAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailQualites = otherDetailQualiteEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailQualite.class))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (DetailQualiteAttente detail : otherDetailsInAttente) {
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

			GrpQualite groupe = new GrpQualite();
			groupe.setTitre(detail.getGroupe());
			groupe.setTache(tache);

			DetailQualite detailQualite = new DetailQualite();
			detailQualite.setAffaire(detail.getPointDeControle());
			detailQualite.setGroupe(groupe);

			otherDetailQualites.add(detailQualite);

		}

		// DOUBLONS DE LIGNES
		for (DetailQualite detailQualite : otherDetailQualites) {
			try {
				if (detailQualite.getGroupe().getTache().getTitreActivite()
						.equalsIgnoreCase(detailQualiteAttente.getActivite())
						&& detailQualite.getGroupe().getTache().getLot().getDesignation()
								.equalsIgnoreCase(detailQualiteAttente.getLot())
						&& detailQualite.getGroupe().getTache().getProduit().getDesignation()
								.equalsIgnoreCase(detailQualiteAttente.getProduit())
						&& detailQualite.getGroupe().getTitre().equalsIgnoreCase(detailQualiteAttente.getGroupe())
						&& detailQualite.getAffaire().equalsIgnoreCase(detailQualiteAttente.getPointDeControle())) {
					return "(8)DOUBLONS DE LIGNES";
				} 
			} catch (NullPointerException e) {
			}
		}

		// RCT
		return "(9)RCT";

	}

	@Override
	public List<DetailQualiteAttente> getDetailQualiteAttentesByAvenantId(Integer id) {
		List<DetailQualiteAttenteEntity> detailQualiteAttenteEntities = detailQualiteAttenteRepository
				.getDetailQualiteAttentesByAvenantId(id);
		return detailQualiteAttenteEntities.stream().map(
				detailQualiteAttenteEntity -> modelMapper.map(detailQualiteAttenteEntity, DetailQualiteAttente.class))
				.collect(Collectors.toList());
	}
}
