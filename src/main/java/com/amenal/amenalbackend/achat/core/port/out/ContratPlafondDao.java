package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.ContratPlafond;

public interface ContratPlafondDao {
	ContratPlafond findContratPlafondById(Integer id);
	
	List<ContratPlafond> findAllContratPlafonds();
	
	ContratPlafond saveContratPlafond(ContratPlafond contratPlafond);
	
	ContratPlafond updateContratPlafond(ContratPlafond contratPlafond);
	
	void deleteContratPlafond(Integer id);

}
