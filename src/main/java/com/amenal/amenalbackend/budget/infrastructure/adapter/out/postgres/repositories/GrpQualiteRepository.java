package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.GrpQualiteEntity;

@Repository
public interface GrpQualiteRepository extends JpaRepository<GrpQualiteEntity, Integer>{
	@Query("SELECT l FROM GrpQualiteEntity l WHERE l.tache.id = :tacheId")
    List<GrpQualiteEntity> getGrpQualiteByTacheId(Integer tacheId);
	
	@Query("SELECT l FROM GrpQualiteEntity l WHERE l.tache.produit.metre.avenant.id = :id AND l.titre = :titre")
	List<GrpQualiteEntity> getGrpQualitesByAvenantIdAndTitre(Integer id, String titre);
	
	@Query("SELECT l FROM GrpQualiteEntity l WHERE l.tache.id = :tacheId AND l.titre = :titre")
	List<GrpQualiteEntity> getGrpQualitesByTacheIdAndTitre(Integer tacheId, String titre);
}
