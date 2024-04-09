package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

public interface DetailProduitDao {
	DetailProduit findDetailProduitById(Integer id);
	
	List<DetailProduit> findAllDetailProduits();
	
	DetailProduit saveDetailProduit(DetailProduit detailProduit) throws DuplicateElementException;
	
	DetailProduit updateDetailProduit(DetailProduit detailProduit) throws DuplicateElementException;
	
	void deleteDetailProduit(Integer id);
	
}
