package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.BudgetAchatAvEntity;

@Repository
public interface BudgetAchatAvRepository extends JpaRepository<BudgetAchatAvEntity, Integer>{
	
	@Query("SELECT p FROM BudgetAchatAvEntity p WHERE p.avenant.id = :id")
	List<BudgetAchatAvEntity> getBudgesByAvenantId(Integer id);

}
