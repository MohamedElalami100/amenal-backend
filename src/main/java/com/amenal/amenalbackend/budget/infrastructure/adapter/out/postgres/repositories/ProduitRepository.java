package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProduitEntity;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitEntity, Integer>{

	@Query("SELECT p FROM ProduitEntity p WHERE p.metre.avenant.id = :id")
    List<ProduitEntity> getProduitsByAvenantId(Integer id);
	
	@Query("SELECT p FROM ProduitEntity p WHERE p.metre.avenant.id = :id AND p.designation = :designation")
    List<ProduitEntity> getProduitsByAvenantIdAndDesignation(Integer id, String designation);

	@Query("SELECT p FROM ProduitEntity p WHERE p.metre.avenant.id = :id And :charge IN (SELECT c.charge FROM DetailChargeEntity c WHERE c.tache.produit.id = p.id)")
	List<ProduitEntity> getProduitsByAvenantIdAndCharge(Integer id, String charge);
	
}
