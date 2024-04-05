package com.amenal.amenalbackend.achat.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.FactureEntity;
import com.amenal.amenalbackend.achat.adapter.out.postgres.repositories.FactureRepository;
import com.amenal.amenalbackend.achat.application.domain.Facture;
import com.amenal.amenalbackend.achat.application.dto.FactureDto;
import com.amenal.amenalbackend.achat.application.port.out.FactureDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class FactureDaoAdapter implements FactureDao {

	@Autowired
	private FactureRepository factureRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public FactureDto findFactureById(Integer id) {
		FactureEntity factureEntity = factureRepository.findById(id).get();
		
		FactureDto factureDto = modelMapper.map(factureEntity, FactureDto.class);
		Facture facture = modelMapper.map(factureEntity, Facture.class);

		// Set attributes for the FactureDto object
		factureDto.setMntHt(facture.getMntHt());
		factureDto.setMntTtc(facture.getMntTtc());
		factureDto.setMntTva(facture.getMntTva());

		return factureDto;
	}

	@Override
	public List<FactureDto> findAllFactures() {
	    List<FactureEntity> factureEntities = factureRepository.findAll();
	    List<FactureDto> factureDtos = new ArrayList<>();

	    for (FactureEntity factureEntity : factureEntities) {
	        FactureDto factureDto = modelMapper.map(factureEntity, FactureDto.class);
	        Facture facture = modelMapper.map(factureEntity, Facture.class);

	        // Set attributes for the FactureDto object
	        factureDto.setMntHt(facture.getMntHt());
	        factureDto.setMntTtc(facture.getMntTtc());
	        factureDto.setMntTva(facture.getMntTva());

	        factureDtos.add(factureDto);
	    }

	    return factureDtos;
	}


	@Override
	public Facture saveFacture(Facture facture) {
		FactureEntity factureEntity = modelMapper.map(facture, FactureEntity.class);
		FactureEntity savedEntity = factureRepository.save(factureEntity);
		return modelMapper.map(savedEntity, Facture.class);
	}

	@Override
	public Facture updateFacture(Facture facture) {
		FactureEntity existingEntity = factureRepository.findById(facture.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Facture to existingEntity
		modelMapper.map(facture, existingEntity);

		FactureEntity updatedEntity = factureRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Facture.class);
	}

	@Override
	public void deleteFacture(Integer id) {
		// Check if Facture with the given ID exists
		FactureEntity factureEntity = factureRepository.findById(id).orElseThrow();

		// Delete the entity
		factureRepository.delete(factureEntity);
	}

}
