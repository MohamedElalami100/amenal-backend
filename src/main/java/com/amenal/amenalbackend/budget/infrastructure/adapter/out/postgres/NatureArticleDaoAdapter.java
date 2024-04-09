package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.core.domain.NatureArticle;
import com.amenal.amenalbackend.budget.core.port.out.NatureArticleDao;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.NatureArticleEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.NatureArticleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class NatureArticleDaoAdapter implements NatureArticleDao {

	@Autowired
	private NatureArticleRepository natureArticleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public NatureArticle findNatureArticleById(Integer id) {
		NatureArticleEntity natureArticleEntity = natureArticleRepository.findById(id).get();
		NatureArticle natureArticle = modelMapper.map(natureArticleEntity, NatureArticle.class);
		return natureArticle;
	}

	@Override
	public List<NatureArticle> findAllNatureArticles() {
		List<NatureArticleEntity> natureArticleEntities = natureArticleRepository.findAll();
		return natureArticleEntities.stream().map(natureArticleEntity -> modelMapper.map(natureArticleEntity, NatureArticle.class))
				.collect(Collectors.toList());
	}

	@Override
	public NatureArticle saveNatureArticle(NatureArticle natureArticle) {
		NatureArticleEntity natureArticleEntity = modelMapper.map(natureArticle, NatureArticleEntity.class);
		NatureArticleEntity savedEntity = natureArticleRepository.save(natureArticleEntity);
		return modelMapper.map(savedEntity, NatureArticle.class);
	}

	@Override
	public NatureArticle updateNatureArticle(NatureArticle natureArticle) {
		NatureArticleEntity existingEntity = natureArticleRepository.findById(natureArticle.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from NatureArticle to existingEntity
		modelMapper.map(natureArticle, existingEntity);

		NatureArticleEntity updatedEntity = natureArticleRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, NatureArticle.class);
	}

	@Override
	public void deleteNatureArticle(Integer id) {
		// Check if NatureArticle with the given ID exists
		NatureArticleEntity natureArticleEntity = natureArticleRepository.findById(id).orElseThrow();

		// Delete the entity
		natureArticleRepository.delete(natureArticleEntity);
	}

}
