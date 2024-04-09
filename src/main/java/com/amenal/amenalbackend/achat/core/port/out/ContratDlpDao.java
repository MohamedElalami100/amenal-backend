package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.ContratDlp;

public interface ContratDlpDao {
	ContratDlp findContratDlpById(Integer id);
	
	List<ContratDlp> findAllContratDlps();
	
	ContratDlp saveContratDlp(ContratDlp contratDlp);
	
	ContratDlp updateContratDlp(ContratDlp contratDlp);
	
	void deleteContratDlp(Integer id);

}
