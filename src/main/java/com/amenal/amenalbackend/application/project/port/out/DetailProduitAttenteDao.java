package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailProduitAttente;

public interface DetailProduitAttenteDao {
	DetailProduitAttente findDetailProduitAttenteById(Integer id);
	
	List<DetailProduitAttente> findAllDetailProduitAttentes();
	
	DetailProduitAttente saveDetailProduitAttente(DetailProduitAttente detailProduitAttente);
	
	DetailProduitAttente updateDetailProduitAttente(DetailProduitAttente detailProduitAttente);
	
	void deleteDetailProduitAttente(Integer id);

	List<DetailProduitAttente> getDetailProduitAttentesByAvenantId(Integer id);

	DetailProduitAttente saveDetailProduitAttenteWithErreur(DetailProduitAttente detailProduitAttente);
	
}
