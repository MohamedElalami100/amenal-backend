package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailDelaiAttente;

public interface DetailDelaiAttenteDao {
	DetailDelaiAttente findDetailDelaiAttenteById(Integer id);
	
	List<DetailDelaiAttente> findAllDetailDelaiAttentes();
	
	DetailDelaiAttente saveDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente);
	
	DetailDelaiAttente updateDetailDelaiAttente(DetailDelaiAttente detailDelaiAttente);
	
	void deleteDetailDelaiAttente(Integer id);

	List<DetailDelaiAttente> getDetailDelaiAttentesByAvenantId(Integer id);

	DetailDelaiAttente saveDetailDelaiAttenteWithErreur(DetailDelaiAttente detailDelaiAttente);
	
}
