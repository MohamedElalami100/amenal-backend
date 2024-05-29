package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DetailChargeEntity;

@Repository
public interface DetailChargeRepository extends JpaRepository<DetailChargeEntity, Integer>{
    @Query("SELECT l FROM DetailChargeEntity l WHERE l.tache.id = :tacheId")
    List<DetailChargeEntity> getDetailChargeByTacheId(Integer tacheId);
    
    @Query("SELECT l FROM DetailChargeEntity l WHERE l.tache.produit.metre.budget.avenant.id = :avenantId")
    List<DetailChargeEntity> getDetailChargesByAvenantId(Integer avenantId);

    @Query("SELECT l FROM DetailChargeEntity l WHERE l.tache.id = :id AND l.charge = :designation")
	List<DetailChargeEntity> getDetailChargesByTacheIdAndDesignation(Integer id, String designation);

    @Query("SELECT l FROM DetailChargeEntity l WHERE l.tache.produit.metre.budget.avenant.project.id = :id")
    List<DetailChargeEntity> getDetailChargesByProjectId(Integer id);
}
