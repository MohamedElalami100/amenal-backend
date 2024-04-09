package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DetailDevisEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DetailDevisRepository;
import com.amenal.amenalbackend.achat.application.domain.DetailDevis;
import com.amenal.amenalbackend.achat.application.dto.DetailDevisDto;
import com.amenal.amenalbackend.achat.application.port.out.DetailDevisDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailDevisDaoAdapter implements DetailDevisDao {

	@Autowired
	private DetailDevisRepository detailDevisRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailDevisDto findDetailDevisById(Integer id) {
		DetailDevisEntity detailDevisEntity = detailDevisRepository.findById(id).get();
		
		DetailDevisDto detailDevisDto = modelMapper.map(detailDevisEntity, DetailDevisDto.class);
		DetailDevis detailDevis = modelMapper.map(detailDevisEntity, DetailDevis.class);

		// Set attributes for the DetailDevisDto object
		detailDevisDto.setMntHt(detailDevis.getMntHt());
		detailDevisDto.setMntTtc(detailDevis.getMntTtc());
		detailDevisDto.setMntTva(detailDevis.getMntTva());

		return detailDevisDto;
	}

	@Override
	public List<DetailDevisDto> findAllDetailDeviss() {
	    List<DetailDevisEntity> detailDevisEntities = detailDevisRepository.findAll();
	    List<DetailDevisDto> detailDevisDtos = new ArrayList<>();

	    for (DetailDevisEntity detailDevisEntity : detailDevisEntities) {
	        DetailDevisDto detailDevisDto = modelMapper.map(detailDevisEntity, DetailDevisDto.class);
	        DetailDevis detailDevis = modelMapper.map(detailDevisEntity, DetailDevis.class);

	        // Set attributes for the DetailDevisDto object
	        detailDevisDto.setMntHt(detailDevis.getMntHt());
	        detailDevisDto.setMntTtc(detailDevis.getMntTtc());
	        detailDevisDto.setMntTva(detailDevis.getMntTva());

	        detailDevisDtos.add(detailDevisDto);
	    }

	    return detailDevisDtos;
	}

	@Override
	public DetailDevis saveDetailDevis(DetailDevis detailDevis) {
		DetailDevisEntity detailDevisEntity = modelMapper.map(detailDevis, DetailDevisEntity.class);
		DetailDevisEntity savedEntity = detailDevisRepository.save(detailDevisEntity);
		return modelMapper.map(savedEntity, DetailDevis.class);
	}

	@Override
	public DetailDevis updateDetailDevis(DetailDevis detailDevis) {
		DetailDevisEntity existingEntity = detailDevisRepository.findById(detailDevis.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailDevis to existingEntity
		modelMapper.map(detailDevis, existingEntity);

		DetailDevisEntity updatedEntity = detailDevisRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailDevis.class);
	}

	@Override
	public void deleteDetailDevis(Integer id) {
		// Check if DetailDevis with the given ID exists
		DetailDevisEntity detailDevisEntity = detailDevisRepository.findById(id).orElseThrow();

		// Delete the entity
		detailDevisRepository.delete(detailDevisEntity);
	}

}
