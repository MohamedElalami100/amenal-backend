package com.amenal.amenalbackend.budget.adapter.out.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.PersonnelEntity;

@Repository
public interface PersonnelRepository extends JpaRepository<PersonnelEntity, Integer>{

}
