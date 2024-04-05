package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.DetailReception;
import com.amenal.amenalbackend.achat.application.dto.DetailReceptionDto;

public interface DetailReceptionDao {
	DetailReceptionDto findDetailReceptionById(Integer id);
	
	List<DetailReceptionDto> findAllDetailReceptions();
	
	DetailReception saveDetailReception(DetailReception detailReception);
	
	DetailReception updateDetailReception(DetailReception detailReception);
	
	void deleteDetailReception(Integer id);

}
