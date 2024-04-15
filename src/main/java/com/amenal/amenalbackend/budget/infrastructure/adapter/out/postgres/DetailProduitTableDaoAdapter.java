package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Avenant;
import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitTableDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.budget.infrastructure.dto.DetailProduitTableDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailProduitTableDaoAdapter implements DetailProduitTableDao {

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private DetailProduitRepository detailProduitRepository;

	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DetailProduitTableDto> getDetailProduitTableByProjectId(Integer id) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<DetailProduitTableDto> allDetailProduitTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<DetailProduitTableDto> tacheTableDtoList = getDetailProduitTableByAvenantId(avenant.getId());
			allDetailProduitTableDto.addAll(tacheTableDtoList);
		}
		return allDetailProduitTableDto;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProject(Integer projectId, Integer lotId) {
		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByProjectId(projectId);

		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByProduitAndProject(Integer projectId,String produitDesignation) {
		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByProjectId(projectId);
		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation)).collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByTacheAndProject(Integer projectId,Integer tacheId) {
		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByProjectId(projectId);

		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProduitAndProject(Integer projectId,Integer lotId,
			String produitDesignation) {
		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByProjectId(projectId);

		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation) && obj.getLotId() == lotId)
				.collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getDetailProduitTableByAvenantId(Integer id) {
		List<DetailProduitTableDto> detailProduitTableDtos = new ArrayList<>();

		// Fetch all tacheEntities
		List<TacheEntity> tacheEntities = tacheRepository.getTachesByAvenantId(id);
		List<Tache> taches = tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());

		// Iterate over each tache
		for (Tache tache : taches) {

			// Fetch all DetailProduitEntities for the current tache
			List<DetailProduitEntity> detailsChargeEntities = detailProduitRepository
					.getDetailProduitByTacheId(tache.getId());
			List<DetailProduit> detailProduits = detailsChargeEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class))
					.collect(Collectors.toList());

			for (DetailProduit detailProduit : detailProduits) {
				DetailProduitTableDto detailProduitTableDto = new DetailProduitTableDto(detailProduit.getId(), detailProduit.getOrdreMef(),
						tache.getProduit().getDesignation(), tache.getLot().getDesignation(), tache.getTitreActivite(),
						tache.getUnite(), tache.getCleAttachement(), detailProduit.getReference(),
						detailProduit.getNbr(), detailProduit.getDim1(), detailProduit.getDim2(),
						detailProduit.getDim3(), detailProduit.getQte(), tache.getProduit().getId(),
						tache.getLot().getId(), tache.getId());
				detailProduitTableDtos.add(detailProduitTableDto);
			}
		}
		return detailProduitTableDtos;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProduitAndAvenant(Integer lotId,
			Integer produitId) {

		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId && obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByProduitAndAvenant(Integer produitId) {
		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId).collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByTacheAndAvenant(Integer tacheId) {

		TacheEntity tacheEntity = tacheRepository.findById(tacheId).get();
		Tache tache = modelMapper.map(tacheEntity, Tache.class);
		
		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByAvenantId(
				tache.getProduit().getMetre().getBudget().getAvenant().getId());		
		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());
		return filteredDetailProduit;
	}

	@Override
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndAvenant(Integer lotId, Integer avenantId) {
		List<DetailProduitTableDto> detailProduitTableDtos = getDetailProduitTableByAvenantId(avenantId);

		List<DetailProduitTableDto> filteredDetailProduit = detailProduitTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailProduit;
	}


}
