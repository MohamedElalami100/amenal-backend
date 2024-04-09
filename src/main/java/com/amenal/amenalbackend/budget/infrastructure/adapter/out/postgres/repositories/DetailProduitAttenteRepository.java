package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailProduitAttenteEntity;

@Repository
public interface DetailProduitAttenteRepository extends JpaRepository<DetailProduitAttenteEntity, Integer>{
	
	@Query("SELECT d FROM DetailProduitAttenteEntity d WHERE d.id <> :id AND d.metre.budget.avenant.id = :avenantId")
	List<DetailProduitAttenteEntity> getOtherDetailsById(Integer id, Integer avenantId);
	
	@Query("SELECT d FROM DetailProduitAttenteEntity d WHERE d.metre.budget.avenant.id = :id")
	List<DetailProduitAttenteEntity> getDetailProduitAttentesByAvenantId(Integer id);
}
