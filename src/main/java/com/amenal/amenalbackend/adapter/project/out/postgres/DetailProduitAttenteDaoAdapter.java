package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailProduitAttenteEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailProduitAttenteRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.application.project.domain.DetailProduitAttente;
import com.amenal.amenalbackend.application.project.domain.DetailProduit;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.DetailProduitAttenteDao;

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
		String erreur = (detailProduitAttente.getProduit() == null || detailProduitAttente.getProduit() == ""
				? "| Produit Vide "
				: "")
				+ (detailProduitAttente.getLot() == null || detailProduitAttente.getLot() == "" ? "| Lot Vide " : "")
				+ (detailProduitAttente.getActivite() == null || detailProduitAttente.getActivite() == ""
						? "| Activite Vide "
						: "")
				+ (detailProduitAttente.getUpb() == null || detailProduitAttente.getUpb() == "" ? "| Unite Vide " : "");
		if (erreur != "")
			return "(1)" + erreur;

		// TACHE EXISTE DANS UN AUTRE AVENANT
		List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository
				.getTachesInOtherAvenants(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
		List<Tache> tachesInOtherAvenants = tachesInOtherAvenantEntities.stream()
				.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

		for (Tache tache : tachesInOtherAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())) {
				return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
			}
		}

		// get taches in same avenant:
		List<TacheEntity> tachesInSameAvenantEntities = tacheRepository
				.getTachesByAvenantId(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
		List<Tache> tachesInSameAvenants = tachesInSameAvenantEntities.stream()
				.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

		List<DetailProduitAttenteEntity> otherDetailEntities = detailProduitAttenteRepository.getOtherDetailsById(
				detailProduitAttente.getId(), detailProduitAttente.getMetre().getBudget().getAvenant().getId());
		List<DetailProduitAttente> otherDetailsInAttente = otherDetailEntities.stream()
				.map(detailEntity -> modelMapper.map(detailEntity, DetailProduitAttente.class))
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

			tachesInSameAvenants.add(tache);
		}
		// TACHE LIEE A DEUX PRODUITS DIFFERENTS
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
					&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())) {
				return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
			}
		}

		// TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())
					&& !tache.getUnite().equalsIgnoreCase(detailProduitAttente.getUpb())) {
				return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
			}
		}

		// TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for (Tache tache : tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
					&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())
					&& tache.getCleAttachement() != detailProduitAttente.getCle()) {
				return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
			}
		}

		// TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE
		Boolean activitePrincipaleExist = false;

		if (!detailProduitAttente.getCle()) {
			for (Tache tache : tachesInSameAvenants) {
				if (tache.getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
						&& tache.getLot().getDesignation().equalsIgnoreCase(detailProduitAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailProduitAttente.getProduit())) {
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
		erreur = (detailProduitAttente.getProduit() == null || detailProduitAttente.getProduit() == ""
				? "| Produit Vide "
				: "")
				+ (detailProduitAttente.getNbr() == null || detailProduitAttente.getNbr() == 0 ? "| Nbr null " : "");
		if (erreur != "")
			return "(7)" + erreur;

		// get detailProduits in same avenant:
		List<DetailProduitEntity> otherDetailProduitEntities = detailProduitRepository
				.getDetailProduitsByAvenantId(detailProduitAttente.getMetre().getBudget().getAvenant().getId());
		List<DetailProduit> otherDetailProduits = otherDetailProduitEntities.stream()
				.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class)).collect(Collectors.toList());

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

		// DOUBLONS DE LIGNES
		for (DetailProduit detailProduit : otherDetailProduits) {
			if (detailProduit.getTache().getTitreActivite().equalsIgnoreCase(detailProduitAttente.getActivite())
					&& detailProduit.getTache().getLot().getDesignation()
							.equalsIgnoreCase(detailProduitAttente.getLot())
					&& detailProduit.getTache().getProduit().getDesignation()
							.equalsIgnoreCase(detailProduitAttente.getProduit())
					&& detailProduit.getReference().equalsIgnoreCase(detailProduitAttente.getReference())) {
				return "(8)DOUBLONS DE LIGNES";
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
