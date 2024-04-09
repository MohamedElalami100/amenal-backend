package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.DossierEntity;

@Repository
public interface DossierRepository extends JpaRepository<DossierEntity, Integer>{

}
