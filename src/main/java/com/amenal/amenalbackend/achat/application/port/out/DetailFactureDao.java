package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailFacture;
import com.amenal.amenalbackend.achat.application.dto.DetailFactureDto;

public interface DetailFactureDao {
	DetailFactureDto findDetailFactureById(Integer id);
	
	List<DetailFactureDto> findAllDetailFactures();
	
	DetailFacture saveDetailFacture(DetailFacture detailFacture);
	
	DetailFacture updateDetailFacture(DetailFacture detailFacture);
	
	void deleteDetailFacture(Integer id);

}
