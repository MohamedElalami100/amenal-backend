package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailProduit;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

public interface DetailProduitDao {
	DetailProduit findDetailProduitById(Integer id);
	
	List<DetailProduit> findAllDetailProduits();
	
	DetailProduit saveDetailProduit(DetailProduit detailProduit) throws DuplicateElementException;
	
	DetailProduit updateDetailProduit(DetailProduit detailProduit) throws DuplicateElementException;
	
	void deleteDetailProduit(Integer id);
	
}
