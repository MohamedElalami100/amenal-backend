package com.amenal.amenalbackend.budget.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.DetailProduitTableDto;
import com.amenal.amenalbackend.budget.application.port.out.DetailProduitTableDao;

public class DetailProduitTableUseCase {
	
	private DetailProduitTableDao detailProduitTableDao;
	
	public DetailProduitTableUseCase(DetailProduitTableDao detailProduitTableDao) {
		this.detailProduitTableDao = detailProduitTableDao;
	}

	public List<DetailProduitTableDto> getDetailProduitTableByProjectId(Integer id) {
		return detailProduitTableDao.getDetailProduitTableByProjectId(id);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produitDesignation) {
		return detailProduitTableDao.getFilteredDetailProduitTableByLotAndProduitAndProject(projectId, lotId, produitDesignation);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProject(Integer projectId,Integer lotId) {
		return detailProduitTableDao.getFilteredDetailProduitTableByLotAndProject(projectId, lotId);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByProduitAndProject(Integer projectId, String produitDesignation) {
		return detailProduitTableDao.getFilteredDetailProduitTableByProduitAndProject(projectId, produitDesignation);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByTacheAndProject(Integer projectId, Integer tacheId) {
		return detailProduitTableDao.getFilteredDetailProduitTableByTacheAndProject(projectId, tacheId);
	}
	
	public List<DetailProduitTableDto> getDetailProduitTableByAvenantId(Integer id) {
		return detailProduitTableDao.getDetailProduitTableByAvenantId(id);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId) {
		return detailProduitTableDao.getFilteredDetailProduitTableByLotAndProduitAndAvenant(lotId, produitId);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndAvenant(Integer lotId,Integer avenantId) {
		return detailProduitTableDao.getFilteredDetailProduitTableByLotAndAvenant(lotId, avenantId);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByProduitAndAvenant(Integer produitId) {
		return detailProduitTableDao.getFilteredDetailProduitTableByProduitAndAvenant(produitId);
	}
	
	public List<DetailProduitTableDto> getFilteredDetailProduitTableByTacheAndAvenant(Integer tacheId) {
		return detailProduitTableDao.getFilteredDetailProduitTableByTacheAndAvenant(tacheId);
	}
}
