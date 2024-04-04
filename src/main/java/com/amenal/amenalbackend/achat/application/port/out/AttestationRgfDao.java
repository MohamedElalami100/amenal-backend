package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.AttestationRgf;

public interface AttestationRgfDao {
	AttestationRgf findAttestationRgfById(Integer id);
	
	List<AttestationRgf> findAllAttestationRgfs();
	
	AttestationRgf saveAttestationRgf(AttestationRgf attestationRgf);
	
	AttestationRgf updateAttestationRgf(AttestationRgf attestationRgf);
	
	void deleteAttestationRgf(Integer id);

}
