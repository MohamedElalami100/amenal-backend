package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailFacture;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailFactureDto;

public interface DetailFactureDao {
	DetailFactureDto findDetailFactureById(Integer id);
	
	List<DetailFactureDto> findAllDetailFactures();
	
	DetailFacture saveDetailFacture(DetailFacture detailFacture);
	
	DetailFacture updateDetailFacture(DetailFacture detailFacture);
	
	void deleteDetailFacture(Integer id);

}
