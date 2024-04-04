package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.DetailChargeEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.LotEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.LotRepository;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.budget.application.domain.Avenant;
import com.amenal.amenalbackend.budget.application.domain.DetailCharge;
import com.amenal.amenalbackend.budget.application.domain.DetailProduit;
import com.amenal.amenalbackend.budget.application.domain.Lot;
import com.amenal.amenalbackend.budget.application.domain.Tache;
import com.amenal.amenalbackend.budget.application.dto.ActiviteDeLotDto;
import com.amenal.amenalbackend.budget.application.dto.LotTableDto;
import com.amenal.amenalbackend.budget.application.port.out.LotTableDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class LotTableDaoAdapter implements LotTableDao {

	@Autowired
	private LotRepository lotRepository;

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
	public List<LotTableDto> getLotTableByAvenantIdAndCharge(Integer id, String charge) {
		List<LotTableDto> lotTableDtos = new ArrayList<>();

		// Fetch all lotEntities and map them to Lot
		List<LotEntity> lotEntities;
		if(charge.equals("null"))
			lotEntities = lotRepository.getLotsByAvenantId(id);
		else 
			lotEntities = lotRepository.getLotsByAvenantIdAndCharge(id, charge);
		List<Lot> lots = lotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
				.collect(Collectors.toList());

		// Iterate over each lot
		for (Lot lot : lots) {
			List<ActiviteDeLotDto> activiteDeLotDtos = new ArrayList<>();

			// Fetch all LotEntities for the current Produit
			List<TacheEntity> tacheEntities = tacheRepository.getActiviteByLotIdAndAvenantId(lot.getId(), id);
			List<Tache> taches = tacheEntities.stream().map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class))
					.collect(Collectors.toList());

			// Iterate over each Activite for the current Lot
			for (Tache tache : taches) {

				// Fetch all DetailChargeEntities for the current ActivitePrincipale
				List<DetailChargeEntity> detailsChargeEntities = detailChargeRepository
						.getDetailChargeByTacheId(tache.getId());
				List<DetailCharge> detailCharges = detailsChargeEntities.stream()
						.map(detailEntity -> modelMapper.map(detailEntity, DetailCharge.class))
						.collect(Collectors.toList());

				// Set list of DetailCharge to ActivitePrincipale
				tache.setDetailCharges(detailCharges);

				// Fetch all DetailProduitEntities for the current ActivitePrincipale
				List<DetailProduitEntity> detailsProduitEntities = detailProduitRepository
						.getDetailProduitByTacheId(tache.getId());
				List<DetailProduit> detailProduits = detailsProduitEntities.stream()
						.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class))
						.collect(Collectors.toList());

				// Set list of DetailProduit to ActivitePrincipale
				tache.setDetailProduits(detailProduits);
				ActiviteDeLotDto activiteDeLotDto = new ActiviteDeLotDto(tache.getId(), tache.getCleAttachement(),
						tache.getOrdre(), tache.getTitreActivite(), tache.getUnite(), tache.getQtePBdg(),
						tache.getPuRef(), tache.getMntRefB(), tache.getQtePBdg(), tache.getPucBdg(), tache.getMncBdg(),
						tache.getMrgRefB(), tache.getMrpRefB(), tache.getDateDebut(), tache.getDelai(),
						tache.getDateFin());
				activiteDeLotDtos.add(activiteDeLotDto);
			}

			lot.setTaches(taches);

			// Create LotTableDto and add it to the list
			LotTableDto lotTableDto = new LotTableDto(lot.getId(), lot.getOrdre(), lot.getDesignation(),
					lot.getMntRefB(), lot.getMncBdg(), lot.getMrgRefB(), lot.getMrpRefB(), lot.getDateDbtIni(),
					lot.getDlaIni(), lot.getDateFinIni(), activiteDeLotDtos);

			lotTableDtos.add(lotTableDto);

		}

		return lotTableDtos;
	}

	@Override
	public List<LotTableDto> getLotTableByProjectIdAndCharge(Integer id, String charge) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<LotTableDto> allLotTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<LotTableDto> lotTableDtoList = getLotTableByAvenantIdAndCharge(avenant.getId(), charge);
			allLotTableDto.addAll(lotTableDtoList);
		}

		// apply sigma:
		return LotTableDto.removeDuplicatesAndSum(allLotTableDto);
	}

}
