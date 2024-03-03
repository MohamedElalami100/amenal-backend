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
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.LotEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.entities.TacheEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailChargeRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.LotRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.TacheRepository;
import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.domain.DetailProduit;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.domain.Produit;
import com.amenal.amenalbackend.application.project.domain.Tache;
import com.amenal.amenalbackend.application.project.dto.ActiviteDeLotDto;
import com.amenal.amenalbackend.application.project.dto.LotTableDto;
import com.amenal.amenalbackend.application.project.dto.ProduitTableDto;
import com.amenal.amenalbackend.application.project.port.out.ProduitTableDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProduitTableDaoAdapter implements ProduitTableDao {

	@Autowired
	private ProduitRepository produitRepository;

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
	public List<ProduitTableDto> getProduitTableByAvenantIdAndCharge(Integer id, String charge) {
		List<ProduitTableDto> produitTableDtos = new ArrayList<>();

		// Fetch all produitEntities and map them to Produit
		List<ProduitEntity> produitEntities;

		if (charge.equals("null"))
			produitEntities = produitRepository.getProduitsByAvenantId(id);
		else
			produitEntities = produitRepository.getProduitsByAvenantIdAndCharge(id, charge);
		List<Produit> produits = produitEntities.stream()
				.map(produitEntity -> modelMapper.map(produitEntity, Produit.class)).collect(Collectors.toList());

		// Iterate over each Produit
		for (Produit produit : produits) {
			List<LotTableDto> lotTableDtos = new ArrayList<>();

			// Fetch all LotEntities for the current Produit
			List<LotEntity> lotEntities = lotRepository.getLotsByProduitId(produit.getId());
			List<Lot> lots = lotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
					.collect(Collectors.toList());

			List<Tache> allProduitTaches = new ArrayList<>();

			// Iterate over each Lot for the current Produit
			for (Lot lot : lots) {
				List<ActiviteDeLotDto> activiteDeLotDtos = new ArrayList<>();

				// Fetch all ActivitePrincipaleEntities for the current Produit and Lot
				List<TacheEntity> tacheEntities = tacheRepository.getTachesByProduitIdAndLotId(produit.getId(),
						lot.getId());
				List<Tache> taches = tacheEntities.stream()
						.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

				List<Tache> allLotTaches = new ArrayList<>();

				// Iterate over each ActivitePrincipale for the current Produit and Lot
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

					allLotTaches.add(tache);
					allProduitTaches.add(tache);

					// Create ActivitePrincipaleDto and add it to the list
					ActiviteDeLotDto activiteDeLotDto = new ActiviteDeLotDto(tache.getId(), tache.getCleAttachement(),
							tache.getOrdre(), tache.getTitreActivite(), tache.getUnite(), tache.getQtePBdg(),
							tache.getPuRef(), tache.getMntRefB(), tache.getQtePBdg(), tache.getPucBdg(),
							tache.getMncBdg(), tache.getMrgRefB(), tache.getMrpRefB(), tache.getDateDebut(),
							tache.getDelai(), tache.getDateFin());
					activiteDeLotDtos.add(activiteDeLotDto);
				}

				// Set list of ActivitePrincipaleDto to Lot
				lot.setTaches(allLotTaches);

				// Create LotDeProduitDto and add it to the list
				LotTableDto lotTableDto = new LotTableDto(lot.getId(), lot.getOrdre(), lot.getDesignation(),
						lot.getMntRefB(), lot.getMncBdg(), lot.getMrgRefB(), lot.getMrpRefB(), lot.getDateDbtIni(),
						lot.getDlaIni(), lot.getDateFinIni(), activiteDeLotDtos);
				lotTableDtos.add(lotTableDto);
			}

			// Set list of Tache to Produit
			produit.setTaches(allProduitTaches);

			// Create ProduitTableDto and add it to the list
			ProduitTableDto produitTableDto = new ProduitTableDto(produit.getId(), produit.getArticle(),
					produit.getDesignation(), produit.getUnite(), produit.getQteRef(), produit.getPuRef(),
					produit.getMntRef(), produit.getPucBdg(), produit.getQteCum(), produit.getMncBdg(),
					produit.getMrgRef(), produit.getMrpRef(), produit.getDateDbtIni(), produit.getDlaIni(),
					produit.getDateFinIni(), lotTableDtos);

			produitTableDtos.add(produitTableDto);
		}
		return produitTableDtos;
	}

	@Override
	public List<ProduitTableDto> getProduitTableByProjectIdAndCharge(Integer id, String charge) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		List<Avenant> avenants = avenantEntities.stream()
				.map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class)).collect(Collectors.toList());

		List<ProduitTableDto> allProduitTableDto = new ArrayList<>();
		for (Avenant avenant : avenants) {
			List<ProduitTableDto> produitTableDtoList = getProduitTableByAvenantIdAndCharge(avenant.getId(), charge);
			allProduitTableDto.addAll(produitTableDtoList);
		}

		// apply sigma:
		return ProduitTableDto.removeDuplicatesAndSum(allProduitTableDto);
	}

}
