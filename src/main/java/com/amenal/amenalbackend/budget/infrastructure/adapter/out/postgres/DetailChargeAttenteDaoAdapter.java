package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.DetailCharge;
import com.amenal.amenalbackend.budget.core.domain.DetailChargeAttente;
import com.amenal.amenalbackend.budget.core.domain.Lot;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailChargeAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailChargeAttenteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailChargeAttenteDaoAdapter implements DetailChargeAttenteDao {

	@Autowired
	private DetailChargeAttenteRepository detailChargeAttenteRepository;

	@Autowired
	private DetailChargeRepository detailChargeRepository;

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailChargeAttente findDetailChargeAttenteById(Integer id) {
		DetailChargeAttenteEntity detailChargeAttenteEntity = detailChargeAttenteRepository.findById(id).get();
		DetailChargeAttente detailChargeAttente = modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class);
		return detailChargeAttente;
	}

	@Override
	public List<DetailChargeAttente> findAllDetailChargeAttentes() {
		List<DetailChargeAttenteEntity> detailChargeAttenteEntities = detailChargeAttenteRepository.findAll();
		return detailChargeAttenteEntities.stream()
				.map(detailChargeAttenteEntity -> modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailChargeAttente saveDetailChargeAttente(DetailChargeAttente detailChargeAttente) {
		DetailChargeAttenteEntity detailChargeAttenteEntity = modelMapper.map(detailChargeAttente,
				DetailChargeAttenteEntity.class);
		DetailChargeAttenteEntity savedEntity = detailChargeAttenteRepository.save(detailChargeAttenteEntity);
		return modelMapper.map(savedEntity, DetailChargeAttente.class);
	}

	@Override
	public DetailChargeAttente saveDetailChargeAttenteWithErreur(DetailChargeAttente detailChargeAttente) {
		detailChargeAttente.setErreur(getErreurMessage(detailChargeAttente));

		DetailChargeAttenteEntity detailChargeAttenteEntity = modelMapper.map(detailChargeAttente,
				DetailChargeAttenteEntity.class);
		DetailChargeAttenteEntity savedEntity = detailChargeAttenteRepository.save(detailChargeAttenteEntity);
		return modelMapper.map(savedEntity, DetailChargeAttente.class);
	}

	@Override
	public DetailChargeAttente updateDetailChargeAttente(DetailChargeAttente detailChargeAttente) {
		detailChargeAttenteRepository.findById(detailChargeAttente.getId())
				.orElseThrow();
		detailChargeAttente.setErreur(getErreurMessage(detailChargeAttente));
		// Use ModelMapper to map non-null properties from DetailChargeAttente to
		// existingEntity
		DetailChargeAttenteEntity newEntity = modelMapper.map(detailChargeAttente, DetailChargeAttenteEntity.class);

		DetailChargeAttenteEntity updatedEntity = detailChargeAttenteRepository.save(newEntity);
		return modelMapper.map(updatedEntity, DetailChargeAttente.class);
	}

	@Override
	public void deleteDetailChargeAttente(Integer id) {
		// Check if DetailChargeAttente with the given ID exists
		DetailChargeAttenteEntity detailChargeAttenteEntity = detailChargeAttenteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailChargeAttenteRepository.delete(detailChargeAttenteEntity);
	}

	private String getErreurMessage(DetailChargeAttente detailChargeAttente) {
		// set error message:

		// check if a one of the first fields is null or contain an empty string
		String erreur = "";
		try {
			erreur = (detailChargeAttente.getProduit() == null || detailChargeAttente.getProduit().isEmpty()
					? "| Produit Vide "
					: "")
					+ (detailChargeAttente.getLot() == null || detailChargeAttente.getLot().isEmpty() ? "| Lot Vide "
							: "")
					+ (detailChargeAttente.getActivite() == null || detailChargeAttente.getActivite() == ""
							? "| Activite Vide "
							: "")
					+ (detailChargeAttente.getUpb() == null || detailChargeAttente.getUpb().isEmpty() ? "| Unite Vide "
							: "");
			if (!erreur.isEmpty())
				return "(1)" + erreur;
		} catch (NullPointerException e) {
		}
		try {
			// TACHE EXISTE DANS UN AUTRE AVENANT
			List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
					.getTachesInOtherAvenants(detailChargeAttente.getMetre().getBudget().getAvenant().getId());
			List<Tache> tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());
			for (Tache tache : tachesInOtherAvenants) {
				if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailChargeAttente.getLot())) {
					return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
				}
			}
		} catch (NullPointerException e) {
		}

		// get taches in same avenant:
		List<Tache> tachesInSameAvenants = new ArrayList<>();
		List<DetailChargeAttente> otherDetailsInAttente = new ArrayList<>();

		try {
			List<TacheEntity> tachesInSameAvenantEntities = tacheRepository
					.getTachesByAvenantId(detailChargeAttente.getMetre().getBudget().getAvenant().getId());
			tachesInSameAvenants = tachesInSameAvenantEntities.stream()
					.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

			List<DetailChargeAttenteEntity> otherDetailEntities = detailChargeAttenteRepository.getOtherDetailsById(
					detailChargeAttente.getId(), detailChargeAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailsInAttente = otherDetailEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailChargeAttente.class))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (DetailChargeAttente detail : otherDetailsInAttente) {
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
				if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailChargeAttente.getLot())
						&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())) {
					return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
				}
			} catch (NullPointerException e) {
			}
		}

		// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailChargeAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())
						&& !tache.getUnite().equalsIgnoreCase(detailChargeAttente.getUpb())) {
					return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
				}
			} catch (NullPointerException e) {
			}
		}
		// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for (Tache tache : tachesInSameAvenants) {
			try {
				if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailChargeAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())
						&& tache.getCleAttachement() != detailChargeAttente.getCle()) {
					return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}
		try {
			// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
			Boolean activitePrincipaleExist = false;
			if (!detailChargeAttente.getCle()) {
				for (Tache tache : tachesInSameAvenants) {
					try {
						if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite())
								&& tache.getLot().getDesignation().equalsIgnoreCase(detailChargeAttente.getLot())
								&& tache.getProduit().getDesignation()
										.equalsIgnoreCase(detailChargeAttente.getProduit())) {
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
			erreur = (detailChargeAttente.getCharge() == null || detailChargeAttente.getCharge().isEmpty()
					? "| Charge Vide "
					: "")
					+ (detailChargeAttente.getUcb() == null || detailChargeAttente.getUcb().isEmpty()
							? "| Unite Charge Vide "
							: "")
					+ (detailChargeAttente.getQcb() == null || detailChargeAttente.getQcb() == 0 ? "| Qcb null " : "")
					+ (detailChargeAttente.getPcb() == null || detailChargeAttente.getPcb() == 0 ? "| Pcb null " : "");
			if (!erreur.isEmpty())
				return "(7)" + erreur;
		} catch (NullPointerException e) {
		}

		// get detailCharges in same avenant:
		List<DetailChargeEntity> otherDetailChargeEntities = new ArrayList<>();
		List<DetailCharge> otherDetailCharges = new ArrayList<>();
		try {
			otherDetailChargeEntities = detailChargeRepository
					.getDetailChargesByAvenantId(detailChargeAttente.getMetre().getBudget().getAvenant().getId());
			otherDetailCharges = otherDetailChargeEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailCharge.class))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
		}

		for (DetailChargeAttente detail : otherDetailsInAttente) {
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

			DetailCharge detailCharge = new DetailCharge();
			detailCharge.setTache(tache);
			detailCharge.setDesignation(detail.getCharge());
			detailCharge.setUnite(detail.getUcb());

			otherDetailCharges.add(detailCharge);

		}

		// DOUBLONS DE LIGNES
		for (DetailCharge detailCharge : otherDetailCharges) {
			try {
				if (detailCharge.getTache().getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite())
						&& detailCharge.getTache().getLot().getDesignation()
								.equalsIgnoreCase(detailChargeAttente.getLot())
						&& detailCharge.getTache().getProduit().getDesignation()
								.equalsIgnoreCase(detailChargeAttente.getProduit())
						&& detailCharge.getDesignation().equalsIgnoreCase(detailChargeAttente.getCharge())) {
					return "(8)DOUBLONS DE LIGNES";
				}
			} catch (NullPointerException e) {
			}
		}
		// RCT
		return "(9)RCT";

	}

	@Override
	public List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id) {
		List<DetailChargeAttenteEntity> detailChargeAttenteEntities = detailChargeAttenteRepository
				.getDetailChargeAttentesByAvenantId(id);
		return detailChargeAttenteEntities.stream()
				.map(detailChargeAttenteEntity -> modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class))
				.collect(Collectors.toList());
	}
}
