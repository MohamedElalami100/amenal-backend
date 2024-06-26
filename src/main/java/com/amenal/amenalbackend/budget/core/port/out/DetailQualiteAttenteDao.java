package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.DetailQualiteAttente;

public interface DetailQualiteAttenteDao {
	DetailQualiteAttente findDetailQualiteAttenteById(Integer id);
	
	List<DetailQualiteAttente> findAllDetailQualiteAttentes();
	
	DetailQualiteAttente saveDetailQualiteAttente(DetailQualiteAttente detailQualite);
	
	DetailQualiteAttente updateDetailQualiteAttente(DetailQualiteAttente detailQualite);
	
	void deleteDetailQualiteAttente(Integer id);

	List<DetailQualiteAttente> getDetailQualiteAttentesByAvenantId(Integer id);

	DetailQualiteAttente saveDetailQualiteAttenteWithErreur(DetailQualiteAttente detailQualiteAttente);
	
}
