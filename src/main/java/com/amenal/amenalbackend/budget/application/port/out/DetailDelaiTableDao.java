package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.DetailDelaiTableDto;
import com.amenal.amenalbackend.budget.application.dto.RowDelaiDto;

public interface DetailDelaiTableDao {

	List<DetailDelaiTableDto> getDetailDelaiTableByProjectId(Integer id);

	List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produit);

	List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProject(Integer projectId, Integer lotId);

	List<RowDelaiDto> getFilteredDetailDelaiTableByProduitAndProject(Integer projectId, String produitDesignation);

	List<RowDelaiDto> getFilteredDetailDelaiTableByTacheAndProject(Integer projectId, Integer tacheId);

	// By Avenant
	List<DetailDelaiTableDto> getDetailDelaiTableByAvenantId(Integer id);

	List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndProduitAndAvenant(Integer lotId,
			Integer produitId);

	List<RowDelaiDto> getFilteredDetailDelaiTableByLotAndAvenant(Integer lotId, Integer avenantId);

	List<RowDelaiDto> getFilteredDetailDelaiTableByProduitAndAvenant(Integer produitId);

	List<RowDelaiDto> getFilteredDetailDelaiTableByTacheAndAvenant(Integer tacheId);	
}
