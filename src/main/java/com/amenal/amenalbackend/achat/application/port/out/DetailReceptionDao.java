package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailReception;

public interface DetailReceptionDao {
	DetailReception findDetailReceptionById(Integer id);
	
	List<DetailReception> findAllDetailReceptions();
	
	DetailReception saveDetailReception(DetailReception detailReception);
	
	DetailReception updateDetailReception(DetailReception detailReception);
	
	void deleteDetailReception(Integer id);

}
