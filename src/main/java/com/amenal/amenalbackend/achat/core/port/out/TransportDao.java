package com.amenal.amenalbackend.achat.application.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.application.domain.Transport;

public interface TransportDao {
	Transport findTransportById(Integer id);
	
	List<Transport> findAllTransports();
	
	Transport saveTransport(Transport transport);
	
	Transport updateTransport(Transport transport);
	
	void deleteTransport(Integer id);

}
