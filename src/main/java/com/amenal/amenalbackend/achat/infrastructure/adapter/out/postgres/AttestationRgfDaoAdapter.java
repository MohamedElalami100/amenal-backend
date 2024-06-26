package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.AttestationRgf;
import com.amenal.amenalbackend.achat.core.port.out.AttestationRgfDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.AttestationRgfEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.AttestationRgfRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class AttestationRgfDaoAdapter implements AttestationRgfDao {

	@Autowired
	private AttestationRgfRepository attestationRgfRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AttestationRgf findAttestationRgfById(Integer id) {
		AttestationRgfEntity attestationRgfEntity = attestationRgfRepository.findById(id).get();
		AttestationRgf attestationRgf = modelMapper.map(attestationRgfEntity, AttestationRgf.class);
		return attestationRgf;
	}

	@Override
	public List<AttestationRgf> findAllAttestationRgfs() {
		List<AttestationRgfEntity> attestationRgfEntities = attestationRgfRepository.findAll();
		return attestationRgfEntities.stream().map(attestationRgfEntity -> modelMapper.map(attestationRgfEntity, AttestationRgf.class))
				.collect(Collectors.toList());
	}

	@Override
	public AttestationRgf saveAttestationRgf(AttestationRgf attestationRgf) {
		AttestationRgfEntity attestationRgfEntity = modelMapper.map(attestationRgf, AttestationRgfEntity.class);
		AttestationRgfEntity savedEntity = attestationRgfRepository.save(attestationRgfEntity);
		return modelMapper.map(savedEntity, AttestationRgf.class);
	}

	@Override
	public AttestationRgf updateAttestationRgf(AttestationRgf attestationRgf) {
		AttestationRgfEntity existingEntity = attestationRgfRepository.findById(attestationRgf.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from AttestationRgf to existingEntity
		AttestationRgfEntity newEntity = modelMapper.map(attestationRgf, AttestationRgfEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		AttestationRgfEntity updatedEntity = attestationRgfRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, AttestationRgf.class);
	}

	@Override
	public void deleteAttestationRgf(Integer id) {
		// Check if AttestationRgf with the given ID exists
		AttestationRgfEntity attestationRgfEntity = attestationRgfRepository.findById(id).orElseThrow();

		// Delete the entity
		attestationRgfRepository.delete(attestationRgfEntity);
	}

}
