package com.amenal.amenalbackend.budget.adapter.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.BudgetAchatAvEntity;
import com.amenal.amenalbackend.budget.adapter.out.postgres.repositories.BudgetAchatAvRepository;
import com.amenal.amenalbackend.budget.application.domain.BudgetAchatAv;
import com.amenal.amenalbackend.budget.application.port.out.BudgetAchatAvDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class BudgetAchatAvDaoAdapter implements BudgetAchatAvDao {

	@Autowired
	private BudgetAchatAvRepository budgetAchatAvRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BudgetAchatAv findBudgetAchatAvById(Integer id) {
		BudgetAchatAvEntity budgetAchatAvEntity = budgetAchatAvRepository.findById(id).get();
		BudgetAchatAv budgetAchatAv = modelMapper.map(budgetAchatAvEntity, BudgetAchatAv.class);
		return budgetAchatAv;
	}

	@Override
	public List<BudgetAchatAv> findAllBudgetAchatAvs() {
		List<BudgetAchatAvEntity> budgetAchatAvEntities = budgetAchatAvRepository.findAll();
		return budgetAchatAvEntities.stream().map(budgetAchatAvEntity -> modelMapper.map(budgetAchatAvEntity, BudgetAchatAv.class))
				.collect(Collectors.toList());
	}

	@Override
	public BudgetAchatAv saveBudgetAchatAv(BudgetAchatAv budgetAchatAv) {
		BudgetAchatAvEntity budgetAchatAvEntity = modelMapper.map(budgetAchatAv, BudgetAchatAvEntity.class);
		BudgetAchatAvEntity savedEntity = budgetAchatAvRepository.save(budgetAchatAvEntity);
		return modelMapper.map(savedEntity, BudgetAchatAv.class);
	}

	@Override
	public BudgetAchatAv updateBudgetAchatAv(BudgetAchatAv budgetAchatAv) {
		BudgetAchatAvEntity existingEntity = budgetAchatAvRepository.findById(budgetAchatAv.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from BudgetAchatAv to existingEntity
		modelMapper.map(budgetAchatAv, existingEntity);

		BudgetAchatAvEntity updatedEntity = budgetAchatAvRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, BudgetAchatAv.class);
	}

	@Override
	public void deleteBudgetAchatAv(Integer id) {
		// Check if BudgetAchatAv with the given ID exists
		BudgetAchatAvEntity budgetAchatAvEntity = budgetAchatAvRepository.findById(id).orElseThrow();

		// Delete the entity
		budgetAchatAvRepository.delete(budgetAchatAvEntity);
	}

	@Override
	public BudgetAchatAv getOneBudgetAchatByAvenantId(Integer id) {
		List<BudgetAchatAvEntity> budgetAchatAvEntities = budgetAchatAvRepository.getBudgesByAvenantId(id);
		List<BudgetAchatAv> budgetAchatAvs = budgetAchatAvEntities.stream().map(budgetAchatAvEntity -> modelMapper.map(budgetAchatAvEntity, BudgetAchatAv.class))
				.collect(Collectors.toList());
		
		if(!budgetAchatAvs.isEmpty()) {
			return budgetAchatAvs.get(0);
		}
		
		return null;
	}

}
