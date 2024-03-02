package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailProduit;

public interface DetailProduitDao {
	DetailProduit findDetailProduitById(Integer id);
	
	List<DetailProduit> findAllDetailProduits();
	
	DetailProduit saveDetailProduit(DetailProduit detailProduit);
	
	DetailProduit updateDetailProduit(DetailProduit detailProduit);
	
	void deleteDetailProduit(Integer id);
	
}
