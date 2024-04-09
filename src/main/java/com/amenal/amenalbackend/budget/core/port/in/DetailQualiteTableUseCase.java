package com.amenal.amenalbackend.budget.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.budget.core.port.out.DetailQualiteTableDao;
import com.amenal.amenalbackend.budget.infrastructure.dto.DetailQualiteTableDto;

public class DetailQualiteTableUseCase {
	
	private DetailQualiteTableDao detailQualiteTableDao;
	
	public DetailQualiteTableUseCase(DetailQualiteTableDao detailQualiteTableDao) {
		this.detailQualiteTableDao = detailQualiteTableDao;
	}

	public List<DetailQualiteTableDto> getDetailQualiteTableByProjectId(Integer id) {
		return detailQualiteTableDao.getDetailQualiteTableByProjectId(id);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produitDesignation) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByLotAndProduitAndProject(projectId,lotId, produitDesignation);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProject(Integer projectId, Integer lotId) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByLotAndProject(projectId,lotId);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByProduitAndProject(Integer projectId, String produitDesignation) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByProduitAndProject(projectId, produitDesignation);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByTacheAndProject(Integer projectId, Integer tacheId) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByTacheAndProject(projectId, tacheId);
	}
	
	public List<DetailQualiteTableDto> getDetailQualiteTableByAvenantId(Integer id) {
		return detailQualiteTableDao.getDetailQualiteTableByAvenantId(id);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByLotAndProduitAndAvenant(lotId, produitId);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndAvenant(Integer lotId,Integer avenantId) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByLotAndAvenant(lotId, avenantId);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByProduitAndAvenant(Integer produitId) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByProduitAndAvenant(produitId);
	}
	
	public List<DetailQualiteTableDto> getFilteredDetailQualiteTableByTacheAndAvenant(Integer tacheId) {
		return detailQualiteTableDao.getFilteredDetailQualiteTableByTacheAndAvenant(tacheId);
	}

}
