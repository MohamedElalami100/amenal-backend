package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.DetailFactureEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.DetailFactureRepository;
import com.amenal.amenalbackend.achat.application.domain.DetailFacture;
import com.amenal.amenalbackend.achat.application.dto.DetailFactureDto;
import com.amenal.amenalbackend.achat.application.port.out.DetailFactureDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailFactureDaoAdapter implements DetailFactureDao {

	@Autowired
	private DetailFactureRepository detailFactureRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailFactureDto findDetailFactureById(Integer id) {
		DetailFactureEntity detailFactureEntity = detailFactureRepository.findById(id).get();
		
		DetailFactureDto detailFactureDto = modelMapper.map(detailFactureEntity, DetailFactureDto.class);
		DetailFacture detailFacture = modelMapper.map(detailFactureEntity, DetailFacture.class);

		// Set attributes for the DetailFactureDto object
		detailFactureDto.setMntHt(detailFacture.getMntHt());
		detailFactureDto.setMntTtc(detailFacture.getMntTtc());
		detailFactureDto.setMntTva(detailFacture.getMntTva());

		return detailFactureDto;
	}

	@Override
	public List<DetailFactureDto> findAllDetailFactures() {
	    List<DetailFactureEntity> detailFactureEntities = detailFactureRepository.findAll();
	    List<DetailFactureDto> detailFactureDtos = new ArrayList<>();

	    for (DetailFactureEntity detailFactureEntity : detailFactureEntities) {
	        DetailFactureDto detailFactureDto = modelMapper.map(detailFactureEntity, DetailFactureDto.class);
	        DetailFacture detailFacture = modelMapper.map(detailFactureEntity, DetailFacture.class);

	        // Set attributes for the DetailFactureDto object
	        detailFactureDto.setMntHt(detailFacture.getMntHt());
	        detailFactureDto.setMntTtc(detailFacture.getMntTtc());
	        detailFactureDto.setMntTva(detailFacture.getMntTva());

	        detailFactureDtos.add(detailFactureDto);
	    }

	    return detailFactureDtos;
	}


	@Override
	public DetailFacture saveDetailFacture(DetailFacture detailFacture) {
		DetailFactureEntity detailFactureEntity = modelMapper.map(detailFacture, DetailFactureEntity.class);
		DetailFactureEntity savedEntity = detailFactureRepository.save(detailFactureEntity);
		return modelMapper.map(savedEntity, DetailFacture.class);
	}

	@Override
	public DetailFacture updateDetailFacture(DetailFacture detailFacture) {
		DetailFactureEntity existingEntity = detailFactureRepository.findById(detailFacture.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from DetailFacture to existingEntity
		modelMapper.map(detailFacture, existingEntity);

		DetailFactureEntity updatedEntity = detailFactureRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailFacture.class);
	}

	@Override
	public void deleteDetailFacture(Integer id) {
		// Check if DetailFacture with the given ID exists
		DetailFactureEntity detailFactureEntity = detailFactureRepository.findById(id).orElseThrow();

		// Delete the entity
		detailFactureRepository.delete(detailFactureEntity);
	}

}
