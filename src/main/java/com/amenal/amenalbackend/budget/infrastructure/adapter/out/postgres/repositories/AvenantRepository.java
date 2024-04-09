package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.AvenantEntity;

@Repository
public interface AvenantRepository extends JpaRepository<AvenantEntity, Integer>{

	@Query("SELECT l FROM AvenantEntity l WHERE l.project.id = :id")
	List<AvenantEntity> getAvenantsByProjectId(Integer id);
}
