package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.budget.application.domain.Avenant;
import com.amenal.amenalbackend.budget.application.domain.Produit;
import com.amenal.amenalbackend.budget.application.domain.Tache;
import com.amenal.amenalbackend.budget.application.dto.DetailDelaiTableDto;
import com.amenal.amenalbackend.budget.application.dto.RowDelaiDto;
import com.amenal.amenalbackend.budget.application.port.out.DetailDelaiTableDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailDelaiTableDaoAdapter implements DetailDelaiTableDao {

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ProduitRepository produitRepository;


	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DetailDelaiTableDto> getDetailDelaiTableByProjectId(Integer id) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<DetailDelaiTableDto> alldetailDelaitTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<DetailDelaiTableDto> tacheTableDtoList = getDetailDelaiTableByAvenantId(avenant.getId());
			alldetailDelaitTableDto.addAll(tacheTableDtoList);
		}
		return alldetailDelaitTableDto;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produitDesignation) {
		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByProjectId(projectId);

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation) && obj.getLotId() == lotId).collect(Collectors.toList());

		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProject(Integer projectId, Integer lotId) {
		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByProjectId(projectId);

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());

		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByProduitAndProject(Integer projectId, String produitDesignation) {
		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByProjectId(projectId);

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getProduit().equalsIgnoreCase(produitDesignation)).collect(Collectors.toList());

		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByTacheAndProject(Integer projectId, Integer tacheId) {
		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByProjectId(projectId);

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());

		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<DetailDelaiTableDto> getDetailDelaiTableByAvenantId(Integer id) {
		List<DetailDelaiTableDto> detailDelaiTableDtos = new ArrayList<>();

		// Fetch all tacheEntities
		List<TacheEntity> tacheEntities = tacheRepository.getTachesByAvenantId(id);
		List<Tache> taches = tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
				.collect(Collectors.toList());

		// Iterate over each tache
		for (Tache tache : taches) {

			DetailDelaiTableDto detailDelaiTableDto = new DetailDelaiTableDto(tache.getId(), tache.getOrdreMef(),
					tache.getProduit().getDesignation(), tache.getLot().getDesignation(), tache.getTitreActivite(),
					tache.getUnite(), tache.getCleAttachement(), tache.getDateDebut(), tache.getDelai(),
					tache.getDateFin(), tache.getProduit().getId(), tache.getLot().getId(), tache.getId());
			detailDelaiTableDtos.add(detailDelaiTableDto);

		}
		return detailDelaiTableDtos;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId) {
		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId && obj.getLotId() == lotId).collect(Collectors.toList());
		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndAvenant(Integer lotId, Integer avenantId) {
		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByAvenantId(avenantId);

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getLotId() == lotId).collect(Collectors.toList());
		
		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByProduitAndAvenant(Integer produitId) {
		ProduitEntity produitEntity = produitRepository.findById(produitId).get();
		Produit produit = modelMapper.map(produitEntity, Produit.class);

		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByAvenantId(
				produit.getMetre().getBudget().getAvenant().getId());

		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getProduitId() == produitId).collect(Collectors.toList());
		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

	@Override
	public List<RowDelaiDto> getFilteredDetailDelaiTableByTacheAndAvenant(Integer tacheId) {
		TacheEntity tacheEntity = tacheRepository.findById(tacheId).get();
		Tache tache = modelMapper.map(tacheEntity, Tache.class);
		
		List<DetailDelaiTableDto> detailDelaiTableDtos = getDetailDelaiTableByAvenantId(
				tache.getProduit().getMetre().getBudget().getAvenant().getId());
		
		List<DetailDelaiTableDto> filteredDetailDelai = detailDelaiTableDtos.stream()
				.filter(obj -> obj.getActiviteId() == tacheId).collect(Collectors.toList());

		List<RowDelaiDto> rowDetails = DetailDelaiTableDto.transformToRowDelais(filteredDetailDelai);
		return rowDetails;
	}

}
