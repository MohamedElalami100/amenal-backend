package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailChargeAttenteEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailChargeAttenteRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.domain.DetailChargeAttente;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeAttenteDao;

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
		DetailChargeAttenteEntity existingEntity = detailChargeAttenteRepository.findById(detailChargeAttente.getId())
				.orElseThrow();
		detailChargeAttente.setErreur(getErreurMessage(detailChargeAttente));
		// Use ModelMapper to map non-null properties from DetailChargeAttente to
		// existingEntity
		modelMapper.map(detailChargeAttente, existingEntity);

		DetailChargeAttenteEntity updatedEntity = detailChargeAttenteRepository.save(existingEntity);
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
		String erreur = (detailChargeAttente.getProduit() == null || detailChargeAttente.getProduit() == ""
				? "| Produit Vide "
				: "")
				+ (detailChargeAttente.getLot() == null || detailChargeAttente.getLot() == "" ? "| Lot Vide " : "")
				+ (detailChargeAttente.getActivite() == null || detailChargeAttente.getActivite() == ""
						? "| Activite Vide "
						: "")
				+ (detailChargeAttente.getUpb() == null || detailChargeAttente.getUpb() == ""
				? "| Unite Vide "
				: "");
		if (erreur != "")
			return "(1)" + erreur;

		//TACHE EXISTE DANS UN AUTRE AVENANT 
		List<TacheEntity> tachesInOtherAvenantEntities = tacheRepository.getTachesInOtherAvenants(detailChargeAttente.getAvenant().getId());
		List<Tache> tachesInOtherAvenants = tachesInOtherAvenantEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());
		
		for(Tache tache: tachesInOtherAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite()) 
					&& tache.getLot().getDesignation().equalsIgnoreCase( detailChargeAttente.getLot())) {
				return "(2)TACHE EXISTE DANS UN AUTRE AVENANT";
			}	
		}
		
		//get taches in same avenant:
		List<TacheEntity> tachesInSameAvenantEntities = tacheRepository.getTachesByAvenantId(detailChargeAttente.getAvenant().getId());
		List<Tache> tachesInSameAvenants = tachesInSameAvenantEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());
		
		List<DetailChargeAttenteEntity> otherDetailEntities = detailChargeAttenteRepository.getOtherDetailsById(detailChargeAttente.getId(), detailChargeAttente.getAvenant().getId());
		List<DetailChargeAttente> otherDetailsInAttente = otherDetailEntities.stream().map(detailEntity -> modelMapper.map(detailEntity, DetailChargeAttente.class))
				.collect(Collectors.toList());
		
		for(DetailChargeAttente detail: otherDetailsInAttente) {
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
		//TACHE LIEE A DEUX PRODUITS DIFFERENTS	
		for(Tache tache: tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite()) 
					&& tache.getLot().getDesignation().equalsIgnoreCase( detailChargeAttente.getLot())
					&& !tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())) {
				return "(3)TACHE LIEE A DEUX PRODUITS DIFFERENTS";
			}	
		}
		
		//TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES 
		for(Tache tache: tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite()) 
					&& tache.getLot().getDesignation().equalsIgnoreCase( detailChargeAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())
					&& !tache.getUnite().equalsIgnoreCase(detailChargeAttente.getUpb())) {
				return "(4)TACHE DECLAREE AVEC DEUX UNITES DIFFERENTES";
			}	
		}
		
		//TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE
		for(Tache tache: tachesInSameAvenants) {
			if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite()) 
					&& tache.getLot().getDesignation().equalsIgnoreCase( detailChargeAttente.getLot())
					&& tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())
					&& tache.getCleAttachement() != detailChargeAttente.getCle()) {
				return "(5)TACHE DECLAREE EN TANT QUE CLE PRIMAIRE ET SECONDAIRE";
			}	
		}
		
		//TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE 
		Boolean activitePrincipaleExist = false;
		
		if(!detailChargeAttente.getCle()) {
			for(Tache tache: tachesInSameAvenants) {
				if (tache.getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite()) 
						&& tache.getLot().getDesignation().equalsIgnoreCase( detailChargeAttente.getLot())
						&& tache.getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())) {
					if (tache.getCleAttachement()) {
						activitePrincipaleExist = true;
					}			
				}	
			}
		}
		else {
			activitePrincipaleExist = true;
		}
		
		if (!activitePrincipaleExist)
			return "(6)TACHE LIEE A UN PRODUIT/LOT SANS CLE PRIMAIRE"; 
		
		// check if a one of the last fields is null or contain an empty string
		erreur = (detailChargeAttente.getCharge() == null || detailChargeAttente.getCharge() == ""
				? "| Charge Vide "
				: "")
				+ (detailChargeAttente.getUcb() == null || detailChargeAttente.getUcb() == "" ? "| Unite Charge Vide " : "")
				+ (detailChargeAttente.getQcb() == null || detailChargeAttente.getQcb() == 0
						? "| Qcb null "
						: "")
				+ (detailChargeAttente.getPcb() == null || detailChargeAttente.getPcb() == 0
				? "| Pcb null "
				: "");
		if (erreur != "")
			return "(7)" + erreur;
		
		//get detailCharges in same avenant:
		List<DetailChargeEntity> otherDetailChargeEntities = detailChargeRepository.getDetailChargesByAvenantId(detailChargeAttente.getAvenant().getId());
		List<DetailCharge> otherDetailCharges = otherDetailChargeEntities.stream().map(detailEntity -> modelMapper.map(detailEntity, DetailCharge.class))
		.collect(Collectors.toList());
		
		for(DetailChargeAttente detail: otherDetailsInAttente) {
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
		
		//DOUBLONS DE LIGNES
		for(DetailCharge detailCharge: otherDetailCharges) {
			if (detailCharge.getTache().getTitreActivite().equalsIgnoreCase(detailChargeAttente.getActivite()) 
					&& detailCharge.getTache().getLot().getDesignation().equalsIgnoreCase( detailChargeAttente.getLot())
					&& detailCharge.getTache().getProduit().getDesignation().equalsIgnoreCase(detailChargeAttente.getProduit())
					&& detailCharge.getDesignation().equalsIgnoreCase(detailChargeAttente.getCharge())) {
				return "(8)DOUBLONS DE LIGNES";
			}	
		}		
		
		//RCT
		return "(9)RCT";

	}
	
	@Override
	public List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id) {
		List<DetailChargeAttenteEntity> detailChargeAttenteEntities = detailChargeAttenteRepository.getDetailChargeAttentesByAvenantId(id);
		return detailChargeAttenteEntities.stream()
				.map(detailChargeAttenteEntity -> modelMapper.map(detailChargeAttenteEntity, DetailChargeAttente.class))
				.collect(Collectors.toList());	
	}
}
