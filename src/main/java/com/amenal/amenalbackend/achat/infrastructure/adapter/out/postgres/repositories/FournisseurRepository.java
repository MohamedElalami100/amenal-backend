package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.FournisseurEntity;

@Repository
public interface FournisseurRepository extends JpaRepository<FournisseurEntity, Integer>{

}
