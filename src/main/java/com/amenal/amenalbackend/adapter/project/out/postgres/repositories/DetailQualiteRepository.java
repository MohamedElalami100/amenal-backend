package com.amenal.amenalbackend.adapter.project.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailQualiteEntity;

@Repository
public interface DetailQualiteRepository extends JpaRepository<DetailQualiteEntity, Integer>{
	@Query("SELECT l FROM DetailQualiteEntity l WHERE l.groupe.id = :grpId")
    List<DetailQualiteEntity> getDetailQualiteByGrpId(Integer grpId);
	
    @Query("SELECT l FROM DetailQualiteEntity l WHERE l.groupe.tache.produit.metre.budget.avenant.id = :avenantId")
    List<DetailQualiteEntity> getDetailQualitesByAvenantId(Integer avenantId);
}
