package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailDevis;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailDevisDto;

public interface DetailDevisDao {
	DetailDevisDto findDetailDevisById(Integer id);
	
	List<DetailDevisDto> findAllDetailDeviss();
	
	DetailDevis saveDetailDevis(DetailDevis detailDevis);
	
	DetailDevis updateDetailDevis(DetailDevis detailDevis);
	
	void deleteDetailDevis(Integer id);

}
