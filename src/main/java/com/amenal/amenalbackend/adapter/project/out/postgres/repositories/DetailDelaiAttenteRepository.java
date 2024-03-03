package com.amenal.amenalbackend.adapter.project.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailDelaiAttenteEntity;

@Repository
public interface DetailDelaiAttenteRepository extends JpaRepository<DetailDelaiAttenteEntity, Integer>{
	
	@Query("SELECT d FROM DetailDelaiAttenteEntity d WHERE d.id <> :id AND d.metre.budget.avenant.id = :avenantId")
	List<DetailDelaiAttenteEntity> getOtherDetailsById(Integer id, Integer avenantId);
	
	@Query("SELECT d FROM DetailDelaiAttenteEntity d WHERE d.metre.budget.avenant.id = :id")
	List<DetailDelaiAttenteEntity> getDetailDelaiAttentesByAvenantId(Integer id);
}
