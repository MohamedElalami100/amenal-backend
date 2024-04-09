package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProduitStandardEntity;

@Repository
public interface ProduitStandardRepository extends JpaRepository<ProduitStandardEntity, Integer>{

}
