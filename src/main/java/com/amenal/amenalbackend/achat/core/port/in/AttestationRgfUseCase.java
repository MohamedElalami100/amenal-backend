package com.amenal.amenalbackend.achat.core.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.AttestationRgf;
import com.amenal.amenalbackend.achat.core.port.out.AttestationRgfDao;

public class AttestationRgfUseCase {
	
	private AttestationRgfDao attestationRgfDao;
	
	public AttestationRgfUseCase(AttestationRgfDao attestationRgfDao) {
		this.attestationRgfDao = attestationRgfDao;
	}

	public AttestationRgf findAttestationRgfById(Integer id) {
	    return attestationRgfDao.findAttestationRgfById(id);
	}

	public List<AttestationRgf> findAllAttestationRgfs() {
		return attestationRgfDao.findAllAttestationRgfs();
	}
	
	public AttestationRgf saveAttestationRgf(AttestationRgf attestationRgf) {
		return attestationRgfDao.saveAttestationRgf(attestationRgf);
	}
	
	public AttestationRgf updateAttestationRgf(AttestationRgf attestationRgf) {
		return attestationRgfDao.updateAttestationRgf(attestationRgf);
	}
	
	public void deleteAttestationRgf(Integer id) {
		attestationRgfDao.deleteAttestationRgf(id);
	}	

}
