package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DetailReceptionEntity;

import java.util.List;

@Repository
public interface DetailReceptionRepository extends JpaRepository<DetailReceptionEntity, Integer>{
    List<DetailReceptionEntity> findByDetailCommandeId(Integer commandeId);
}
