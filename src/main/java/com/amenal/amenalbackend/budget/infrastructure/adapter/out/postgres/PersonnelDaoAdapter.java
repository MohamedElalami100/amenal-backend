package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.PersonnelEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.PersonnelRepository;
import com.amenal.amenalbackend.budget.application.domain.Personnel;
import com.amenal.amenalbackend.budget.application.port.out.PersonnelDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PersonnelDaoAdapter implements PersonnelDao {

	@Autowired
	private PersonnelRepository personnelRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Personnel findPersonnelById(Integer id) {
		PersonnelEntity personnelEntity = personnelRepository.findById(id).get();
		Personnel personnel = modelMapper.map(personnelEntity, Personnel.class);
		return personnel;
	}

	@Override
	public List<Personnel> findAllPersonnels() {
		List<PersonnelEntity> personnelEntities = personnelRepository.findAll();
		return personnelEntities.stream().map(personnelEntity -> modelMapper.map(personnelEntity, Personnel.class))
				.collect(Collectors.toList());
	}

	@Override
	public Personnel savePersonnel(Personnel personnel) {
		personnel.setMatricule(null);
		PersonnelEntity personnelEntity = modelMapper.map(personnel, PersonnelEntity.class);
		PersonnelEntity savedEntity = personnelRepository.save(personnelEntity);
		return modelMapper.map(savedEntity, Personnel.class);
	}

	@Override
	public Personnel updatePersonnel(Personnel personnel) {
		PersonnelEntity existingEntity = personnelRepository.findById(personnel.getMatricule()).orElseThrow();

		// Use ModelMapper to map non-null properties from Personnel to existingEntity
		modelMapper.map(personnel, existingEntity);

		PersonnelEntity updatedEntity = personnelRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Personnel.class);
	}

	@Override
	public void deletePersonnel(Integer id) {
		// Check if Personnel with the given ID exists
		PersonnelEntity personnelEntity = personnelRepository.findById(id).orElseThrow();

		// Delete the entity
		personnelRepository.delete(personnelEntity);
	}

}
