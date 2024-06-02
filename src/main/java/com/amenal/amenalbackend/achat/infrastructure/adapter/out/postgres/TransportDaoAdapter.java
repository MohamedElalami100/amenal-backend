package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Transport;
import com.amenal.amenalbackend.achat.core.port.out.TransportDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.TransportEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.TransportRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class TransportDaoAdapter implements TransportDao {

	@Autowired
	private TransportRepository transportRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Transport findTransportById(Integer id) {
		TransportEntity transportEntity = transportRepository.findById(id).get();
		Transport transport = modelMapper.map(transportEntity, Transport.class);
		return transport;
	}

	@Override
	public List<Transport> findAllTransports() {
		List<TransportEntity> transportEntities = transportRepository.findAll();
		return transportEntities.stream().map(transportEntity -> modelMapper.map(transportEntity, Transport.class))
				.collect(Collectors.toList());
	}

	@Override
	public Transport saveTransport(Transport transport) {
		TransportEntity transportEntity = modelMapper.map(transport, TransportEntity.class);
		TransportEntity savedEntity = transportRepository.save(transportEntity);
		return modelMapper.map(savedEntity, Transport.class);
	}

	@Override
	public Transport updateTransport(Transport transport) {
		TransportEntity existingEntity = transportRepository.findById(transport.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Transport to existingEntity
		TransportEntity newEntity = modelMapper.map(transport, TransportEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		TransportEntity updatedEntity = transportRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Transport.class);
	}

	@Override
	public void deleteTransport(Integer id) {
		// Check if Transport with the given ID exists
		TransportEntity transportEntity = transportRepository.findById(id).orElseThrow();

		// Delete the entity
		transportRepository.delete(transportEntity);
	}

}
