 package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailDemandeDevis;

public interface DetailDemandeDevisDao {
	DetailDemandeDevis findDetailDemandeDevisById(Integer id);
	
	List<DetailDemandeDevis> findAllDetailDemandeDeviss();
	
	DetailDemandeDevis saveDetailDemandeDevis(DetailDemandeDevis detailDemandeDevis);
	
	DetailDemandeDevis updateDetailDemandeDevis(DetailDemandeDevis detailDemandeDevis);
	
	void deleteDetailDemandeDevis(Integer id);

}
