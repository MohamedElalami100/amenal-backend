package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.core.domain.Avenant;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.AvenantEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.port.out.AvenantDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.AvenantRepository;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

@RequiredArgsConstructor
@Service
@Transactional
public class AvenantDaoAdapter implements AvenantDao {

	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Avenant findAvenantById(Integer id) {
		AvenantEntity avenantEntity = avenantRepository.findById(id).get();
		Avenant avenant = modelMapper.map(avenantEntity, Avenant.class);
		return avenant;
	}

	@Override
	public List<Avenant> findAllAvenants() {
		List<AvenantEntity> avenantEntities = avenantRepository.findAll();
		return avenantEntities.stream().map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class))
				.collect(Collectors.toList());
	}

	@Override
	public Avenant saveAvenant(Avenant avenant) {
		AvenantEntity avenantEntity = modelMapper.map(avenant, AvenantEntity.class);
		AvenantEntity savedEntity = avenantRepository.save(avenantEntity);
		return modelMapper.map(savedEntity, Avenant.class);
	}

	@Override
	public Avenant updateAvenant(Avenant avenant) {
		AvenantEntity existingEntity = avenantRepository.findById(avenant.getId()).orElseThrow();

		AvenantEntity newEntity = modelMapper.map(avenant, AvenantEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

		AvenantEntity updatedEntity = avenantRepository.save(existingEntity);

		return modelMapper.map(updatedEntity, Avenant.class);
	}

	@Override
	public void deleteAvenant(Integer id) {
		// Check if Avenant with the given ID exists
		AvenantEntity avenantEntity = avenantRepository.findById(id).orElseThrow();

		// Delete the entity
		avenantRepository.delete(avenantEntity);
	}

	@Override
	public List<Avenant> getAvenantsByProjectId(Integer id) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		return avenantEntities.stream().map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class))
				.collect(Collectors.toList());
	}

}
