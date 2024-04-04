package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailFacture;

public interface DetailFactureDao {
	DetailFacture findDetailFactureById(Integer id);
	
	List<DetailFacture> findAllDetailFactures();
	
	DetailFacture saveDetailFacture(DetailFacture detailFacture);
	
	DetailFacture updateDetailFacture(DetailFacture detailFacture);
	
	void deleteDetailFacture(Integer id);

}
