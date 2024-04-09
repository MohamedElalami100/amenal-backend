package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailQualiteAttenteEntity;

@Repository
public interface DetailQualiteAttenteRepository extends JpaRepository<DetailQualiteAttenteEntity, Integer>{
	@Query("SELECT d FROM DetailQualiteAttenteEntity d WHERE d.id <> :id AND d.metre.budget.avenant.id = :avenantId")
	List<DetailQualiteAttenteEntity> getOtherDetailsById(Integer id, Integer avenantId);
	
	@Query("SELECT d FROM DetailQualiteAttenteEntity d WHERE d.metre.budget.avenant.id = :id")
	List<DetailQualiteAttenteEntity> getDetailQualiteAttentesByAvenantId(Integer id);
}
