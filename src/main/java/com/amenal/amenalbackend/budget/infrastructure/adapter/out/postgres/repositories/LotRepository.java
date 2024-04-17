package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.LotEntity;

@Repository
public interface LotRepository extends JpaRepository<LotEntity, Integer> {
    @Query("SELECT l FROM LotEntity l WHERE l.id IN (SELECT t.lot.id FROM TacheEntity t WHERE t.produit.id = :id)")
    List<LotEntity> getLotsByProduitId(Integer id);
    
	@Query("SELECT l FROM LotEntity l WHERE l.id IN (SELECT t.lot.id FROM TacheEntity t WHERE t.produit.metre.budget.avenant.id = :id)")
    List<LotEntity> getLotsByAvenantId(Integer id);
	
	@Query("SELECT p FROM LotEntity p WHERE p.project.id = :id")
	List<LotEntity> getLotsByProjectId(Integer id);

	@Query("SELECT p FROM LotEntity p WHERE p.project.id = :id AND p.designation = :designation")
	List<LotEntity> getLotsByProjectIdAndDesignation(Integer id, String designation);
	
	@Query("SELECT l FROM LotEntity l WHERE  l.id IN (SELECT t.lot.id FROM TacheEntity t WHERE t.produit.metre.budget.avenant.id = :id) And :charge IN (SELECT c.charge FROM DetailChargeEntity c WHERE c.tache.lot.id = l.id)")
	List<LotEntity> getLotsByAvenantIdAndCharge(Integer id, String charge);
}
