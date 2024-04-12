package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.core.domain.DetailProduitAttente;
import com.amenal.amenalbackend.budget.core.domain.Lot;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitAttenteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailProduitAttenteDaoAdapter implements DetailProduitAttenteDao {

	@Autowired
	private DetailProduitAttenteRepository detailProduitAttenteRepository;

	@Autowired
	private DetailProduitRepository detailProduitRepository;

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailProduitAttente findDetailProduitAttenteById(Integer id) {
		DetailProduitAttenteEntity detailProduitAttenteEntity = detailProduitAttenteRepository.findById(id).get();
		DetailProduitAttente detailProduitAttente = modelMapper.map(detailProduitAttenteEntity,
				DetailProduitAttente.class);
		return detailProduitAttente;
	}

	@Override
	public List<DetailProduitAttente> findAllDetailProduitAttentes() {
		List<DetailProduitAttenteEntity> detailProduitAttenteEntities = detailProduitAttenteRepository.findAll();
		return detailProduitAttenteEntities.stream().map(
				detailProduitAttenteEntity -> modelMapper.map(detailProduitAttenteEntity, DetailProduitAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailProduitAttente saveDetailProduitAttente(DetailProduitAttente detailProduitAttente) {
		DetailProduitAttenteEntity detailProduitAttenteEntity = modelMapper.map(detailProduitAttente,
				DetailProduitAttenteEntity.class);
		DetailProduitAttenteEntity savedEntity = detailProduitAttenteRepository.save(detailProduitAttenteEntity);
		return modelMapper.map(savedEntity, DetailProduitAttente.class);
	}

	@Override
	public DetailProduitAttente saveDetailProduitAttenteWithErreur(DetailProduitAttente detailProduitAttente) {
		detailProduitAttente.setErreur(getErreurMessage(detailProduitAttente));

		DetailProduitAttenteEntity detailProduitAttenteEntity = modelMapper.map(detailProduitAttente,
				DetailProduitAttenteEntity.class);
		DetailProduitAttenteEntity savedEntity = detailProduitAttenteRepository.save(detailProduitAttenteEntity);
		return modelMapper.map(savedEntity, DetailProduitAttente.class);
	}

	@Override
	public DetailProduitAttente updateDetailProduitAttente(DetailProduitAttente detailProduitAttente) {
		DetailProduitAttenteEntity existingEntity = detailProduitAttenteRepository
				.findById(detailProduitAttente.getId()).orElseThrow();
		detailProduitAttente.setErreur(getErreurMessage(detailProduitAttente));

		// Use ModelMapper to map non-null properties from DetailProduitAttente to
		// existingEntity
		modelMapper.map(detailProduitAttente, existingEntity);

		DetailProduitAttenteEntity updatedEntity = detailProduitAttenteRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailProduitAttente.class);
	}

	@Override
	public void deleteDetailProduitAttente(Integer id) {
		// Check if DetailProduitAttente with the given ID exists
		DetailProduitAttenteEntity detailProduitAttenteEntity = detailProduitAttenteRepository.findById(id)
				.orElseThrow();

		// Delete the entity
		detailProduitAttenteRepository.delete(detailProduitAttenteEntity);
	}

	private String getErreurMessage(DetailProduitAttente detailProduitAttente) {
		// set error message:

		// check if a one of the first fields is null or contain an empty string
		String erreur = "";
		try {
			erreur = (detailProduitAttente.getProduit() == null || detailProduitAttente.getProduit().isEmpty()
					? "| Produit Vide "
					: "")
					+ (detailProduitAttente.getLot() == null || detailProduitAttente.getLot().isEmpty() ? "| Lot Vide "
							: "")
					+ (detailProduitAttente.getActivite() == null || detailProduitAttente.getActivite().isEmpty()
							? "| Activite Vide "
							: "")
					+ (detailProduitAttente.getUpb() == null || detailProduitAttente.getUpb().isEmpty()
							? "| Unite Vide "
							: "");
			if (!erreur.isEmpty())
				return "(1)" + erreur;
		} catch (NullPointerException e) {
		}

		// TACHE EXISTE DANS UN AUTRE AVENANT
		List<Tache> tachesInOtherAvenants = new ArrayList<>();
		try {
			List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
					.getTachesInOtherAvenants(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
			tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		try {
			for (Tache tache : tachesInOtherAvenants) {
				if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())) {
					return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
				}
			}
		} catch (NullPointerException e) {
		}

		// get taches in same avenant:
		List<Tache> tachesInSameAvenants = new ArrayList<>();
		List<DetailProduitAttente> otherDetailsInAttente = new ArrayList<>();

		try {
			List<TacheEntity> tachesInSameAvenantEntities = tacheRepository
					.getTachesByAvenantId(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
			tachesInSameAvenants = tachesInSameAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
			List<DetailProduitAttenteEntity> otherDetailEntities = detailProduitAttenteRepository.getOtherDetailsById(
					detailProduitAttente.getId(), detailProduitAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailsInAttente = otherDetailEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailProduitAttente.class))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (DetailProduitAttente detail : otherDetailsInAttente) {
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
				if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
						&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())) {
					return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
				}
			} catch (NullPointerException e) {
			}
		}

		// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())
						&& !tache.getUnite().equalsIgnoreCase(detailProduitAttente.getUpb())) {
					return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
				}
			} catch (NullPointerException e) {
			}
		}

		// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())
						&& tache.getCleAttachement() != detailProduitAttente.getCle()) {
					return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
				}
			} catch (NullPointerException e) {
			}
		}

		try {
			// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
			Boolean activitePrincipaleExist = false;
			if (!detailProduitAttente.getCle()) {
				for (Tache tache : tachesInSameAvenants) {
					try {
						if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
								&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
								&& tache.getProduit().getDesignation()
										.equalsIgnoreCase(detailProduitAttente.getProduit())) {
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
			erreur = (detailProduitAttente.getProduit() == null || detailProduitAttente.getProduit().isEmpty()
					? "| Produit Vide "
					: "")
					+ (detailProduitAttente.getNbr() == null || detailProduitAttente.getNbr() == 0 ? "| Nbr null "
							: "");
			if (!erreur.isEmpty())
				return "(7)" + erreur;
		} catch (NullPointerException e) {
		}

		// get detailProduits in same avenant:
		List<DetailProduit> otherDetailProduits = new ArrayList<>();

		try {
			List<DetailProduitEntity> otherDetailProduitEntities = detailProduitRepository
					.getDetailProduitsByAvenantId(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailProduits = otherDetailProduitEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class))
					.collect(Collectors.toList());
			for (DetailProduitAttente detail : otherDetailsInAttente) {
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

				DetailProduit detailProduit = new DetailProduit();
				detailProduit.setTache(tache);
				detailProduit.setReference(detail.getReference());

				otherDetailProduits.add(detailProduit);

			}
		} catch (NullPointerException e) {
		}

		// DOUBLONS DE LIGNES
		for (DetailProduit detailProduit : otherDetailProduits) {
			try {
				if (detailProduit.getTache().getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
						&& detailProduit.getTache().getLot().getDesignation()
								.equalsIgnoreCase(detailProduitAttente.getLot())
						&& detailProduit.getTache().getProduit().getDesignation()
								.equalsIgnoreCase(detailProduitAttente.getProduit())
						&& detailProduit.getReference().equalsIgnoreCase(detailProduitAttente.getReference())) {
					return "(8)DOUBLONS DE LIGNES";
				}
			} catch (NullPointerException e) {
			}
		}

		// RCT
		return "(9)RCT";

	}

	@Override
	public List<DetailProduitAttente> getDetailProduitAttentesByAvenantId(Integer id) {
		List<DetailProduitAttenteEntity> detailProduitAttenteEntities = detailProduitAttenteRepository
				.getDetailProduitAttentesByAvenantId(id);
		return detailProduitAttenteEntities.stream().map(
				detailProduitAttenteEntity -> modelMapper.map(detailProduitAttenteEntity, DetailProduitAttente.class))
				.collect(Collectors.toList());
	}

}
