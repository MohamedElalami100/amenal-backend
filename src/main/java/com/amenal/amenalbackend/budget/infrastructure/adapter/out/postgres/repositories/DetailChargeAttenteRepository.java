package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeAttenteEntity;

@Repository
public interface DetailChargeAttenteRepository extends JpaRepository<DetailChargeAttenteEntity, Integer>{

	@Query("SELECT d FROM DetailChargeAttenteEntity d WHERE d.id <> :id AND d.metre.budget.avenant.id = :avenantId")
	List<DetailChargeAttenteEntity> getOtherDetailsById(Integer id, Integer avenantId);
	
	@Query("SELECT d FROM DetailChargeAttenteEntity d WHERE d.metre.budget.avenant.id = :id")
	List<DetailChargeAttenteEntity> getDetailChargeAttentesByAvenantId(Integer id);
}
