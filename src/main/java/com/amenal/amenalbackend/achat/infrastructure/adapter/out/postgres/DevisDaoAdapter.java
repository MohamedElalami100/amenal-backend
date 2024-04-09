package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Devis;
import com.amenal.amenalbackend.achat.core.port.out.DevisDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DevisRepository;
import com.amenal.amenalbackend.achat.infrastructure.dto.DevisDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DevisDaoAdapter implements DevisDao {

	@Autowired
	private DevisRepository devisRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DevisDto findDevisById(Integer id) {
		DevisEntity devisEntity = devisRepository.findById(id).get();
		
		DevisDto devisDto = modelMapper.map(devisEntity, DevisDto.class);
		Devis devis = modelMapper.map(devisEntity, Devis.class);

		// Set attributes for the DevisDto object
		devisDto.setMntHt(devis.getMntHt());
		devisDto.setMntTtc(devis.getMntTtc());
		devisDto.setMntTva(devis.getMntTva());

		return devisDto;
	}

	@Override
	public List<DevisDto> findAllDeviss() {
	    List<DevisEntity> devisEntities = devisRepository.findAll();
	    List<DevisDto> devisDtos = new ArrayList<>();

	    for (DevisEntity devisEntity : devisEntities) {
	        DevisDto devisDto = modelMapper.map(devisEntity, DevisDto.class);
	        Devis devis = modelMapper.map(devisEntity, Devis.class);

	        // Set attributes for the DevisDto object
	        devisDto.setMntHt(devis.getMntHt());
	        devisDto.setMntTtc(devis.getMntTtc());
	        devisDto.setMntTva(devis.getMntTva());

	        devisDtos.add(devisDto);
	    }

	    return devisDtos;
	}

	@Override
	public Devis saveDevis(Devis devis) {
		DevisEntity devisEntity = modelMapper.map(devis, DevisEntity.class);
		DevisEntity savedEntity = devisRepository.save(devisEntity);
		return modelMapper.map(savedEntity, Devis.class);
	}

	@Override
	public Devis updateDevis(Devis devis) {
		DevisEntity existingEntity = devisRepository.findById(devis.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Devis to existingEntity
		modelMapper.map(devis, existingEntity);

		DevisEntity updatedEntity = devisRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Devis.class);
	}

	@Override
	public void deleteDevis(Integer id) {
		// Check if Devis with the given ID exists
		DevisEntity devisEntity = devisRepository.findById(id).orElseThrow();

		// Delete the entity
		devisRepository.delete(devisEntity);
	}

}
