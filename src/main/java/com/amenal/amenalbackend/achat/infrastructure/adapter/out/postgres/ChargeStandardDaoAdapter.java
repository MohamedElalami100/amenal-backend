package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.ChargeStandard;
import com.amenal.amenalbackend.achat.core.port.out.ChargeStandardDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ChargeStandardEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ChargeStandardRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class ChargeStandardDaoAdapter implements ChargeStandardDao {

	@Autowired
	private ChargeStandardRepository chargeStandardRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ChargeStandard findChargeStandardById(Integer id) {
		ChargeStandardEntity chargeStandardEntity = chargeStandardRepository.findById(id).get();
		ChargeStandard chargeStandard = modelMapper.map(chargeStandardEntity, ChargeStandard.class);
		return chargeStandard;
	}

	@Override
	public List<ChargeStandard> findAllChargeStandards() {
		List<ChargeStandardEntity> chargeStandardEntities = chargeStandardRepository.findAll();
		return chargeStandardEntities.stream().map(chargeStandardEntity -> modelMapper.map(chargeStandardEntity, ChargeStandard.class))
				.collect(Collectors.toList());
	}

	@Override
	public ChargeStandard saveChargeStandard(ChargeStandard chargeStandard) {
		ChargeStandardEntity chargeStandardEntity = modelMapper.map(chargeStandard, ChargeStandardEntity.class);
		ChargeStandardEntity savedEntity = chargeStandardRepository.save(chargeStandardEntity);
		return modelMapper.map(savedEntity, ChargeStandard.class);
	}

	@Override
	public ChargeStandard updateChargeStandard(ChargeStandard chargeStandard) {
		ChargeStandardEntity existingEntity = chargeStandardRepository.findById(chargeStandard.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from ChargeStandard to existingEntity
		ChargeStandardEntity newEntity = modelMapper.map(chargeStandard, ChargeStandardEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		ChargeStandardEntity updatedEntity = chargeStandardRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, ChargeStandard.class);
	}

	@Override
	public void deleteChargeStandard(Integer id) {
		// Check if ChargeStandard with the given ID exists
		ChargeStandardEntity chargeStandardEntity = chargeStandardRepository.findById(id).orElseThrow();

		// Delete the entity
		chargeStandardRepository.delete(chargeStandardEntity);
	}

}
