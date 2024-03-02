package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.dto.DetailChargeTableDto;
import com.amenal.amenalbackend.application.project.port.out.DetailChargeTableDao;

public class DetailChargeTableUseCase {
	
	private DetailChargeTableDao detailChargeTableDao;
	
	public DetailChargeTableUseCase(DetailChargeTableDao detailChargeTableDao) {
		this.detailChargeTableDao = detailChargeTableDao;
	}

	public List<DetailChargeTableDto> getDetailChargeTableByProjectId(Integer id) {
		return detailChargeTableDao.getDetailChargeTableByProjectId(id);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produitDesignation) {
		return detailChargeTableDao.getFilteredDetailChargeTableByLotAndProduitAndProject(projectId, lotId, produitDesignation);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProject(Integer projectId, Integer lotId) {
		return detailChargeTableDao.getFilteredDetailChargeTableByLotAndProject(projectId, lotId);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByProduitAndProject(Integer projectId, String produitDesignation) {
		return detailChargeTableDao.getFilteredDetailChargeTableByProduitAndProject(projectId, produitDesignation);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByTacheAndProject(Integer projectId, Integer tacheId) {
		return detailChargeTableDao.getFilteredDetailChargeTableByTacheAndProject(projectId, tacheId);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId) {
		return detailChargeTableDao.getFilteredDetailChargeTableByLotAndProduitAndAvenant(lotId, produitId);
	}
	
	public List<DetailChargeTableDto> getDetailChargeTableByAvenantId(Integer id) {
		return detailChargeTableDao.getDetailChargeTableByAvenantId(id);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndAvenant(Integer lotId,Integer avenantId) {
		return detailChargeTableDao.getFilteredDetailChargeTableByLotAndAvenant(lotId, avenantId);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByProduitAndAvenant(Integer produitId) {
		return detailChargeTableDao.getFilteredDetailChargeTableByProduitAndAvenant(produitId);
	}
	
	public List<DetailChargeTableDto> getFilteredDetailChargeTableByTacheAndAvenant(Integer tacheId) {
		return detailChargeTableDao.getFilteredDetailChargeTableByTacheAndAvenant(tacheId);
	}
}
