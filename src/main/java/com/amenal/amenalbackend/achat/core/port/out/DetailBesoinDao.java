package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailBesoin;

public interface DetailBesoinDao {
	DetailBesoin findDetailBesoinById(Integer id);
	
	List<DetailBesoin> findAllDetailBesoins();
	
	DetailBesoin saveDetailBesoin(DetailBesoin detailBesoin);
	
	DetailBesoin updateDetailBesoin(DetailBesoin detailBesoin);
	
	void deleteDetailBesoin(Integer id);

}
