package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.DetailReception;
import com.amenal.amenalbackend.achat.infrastructure.dto.AddDetailReceptionDto;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailReceptionDto;

public interface DetailReceptionDao {
	DetailReceptionDto findDetailReceptionById(Integer id);
	
	List<DetailReceptionDto> findAllDetailReceptions();

    DetailReception saveDetailReception(AddDetailReceptionDto detailReception);

    DetailReception updateDetailReception(DetailReception detailReception);
	
	void deleteDetailReception(Integer id);

    List<DetailReceptionDto> findByDetailCommandeId(Integer commandeId);
}
