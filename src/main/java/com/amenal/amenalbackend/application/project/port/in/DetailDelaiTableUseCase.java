package com.amenal.amenalbackend.application.project.port.in;

import java.util.List;

import com.amenal.amenalbackend.application.project.dto.DetailDelaiTableDto;
import com.amenal.amenalbackend.application.project.dto.RowDelaiDto;
import com.amenal.amenalbackend.application.project.port.out.DetailDelaiTableDao;

public class DetailDelaiTableUseCase {
	
	private DetailDelaiTableDao detailDelaiTableDao;
	
	public DetailDelaiTableUseCase(DetailDelaiTableDao detailDelaiTableDao) {
		this.detailDelaiTableDao = detailDelaiTableDao;
	}

	public List<DetailDelaiTableDto> getDetailDelaiTableByProjectId(Integer id) {
		return detailDelaiTableDao.getDetailDelaiTableByProjectId(id);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produitDesignation) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByLotAndProduitAndProject(projectId, lotId, produitDesignation);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProject(Integer projectId, Integer lotId) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByLotAndProject(projectId, lotId);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByProduitAndProject(Integer projectId,String produitDesignation) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByProduitAndProject(projectId, produitDesignation);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByTacheAndProject(Integer projectId, Integer tacheId) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByTacheAndProject(projectId, tacheId);
	}
	
	public List<DetailDelaiTableDto> getDetailDelaiTableByAvenantId(Integer id) {
		return detailDelaiTableDao.getDetailDelaiTableByAvenantId(id);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByLotAndProduitAndAvenant(lotId, produitId);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndAvenant(Integer lotId,Integer avenantId) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByLotAndAvenant(lotId, avenantId);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByProduitAndAvenant(Integer produitId) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByProduitAndAvenant(produitId);
	}
	
	public List<RowDelaiDto> getFilteredDetailDelaiTableByTacheAndAvenant(Integer tacheId) {
		return detailDelaiTableDao.getFilteredDetailDelaiTableByTacheAndAvenant(tacheId);
	}
	
}
