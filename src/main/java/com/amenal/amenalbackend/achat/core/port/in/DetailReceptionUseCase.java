package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailDevis;
import com.amenal.amenalbackend.achat.core.domain.DetailReception;
import com.amenal.amenalbackend.achat.core.domain.Reception;
import com.amenal.amenalbackend.achat.core.port.out.DetailDevisDao;
import com.amenal.amenalbackend.achat.core.port.out.DetailReceptionDao;
import com.amenal.amenalbackend.achat.core.port.out.ReceptionDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailDevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailReceptionEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ReceptionEntity;
import com.amenal.amenalbackend.achat.infrastructure.dto.AddDetailReceptionDto;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailDevisDto;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailReceptionDto;
import com.amenal.amenalbackend.achat.infrastructure.dto.ReceptionDto;
import com.amenal.amenalbackend.utils.infrastructure.exception.DetailReceptionLargerThanDetailCommande;

public class DetailReceptionUseCase {
	
	private DetailReceptionDao detailReceptionDao;

	private DetailDevisDao detailDevisDao;


	public DetailReceptionUseCase(DetailReceptionDao detailReceptionDao, DetailDevisDao detailDevisDao) {
		this.detailReceptionDao = detailReceptionDao;
		this.detailDevisDao = detailDevisDao;
	}

	public DetailReceptionDto findDetailReceptionById(Integer id) {
	    return detailReceptionDao.findDetailReceptionById(id);
	}

	public List<DetailReceptionDto> findAllDetailReceptions() {
		return detailReceptionDao.findAllDetailReceptions();
	}
	
	public DetailReception saveDetailReception(AddDetailReceptionDto addDetailReceptionDto) throws DetailReceptionLargerThanDetailCommande {

		DetailDevisDto detailCommande = detailDevisDao.
				findDetailDevisById(addDetailReceptionDto.getDetailCommandeId());
		if (addDetailReceptionDto.getQte() != null && addDetailReceptionDto.getDetailCommandeId() != null){
			Double qteReceptionne = addDetailReceptionDto.getQte();
			Double qteCommande = detailCommande.getQte();
			Double qteDejaReceptionne = detailReceptionDao
					.findByDetailCommandeId(addDetailReceptionDto.getDetailCommandeId())
					.stream()
					.mapToDouble(DetailReceptionDto::getQte)
					.sum();
			if (qteReceptionne > qteCommande - qteDejaReceptionne){
				throw new DetailReceptionLargerThanDetailCommande("La qte du articte receptionné est supérieur a son qantité commandé :"
						+ qteCommande + " - son qantité déja recéptionné: "+ qteDejaReceptionne
				);
			}
		}
		return detailReceptionDao.saveDetailReception(addDetailReceptionDto);
	}
	
	public DetailReception updateDetailReception(DetailReception detailReception) throws DetailReceptionLargerThanDetailCommande {
		if (detailReception.getQte() != null && detailReception.getDetailCommande() != null
				&& detailReception.getDetailCommande().getQte() != null){
			Double qteReceptionne = detailReception.getQte();
			Double qteCommande = detailReception.getDetailCommande().getQte();
			Double qteDejaReceptionne = detailReceptionDao
					.findByDetailCommandeId(detailReception.getDetailCommande().getId())
					.stream()
					.mapToDouble(DetailReceptionDto::getQte)
					.sum();
			if (qteReceptionne > qteCommande - qteDejaReceptionne){
				throw new DetailReceptionLargerThanDetailCommande("La qte du articte receptionné est supérieur a son qantité commandé :"
						+ qteCommande + " - son qantité déja recéptionné: "+ qteDejaReceptionne
				);
			}
		}
		return detailReceptionDao.updateDetailReception(detailReception);
	}
	
	public void deleteDetailReception(Integer id) {
		detailReceptionDao.deleteDetailReception(id);
	}	

}
