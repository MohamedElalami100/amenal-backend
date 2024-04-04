package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailDevis;

public interface DetailDevisDao {
	DetailDevis findDetailDevisById(Integer id);
	
	List<DetailDevis> findAllDetailDeviss();
	
	DetailDevis saveDetailDevis(DetailDevis detailDevis);
	
	DetailDevis updateDetailDevis(DetailDevis detailDevis);
	
	void deleteDetailDevis(Integer id);

}
