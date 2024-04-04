package com.amenal.amenalbackend.achat.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.achat.adapter.out.postgres.entities.ChargeStandardEntity;

@Repository
public interface ChargeStandardRepository extends JpaRepository<ChargeStandardEntity, Integer>{

}