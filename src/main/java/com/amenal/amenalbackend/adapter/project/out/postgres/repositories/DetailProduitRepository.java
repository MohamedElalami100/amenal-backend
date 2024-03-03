package com.amenal.amenalbackend.adapter.project.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailProduitEntity;

@Repository
public interface DetailProduitRepository extends JpaRepository<DetailProduitEntity, Integer>{
	   @Query("SELECT l FROM DetailProduitEntity l WHERE l.tache.id = :tacheId")
	    List<DetailProduitEntity> getDetailProduitByTacheId(Integer tacheId);
	   
	    @Query("SELECT l FROM DetailProduitEntity l WHERE l.tache.produit.metre.budget.avenant.id = :avenantId")
	    List<DetailProduitEntity> getDetailProduitsByAvenantId(Integer avenantId);

		@Query("SELECT l FROM DetailProduitEntity l WHERE l.tache.id = :tacheId AND l.reference = :reference")
		List<DetailProduitEntity> getDetailProduitsByTacheIdAndDesignation(Integer id, String reference);
}
