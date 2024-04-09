package com.amenal.amenalbackend.achat.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.achat.core.domain.Transport;

public interface TransportDao {
	Transport findTransportById(Integer id);
	
	List<Transport> findAllTransports();
	
	Transport saveTransport(Transport transport);
	
	Transport updateTransport(Transport transport);
	
	void deleteTransport(Integer id);

}
