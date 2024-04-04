package com.amenal.amenalbackend.achat.application.port.in;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Transport;
import com.amenal.amenalbackend.achat.application.port.out.TransportDao;

public class TransportUseCase {
	
	private TransportDao transportDao;
	
	public TransportUseCase(TransportDao transportDao) {
		this.transportDao = transportDao;
	}

	public Transport findTransportById(Integer id) {
	    return transportDao.findTransportById(id);
	}

	public List<Transport> findAllTransports() {
		return transportDao.findAllTransports();
	}
	
	public Transport saveTransport(Transport transport) {
		return transportDao.saveTransport(transport);
	}
	
	public Transport updateTransport(Transport transport) {
		return transportDao.updateTransport(transport);
	}
	
	public void deleteTransport(Integer id) {
		transportDao.deleteTransport(id);
	}	

}
