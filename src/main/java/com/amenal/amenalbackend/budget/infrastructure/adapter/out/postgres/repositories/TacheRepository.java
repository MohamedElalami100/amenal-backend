package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.TacheEntity;

@Repository
public interface TacheRepository extends JpaRepository<TacheEntity, Integer> {
	@Query("SELECT l FROM TacheEntity l WHERE l.produit.id = :produitId AND l.lot.id = :lotId")
	List<TacheEntity> getTachesByProduitIdAndLotId(Integer produitId, Integer lotId);

	@Query("SELECT l FROM TacheEntity l WHERE l.lot.id = :lotId")
	List<TacheEntity> getActiviteByLotId(Integer lotId);

	@Query("SELECT l FROM TacheEntity l WHERE l.lot.id = :lotId And l.produit.metre.avenant.id = :avenantId")
	List<TacheEntity> getActiviteByLotIdAndAvenantId(Integer lotId, Integer avenantId);

	@Query("SELECT l FROM TacheEntity l WHERE l.produit.id = :produitId")
	List<TacheEntity> getActiviteByProduitId(Integer produitId);

	@Query("SELECT l FROM TacheEntity l WHERE l.produit.metre.avenant.id = :id")
	List<TacheEntity> getTachesByAvenantId(Integer id);

	@Query("SELECT l FROM TacheEntity l WHERE l.produit.metre.avenant.id <> :id AND l.produit.metre.avenant.project.id = (SELECT a.project.id FROM AvenantEntity a WHERE a.id = :id)")
	List<TacheEntity> getTachesInOtherAvenants(Integer id);

	@Query("SELECT l FROM TacheEntity l WHERE l.produit.metre.avenant.id = :id AND l.lot.designation = :lotDesignation AND l.titreActivite = :titreActivite")
	List<TacheEntity> getTachesByAvenantIdAndLotAndActivite(Integer id, String lotDesignation, String titreActivite);

	@Query("SELECT l FROM TacheEntity l WHERE l.produit.metre.avenant.id = :id And :charge IN (SELECT c.charge FROM DetailChargeEntity c WHERE c.tache.id = l.id)")
	List<TacheEntity> getTachesByAvenantIdAndCharge(Integer id, String charge);
}
