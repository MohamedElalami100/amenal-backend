package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Facture;
import com.amenal.amenalbackend.achat.core.port.out.FactureDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.FactureEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.FactureRepository;
import com.amenal.amenalbackend.achat.infrastructure.dto.FactureDto;

import lombok.RequiredArgsConstructor;

import static com.amenal.amenalbackend.utils.infrastructure.Methods.Copy.copyNonNullProperties;

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
		FactureEntity newEntity = modelMapper.map(facture, FactureEntity.class);

		copyNonNullProperties(newEntity, existingEntity);

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
