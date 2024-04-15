package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitAttenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.DetailProduitAttente;
import com.amenal.amenalbackend.budget.core.port.out.DetailProduitAttenteDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.DetailProduitAttenteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailProduitAttenteDaoAdapter implements DetailProduitAttenteDao {

	@Autowired
	private DetailProduitAttenteRepository detailProduitAttenteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailProduitAttente findDetailProduitAttenteById(Integer id) {
		DetailProduitAttenteEntity detailProduitAttenteEntity = detailProduitAttenteRepository.findById(id).get();
		DetailProduitAttente detailProduitAttente = modelMapper.map(detailProduitAttenteEntity, DetailProduitAttente.class);
		return detailProduitAttente;
	}

	@Override
	public List<DetailProduitAttente> findAllDetailProduitAttentes() {
		List<DetailProduitAttenteEntity> detailProduitAttenteEntities = detailProduitAttenteRepository.findAll();
		return detailProduitAttenteEntities.stream()
				.map(detailProduitAttenteEntity -> modelMapper.map(detailProduitAttenteEntity, DetailProduitAttente.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailProduitAttente saveDetailProduitAttente(DetailProduitAttente detailProduitAttente) {
		DetailProduitAttenteEntity detailProduitAttenteEntity = modelMapper.map(detailProduitAttente,
				DetailProduitAttenteEntity.class);
		DetailProduitAttenteEntity savedEntity = detailProduitAttenteRepository.save(detailProduitAttenteEntity);
		return modelMapper.map(savedEntity, DetailProduitAttente.class);
	}

	@Override
	public DetailProduitAttente saveDetailProduitAttenteWithErreur(DetailProduitAttente detailProduitAttente) {
		DetailProduitAttenteEntity detailProduitAttenteEntity = modelMapper.map(detailProduitAttente,
				DetailProduitAttenteEntity.class);
		DetailProduitAttenteEntity savedEntity = detailProduitAttenteRepository.save(detailProduitAttenteEntity);
		return modelMapper.map(savedEntity, DetailProduitAttente.class);
	}

	@Override
	public DetailProduitAttente updateDetailProduitAttente(DetailProduitAttente detailProduitAttente) {
		detailProduitAttenteRepository.findById(detailProduitAttente.getId())
				.orElseThrow();
		// Use ModelMapper to map non-null properties from DetailProduitAttente to
		// existingEntity
		DetailProduitAttenteEntity newEntity = modelMapper.map(detailProduitAttente, DetailProduitAttenteEntity.class);

		DetailProduitAttenteEntity updatedEntity = detailProduitAttenteRepository.save(newEntity);
		return modelMapper.map(updatedEntity, DetailProduitAttente.class);
	}

	@Override
	public void deleteDetailProduitAttente(Integer id) {
		// Check if DetailProduitAttente with the given ID exists
		DetailProduitAttenteEntity detailProduitAttenteEntity = detailProduitAttenteRepository.findById(id).orElseThrow();

		// Delete the entity
		detailProduitAttenteRepository.delete(detailProduitAttenteEntity);
	}

	@Override
	public List<DetailProduitAttente> getDetailProduitAttentesByAvenantId(Integer id) {
		List<DetailProduitAttenteEntity> detailProduitAttenteEntities = detailProduitAttenteRepository
				.getDetailProduitAttentesByAvenantId(id);
		return detailProduitAttenteEntities.stream()
				.map(detailProduitAttenteEntity -> modelMapper.map(detailProduitAttenteEntity, DetailProduitAttente.class))
				.collect(Collectors.toList());
	}
}
