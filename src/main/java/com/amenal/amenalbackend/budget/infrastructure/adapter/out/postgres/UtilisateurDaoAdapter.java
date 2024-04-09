package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.Utilisateur;
import com.amenal.amenalbackend.budget.core.port.out.UtilisateurDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.UtilisateurEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.UtilisateurRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class UtilisateurDaoAdapter implements UtilisateurDao {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Utilisateur findUtilisateurById(Integer id) {
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findById(id).get();
		Utilisateur utilisateur = modelMapper.map(utilisateurEntity, Utilisateur.class);
		return utilisateur;
	}

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		List<UtilisateurEntity> utilisateurEntities = utilisateurRepository.findAll();
		return utilisateurEntities.stream().map(utilisateurEntity -> modelMapper.map(utilisateurEntity, Utilisateur.class))
				.collect(Collectors.toList());
	}

	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		UtilisateurEntity utilisateurEntity = modelMapper.map(utilisateur, UtilisateurEntity.class);
		UtilisateurEntity savedEntity = utilisateurRepository.save(utilisateurEntity);
		return modelMapper.map(savedEntity, Utilisateur.class);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		UtilisateurEntity existingEntity = utilisateurRepository.findById(utilisateur.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Utilisateur to existingEntity
		modelMapper.map(utilisateur, existingEntity);

		UtilisateurEntity updatedEntity = utilisateurRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Utilisateur.class);
	}

	@Override
	public void deleteUtilisateur(Integer id) {
		// Check if Utilisateur with the given ID exists
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findById(id).orElseThrow();

		// Delete the entity
		utilisateurRepository.delete(utilisateurEntity);
	}

}
