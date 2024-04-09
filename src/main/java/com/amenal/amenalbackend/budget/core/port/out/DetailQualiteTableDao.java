package com.amenal.amenalbackend.budget.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.application.dto.DetailQualiteTableDto;

public interface DetailQualiteTableDao {

	List<DetailQualiteTableDto> getDetailQualiteTableByProjectId(Integer id);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProject(Integer projectId, Integer lotId);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByProduitAndProject(Integer projectId,
			String produitDesignation);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByTacheAndProject(Integer projectId, Integer tacheId);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProduitAndProject(Integer projectId, Integer lotId,
			String produit);

	// By Avenant
	List<DetailQualiteTableDto> getDetailQualiteTableByAvenantId(Integer id);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndProduitAndAvenant(Integer lotId,
			Integer produitId);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByLotAndAvenant(Integer lotId, Integer avenantId);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByProduitAndAvenant(Integer produitId);

	List<DetailQualiteTableDto> getFilteredDetailQualiteTableByTacheAndAvenant(Integer tacheId);

}
