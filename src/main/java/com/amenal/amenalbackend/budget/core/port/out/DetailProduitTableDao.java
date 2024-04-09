package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.infrastructure.dto.DetailProduitTableDto;

public interface DetailProduitTableDao {

	List<DetailProduitTableDto> getDetailProduitTableByProjectId(Integer id);
		
	List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProject(Integer projectId, Integer lotId);
	
	List<DetailProduitTableDto> getFilteredDetailProduitTableByProduitAndProject(Integer projectId, String produitDesignation);

	List<DetailProduitTableDto> getFilteredDetailProduitTableByTacheAndProject(Integer projectId, Integer tacheId);
	
	List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produit);
	
	// By Avenant
	List<DetailProduitTableDto> getDetailProduitTableByAvenantId(Integer id);

	List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId);

	List<DetailProduitTableDto> getFilteredDetailProduitTableByLotAndAvenant(Integer lotId, Integer avenantId);

	List<DetailProduitTableDto> getFilteredDetailProduitTableByProduitAndAvenant(Integer produitId);

	List<DetailProduitTableDto> getFilteredDetailProduitTableByTacheAndAvenant(Integer tacheId);



}
