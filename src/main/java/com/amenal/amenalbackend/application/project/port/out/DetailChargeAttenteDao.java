package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.DetailChargeAttente;

public interface DetailChargeAttenteDao {
	DetailChargeAttente findDetailChargeAttenteById(Integer id);
	
	List<DetailChargeAttente> findAllDetailChargeAttentes();
	
	DetailChargeAttente saveDetailChargeAttente(DetailChargeAttente detailCharge);
	
	DetailChargeAttente updateDetailChargeAttente(DetailChargeAttente detailCharge);
	
	void deleteDetailChargeAttente(Integer id);

	List<DetailChargeAttente> getDetailChargeAttentesByAvenantId(Integer id);

	DetailChargeAttente saveDetailChargeAttenteWithErreur(DetailChargeAttente detailChargeAttente);
	
}
