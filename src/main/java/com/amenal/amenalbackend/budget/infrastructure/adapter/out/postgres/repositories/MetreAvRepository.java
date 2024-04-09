package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.MetreAvEntity;

@Repository
public interface MetreAvRepository extends JpaRepository<MetreAvEntity, Integer>{

	@Query("SELECT p FROM MetreAvEntity p WHERE p.budget.avenant.id = :id")
	List<MetreAvEntity> getMetresByAvenantId(Integer id);

}
