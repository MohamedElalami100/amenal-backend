package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.achat.core.domain.Besoin;
import com.amenal.amenalbackend.achat.core.port.out.BesoinDao;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.BesoinEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.BesoinRepository;
import com.amenal.amenalbackend.achat.infrastructure.dto.BesoinDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class BesoinDaoAdapter implements BesoinDao {

	@Autowired
	private BesoinRepository besoinRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BesoinDto findBesoinById(Integer id) {
		BesoinEntity besoinEntity = besoinRepository.findById(id).get();
		
		BesoinDto besoinDto = modelMapper.map(besoinEntity, BesoinDto.class);
		Besoin besoin = modelMapper.map(besoinEntity, Besoin.class);

		// Set attributes for the BesoinDto object
		besoinDto.setMntHt(besoin.getMntHt());
		besoinDto.setMntTtc(besoin.getMntTtc());
		besoinDto.setMntTva(besoin.getMntTva());

		return besoinDto;
	}

	@Override
	public List<BesoinDto> findAllBesoins() {
	    List<BesoinEntity> besoinEntities = besoinRepository.findAll();
	    List<BesoinDto> besoinDtos = new ArrayList<>();

	    for (BesoinEntity besoinEntity : besoinEntities) {
	        BesoinDto besoinDto = modelMapper.map(besoinEntity, BesoinDto.class);
	        Besoin besoin = modelMapper.map(besoinEntity, Besoin.class);

	        // Set attributes for the BesoinDto object
	        besoinDto.setMntHt(besoin.getMntHt());
	        besoinDto.setMntTtc(besoin.getMntTtc());
	        besoinDto.setMntTva(besoin.getMntTva());

	        besoinDtos.add(besoinDto);
	    }

	    return besoinDtos;
	}

	@Override
	public Besoin saveBesoin(Besoin besoin) {
		BesoinEntity besoinEntity = modelMapper.map(besoin, BesoinEntity.class);
		BesoinEntity savedEntity = besoinRepository.save(besoinEntity);
		return modelMapper.map(savedEntity, Besoin.class);
	}

	@Override
	public Besoin updateBesoin(Besoin besoin) {
		BesoinEntity existingEntity = besoinRepository.findById(besoin.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Besoin to existingEntity
		modelMapper.map(besoin, existingEntity);

		BesoinEntity updatedEntity = besoinRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Besoin.class);
	}

	@Override
	public void deleteBesoin(Integer id) {
		// Check if Besoin with the given ID exists
		BesoinEntity besoinEntity = besoinRepository.findById(id).orElseThrow();

		// Delete the entity
		besoinRepository.delete(besoinEntity);
	}

}
