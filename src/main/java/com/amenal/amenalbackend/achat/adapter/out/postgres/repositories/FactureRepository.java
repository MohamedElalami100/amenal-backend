package com.amenal.amenalbackend.achat.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.FactureEntity;

@Repository
public interface FactureRepository extends JpaRepository<FactureEntity, Integer>{

}
