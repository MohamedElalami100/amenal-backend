package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Avenant;
import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.core.domain.Produit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteTableDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.GrpQualiteEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailQualiteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.GrpQualiteRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.budget.infrastructure.dto.DetailQualiteTableDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailQualiteTableDaoAdapter implements DetailQualiteTableDao {

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private DetailQualiteRepository detailQualiteRepository;

	@Autowired
	private GrpQualiteRepository grpQualiteRepository;
	
	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DetailQualiteTableDto> getDetailQualiteTableByProjectId(Integer id) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<DetailQualiteTableDto> allDetailQualiteTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<DetailQualiteTableDto> tacheTableDtoList = getDetailQualiteTableByAvenantId(avenant.getId());
			allDetailQualiteTableDto.addAll(tacheTableDtoList);
		}
		return allDetailQualiteTableDto;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProject(Integer projectId, Integer lotId) {
		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByProjectId(projectId);

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByProduitAndProject(Integer projectId,String produitDesignation) {
		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByProjectId(projectId);

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation)).collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByTacheAndProject(Integer projectId,Integer tacheId) {
		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByProjectId(projectId);

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProduitAndProject(Integer projectId, Integer lotId,
			String produitDesignation) {
		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByProjectId(projectId);

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation) && obj.getLotId() == lotId)
				.collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getDetailQualiteTableByAvenantId(Integer id) {
		List<DetailQualiteTableDto> detailQualiteTableDtos = new ArrayList<>();

		// Fetch all tacheEntities
		List<TacheEntity> tacheEntities = tacheRepository.getTachesByAvenantId(id);
		List<Tache> taches = tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());

		// Iterate over each tache
		for (Tache tache : taches) {
			List<GrpQualiteEntity> grpQualiteEntities = grpQualiteRepository.getGrpQualiteByTacheId(tache.getId());
			List<GrpQualite> grpQualites = grpQualiteEntities.stream()
					.map(grpEntity -> modelMapper.map(grpEntity, GrpQualite.class))
					.collect(Collectors.toList());
			
			for(GrpQualite groupe: grpQualites) {
				// Fetch all DetailQualiteEntities for the current tache
				List<DetailQualiteEntity> detailsChargeEntities = detailQualiteRepository
						.getDetailQualiteByGrpId(groupe.getId());
				List<DetailQualite> detailQualites = detailsChargeEntities.stream()
						.map(detailEntity -> modelMapper.map(detailEntity, DetailQualite.class))
						.collect(Collectors.toList());

				for (DetailQualite detailQualite : detailQualites) {
					DetailQualiteTableDto detailQualiteTableDto = new DetailQualiteTableDto(detailQualite.getId(), detailQualite.getOrdreMef(),
							tache.getProduit().getDesignation(), tache.getLot().getDesignation(),
							tache.getTitreActivite(), tache.getUnite(), tache.getCleAttachement(),
							groupe.getTitre(), detailQualite.getAffaire(), tache.getProduit().getId(),
							tache.getLot().getId(), tache.getId());
					detailQualiteTableDtos.add(detailQualiteTableDto);
				}
			}
		}
		return detailQualiteTableDtos;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProduitAndAvenant(Integer lotId,
			Integer produitId) {

		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId && obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByProduitAndAvenant(Integer produitId) {
		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId).collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByTacheAndAvenant(Integer tacheId) {
		TacheEntity tacheEntity = tacheRepository.findById(tacheId).get();
		Tache tache = modelMapper.map(tacheEntity, Tache.class);
		
		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByAvenantId(
				tache.getProduit().getMetre().getBudget().getAvenant().getId());
		
		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());
		return filteredDetailQualite;
	}

	@Override
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndAvenant(Integer lotId, Integer avenantId) {
		List<DetailQualiteTableDto> detailQualiteTableDtos = getDetailQualiteTableByAvenantId(avenantId);

		List<DetailQualiteTableDto> filteredDetailQualite = detailQualiteTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		return filteredDetailQualite;
	}


}



