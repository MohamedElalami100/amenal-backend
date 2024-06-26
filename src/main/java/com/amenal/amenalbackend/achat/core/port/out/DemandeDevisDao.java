package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DemandeDevis;

public interface DemandeDevisDao {
	DemandeDevis findDemandeDevisById(Integer id);
	
	List<DemandeDevis> findAllDemandeDeviss();
	
	DemandeDevis saveDemandeDevis(DemandeDevis demandeDevis);
	
	DemandeDevis updateDemandeDevis(DemandeDevis demandeDevis);
	
	void deleteDemandeDevis(Integer id);

}
