package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.CommandeEntity;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Integer>{
    @Query("SELECT l FROM CommandeEntity l WHERE l.besoin.tache.produit.metre.avenant.project.id = :id")
    List<CommandeEntity> getCommandesByProjectId(Integer id);
}
