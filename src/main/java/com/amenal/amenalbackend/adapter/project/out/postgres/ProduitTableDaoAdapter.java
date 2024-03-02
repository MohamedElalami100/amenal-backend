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
import com.amenal.amenalbackend.application.project.dto.ActivitePrincipaleDto;
import com.amenal.amenalbackend.application.project.dto.ActiviteSecondaireDto;
import com.amenal.amenalbackend.application.project.dto.LotDeProduitDto;
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
			List<LotDeProduitDto> lotDeProduitDtos = new ArrayList<>();

			// Fetch all LotEntities for the current Produit
			List<LotEntity> lotEntities = lotRepository.getLotsByProduitId(produit.getId());
			List<Lot> lots = lotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
					.collect(Collectors.toList());

			List<Tache> allProduitTaches = new ArrayList<>();

			// Iterate over each Lot for the current Produit
			for (Lot lot : lots) {
				List<ActivitePrincipaleDto> activitePrincipaleDtos = new ArrayList<>();

				// Fetch all ActivitePrincipaleEntities for the current Produit and Lot
				List<TacheEntity> activitePrincipaleEntities = tacheRepository
						.getActivitePrincipalesByProduitIdAndLotId(produit.getId(), lot.getId());
				List<Tache> activitePrincipales = activitePrincipaleEntities.stream()
						.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

				List<Tache> allLotTaches = new ArrayList<>();

				// Iterate over each ActivitePrincipale for the current Produit and Lot
				for (Tache activitePrincipale : activitePrincipales) {
					List<ActiviteSecondaireDto> activiteSecondaireDtos = new ArrayList<>();

					// Fetch all ActiviteSecondaireEntities for the current Produit, Lot, and
					// ActivitePrincipale
					List<TacheEntity> activiteSecondaireEntities = tacheRepository
							.getActiviteSecondairesByActivitePrincipaleId(activitePrincipale.getId());
					List<Tache> activiteSecondaires = activiteSecondaireEntities.stream()
							.map(tacheEntity -> modelMapper.map(tacheEntity, Tache.class)).collect(Collectors.toList());

					// Iterate over each ActiviteSecondaire for the current Produit, Lot, and
					// ActivitePrincipale
					for (Tache activiteSecondaire : activiteSecondaires) {

						// Fetch all DetailChargeEntities for the current ActiviteSecoodaire
						List<DetailChargeEntity> detailsChargeEntities = detailChargeRepository
								.getDetailChargeByTacheId(activiteSecondaire.getId());
						List<DetailCharge> detailCharges = detailsChargeEntities.stream()
								.map(detailEntity -> modelMapper.map(detailEntity, DetailCharge.class))
								.collect(Collectors.toList());

						// Set list of DetailCharge to ActivitePrincipale
						activiteSecondaire.setDetailCharges(detailCharges);

						// Fetch all DetailProduitEntities for the current ActivitePrincipale
						List<DetailProduitEntity> detailsProduitEntities = detailProduitRepository
								.getDetailProduitByTacheId(activiteSecondaire.getId());
						List<DetailProduit> detailProduits = detailsProduitEntities.stream()
								.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class))
								.collect(Collectors.toList());

						// Set list of DetailProduit to ActivitePrincipale
						activiteSecondaire.setDetailProduits(detailProduits);

						allLotTaches.add(activiteSecondaire);
						allProduitTaches.add(activiteSecondaire);

						// Create ActiviteSecondaireDto and add it to the list
						ActiviteSecondaireDto activiteSecondaireDto = new ActiviteSecondaireDto(
								activiteSecondaire.getId(), activiteSecondaire.getOrdre(),
								activiteSecondaire.getTitreActivite(), activiteSecondaire.getUnite(),
								activiteSecondaire.getQtePBdg(), activiteSecondaire.getPuRef(),
								activiteSecondaire.getMntRefB(), activiteSecondaire.getQtePBdg(),
								activiteSecondaire.getPucBdg(), activiteSecondaire.getMncBdg(),
								activiteSecondaire.getMrgRefB(), activiteSecondaire.getMrpRefB(),
								activiteSecondaire.getDateDebut(), activiteSecondaire.getDelai(),
								activiteSecondaire.getDateFin());
						activiteSecondaireDtos.add(activiteSecondaireDto);
					}
					// Set list of ActiviteSecondaires to ActivitePrincipale
					activiteSecondaires.add(activitePrincipale);
					activitePrincipale.setTaches(activiteSecondaires);

					// Fetch all DetailChargeEntities for the current ActivitePrincipale
					List<DetailChargeEntity> detailsChargeEntities = detailChargeRepository
							.getDetailChargeByTacheId(activitePrincipale.getId());
					List<DetailCharge> detailCharges = detailsChargeEntities.stream()
							.map(detailEntity -> modelMapper.map(detailEntity, DetailCharge.class))
							.collect(Collectors.toList());

					// Set list of DetailCharge to ActivitePrincipale
					activitePrincipale.setDetailCharges(detailCharges);

					// Fetch all DetailProduitEntities for the current ActivitePrincipale
					List<DetailProduitEntity> detailsProduitEntities = detailProduitRepository
							.getDetailProduitByTacheId(activitePrincipale.getId());
					List<DetailProduit> detailProduits = detailsProduitEntities.stream()
							.map(detailEntity -> modelMapper.map(detailEntity, DetailProduit.class))
							.collect(Collectors.toList());

					// Set list of DetailProduit to ActivitePrincipale
					activitePrincipale.setDetailProduits(detailProduits);

					allLotTaches.add(activitePrincipale);
					allProduitTaches.add(activitePrincipale);

					// Create ActivitePrincipaleDto and add it to the list
					ActivitePrincipaleDto activitePrincipaleDto = new ActivitePrincipaleDto(activitePrincipale.getId(),
							activitePrincipale.getOrdre(), activitePrincipale.getTitreActivite(),
							activitePrincipale.getUnite(), activitePrincipale.getQtePBdg(),
							activitePrincipale.getPuRef(), activitePrincipale.getMntRefB(),
							activitePrincipale.getQtePBdg(), activitePrincipale.getPucBdg(),
							activitePrincipale.getMncBdg(), activitePrincipale.getMrgRefB(),
							activitePrincipale.getMrpRefB(), activitePrincipale.getDateDbtIni(),
							activitePrincipale.getDlaIni(), activitePrincipale.getDateFinIni(), activiteSecondaireDtos);
					activitePrincipaleDtos.add(activitePrincipaleDto);
				}

				// Set list of ActivitePrincipaleDto to Lot
				lot.setTaches(allLotTaches);

				// Create LotDeProduitDto and add it to the list
				LotDeProduitDto lotDeProduitDto = new LotDeProduitDto(lot.getId(), lot.getOrdre(), lot.getDesignation(),
						lot.getMntRefB(), lot.getMncBdg(), lot.getMrgRefB(), lot.getMrpRefB(), lot.getDateDbtIni(),
						lot.getDlaIni(), lot.getDateFinIni(), activitePrincipaleDtos);
				lotDeProduitDtos.add(lotDeProduitDto);
			}

			// Set list of Tache to Produit
			produit.setTaches(allProduitTaches);

			// Create ProduitTableDto and add it to the list
			ProduitTableDto produitTableDto = new ProduitTableDto(produit.getId(), produit.getArticle(),
					produit.getDesignation(), produit.getUnite(), produit.getQteRef(), produit.getPuRef(),
					produit.getMntRef(), produit.getPucBdg(), produit.getQteCum(), produit.getMncBdg(),
					produit.getMrgRef(), produit.getMrpRef(), produit.getDateDbtIni(), produit.getDlaIni(),
					produit.getDateFinIni(), lotDeProduitDtos);

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
