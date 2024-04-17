package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Avenant;
import com.amenal.amenalbackend.budget.core.domain.DetailCharge;
import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.budget.core.domain.Tache;
import com.amenal.amenalbackend.budget.core.port.out.TacheTableDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.budget.infrastructure.dto.TacheTableDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class TacheTableDaoAdapter implements TacheTableDao {

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private DetailChargeRepository detailChargeRepository;

	@Autowired
	private DetailProduitRepository detailProduitRepository;

	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TacheTableDto> getTacheTableByAvenantIdAndCharge(Integer id, String charge) {
		List<TacheTableDto> tacheTableDtos = new ArrayList<>();

		// Fetch all tacheEntities
		List<TacheEntity> tacheEntities;
		if (charge.equals("null"))
			tacheEntities = tacheRepository.getTachesByAvenantId(id);
		else
			tacheEntities = tacheRepository.getTachesByAvenantIdAndCharge(id, charge);
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

			// Set list of DetailCharge to ActivitePrincipale
			tache.setDetailCharges(detailCharges);

			// Fetch all DetailProduitEntities for the current tache
			List<DetailProduitEntity> detailsProduitEntities = detailProduitRepository
					.getDetailProduitByTacheId(tache.getId());
			List<DetailProduit> detailProduits = detailsProduitEntities.stream()
					.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class))
					.collect(Collectors.toList());

			// Set list of DetailProduit to ActivitePrincipale
			tache.setDetailProduits(detailProduits);
			TacheTableDto tacheTableDto = new TacheTableDto(tache.getId(), tache.getOrdre(),
					tache.getProduit().getDesignation(), tache.getLot().getDesignation(), tache.getTitreActivite(),
					tache.getCle(), tache.getUpb(), tache.getQtePBdg(), tache.getPuRef(),
					tache.getMntRefB(), tache.getQtePBdg(), tache.getPucBdg(), tache.getMncBdg(), tache.getMrgRefB(),
					tache.getMrpRefB(), tache.getDdb(), tache.getDlb(), tache.getDateFin(),
					tache.getProduit().getId(), tache.getLot().getId());
			tacheTableDtos.add(tacheTableDto);
		}
		return tacheTableDtos;
	}

	@Override
	public List<TacheTableDto> getTacheTableByProjectIdAndCharge(Integer id, String charge) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<TacheTableDto> allTacheTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<TacheTableDto> tacheTableDtoList = getTacheTableByAvenantIdAndCharge(avenant.getId(), charge);
			allTacheTableDto.addAll(tacheTableDtoList);
		}

		return allTacheTableDto;
	}

}
