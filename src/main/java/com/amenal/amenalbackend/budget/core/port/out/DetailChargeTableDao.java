package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.infrastructure.dto.DetailChargeTableDto;

public interface DetailChargeTableDao {

	List<DetailChargeTableDto> getDetailChargeTableByProjectId(Integer id);
		
	List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProject(Integer projectId, Integer lotId);
	
	List<DetailChargeTableDto> getFilteredDetailChargeTableByProduitAndProject(Integer projectId, String produitDesignation);

	List<DetailChargeTableDto> getFilteredDetailChargeTableByTacheAndProject(Integer projectId, Integer tacheId);
	
	List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProduitAndProject(Integer projectId, Integer lotId, String produit);
	
	// By Avenant
	List<DetailChargeTableDto> getDetailChargeTableByAvenantId(Integer id);

	List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndProduitAndAvenant(Integer lotId, Integer produitId);

	List<DetailChargeTableDto> getFilteredDetailChargeTableByLotAndAvenant(Integer lotId, Integer avenantId);

	List<DetailChargeTableDto> getFilteredDetailChargeTableByProduitAndAvenant(Integer produitId);

	List<DetailChargeTableDto> getFilteredDetailChargeTableByTacheAndAvenant(Integer tacheId);



}
