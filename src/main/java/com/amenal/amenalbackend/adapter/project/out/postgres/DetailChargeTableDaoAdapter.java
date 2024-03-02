package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.dto.DetailChargeTableDto;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeTableDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailChargeTableDaoAdapter implements DetailChargeTableDao {

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private DetailChargeRepository detailChargeRepository;

	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DetailChargeTableDto> getDetailChargeTableByProjectId(Integer id) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<DetailChargeTableDto> allDetailChargeTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<DetailChargeTableDto> tacheTableDtoList = getDetailChargeTableByAvenantId(avenant.getId());
			allDetailChargeTableDto.addAll(tacheTableDtoList);
		}
		return allDetailChargeTableDto;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProject(Integer projectId, Integer lotId) {
		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByProjectId(projectId);

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByProduitAndProject(Integer projectId, String produitDesignation) {
		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByProjectId(projectId);

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation)).collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByTacheAndProject(Integer projectId, Integer tacheId) {
		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByProjectId(projectId);

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProduitAndProject(Integer projectId, Integer lotId,
			String produitDesignation) {
		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByProjectId(projectId);

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getProduit()
						.equalsIgnoreCase(produitDesignation) && obj.getLotId() == lotId)
				.collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getDetailChargeTableByAvenantId(Integer id) {
		List<DetailChargeTableDto> detailChargeTableDtos = new ArrayList<>();

		// Fetch all tacheEntities
		List<TacheEntity> tacheEntities = tacheRepository.getTachesByAvenantId(id);
		List<Tache> taches = tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());

		// Iterate over each tache
		for (Tache tache : taches) {

			// Fetch all DetailChargeEntities for the current tache
			List<DetailChargeEntity> detailsChargeEntities = detailChargeRepository
					.getDetailChargeByTacheId(tache.getId());
			List<DetailCharge> detailCharges = detailsChargeEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailCharge.class))
					.collect(Collectors.toList());

			for (DetailCharge detailCharge : detailCharges) {
				DetailChargeTableDto detailChargeTableDto = new DetailChargeTableDto(detailCharge.getOrdreMef(),
						tache.getProduit().getDesignation(), tache.getLot().getDesignation(), tache.getTitreActivite(),
						tache.getUnite(), tache.getCleAttachement(), detailCharge.getDesignation(),
						detailCharge.getNature().getNature(), detailCharge.getUnite(), detailCharge.getQte(),
						detailCharge.getPrix(), detailCharge.getMontant(), detailCharge.getLaUnitePrd(),
						detailCharge.getLaQtePrd(), detailCharge.getDateSaisie(), tache.getProduit().getId(),
						tache.getLot().getId(), tache.getId());
				detailChargeTableDtos.add(detailChargeTableDto);
			}
		}
		return detailChargeTableDtos;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProduitAndAvenant(Integer lotId,
			Integer produitId) {

		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId && obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByProduitAndAvenant(Integer produitId) {
		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId).collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByTacheAndAvenant(Integer tacheId) {

		TacheEntity tacheEntity = tacheRepository.findById(tacheId).get();
		Tache tache = modelMapper.map(tacheEntity, Tache.class);
		
		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByAvenantId(
				tache.getProduit().getMetre().getBudget().getAvenant().getId());
		
		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());
		return filteredDetailCharge;
	}

	@Override
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndAvenant(Integer lotId, Integer avenantId) {
		List<DetailChargeTableDto> detailChargeTableDtos = getDetailChargeTableByAvenantId(avenantId);

		List<DetailChargeTableDto> filteredDetailCharge = detailChargeTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailCharge;
	}


}
