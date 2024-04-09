package com.amenal.amenalbackend.budget.adapter.out.postgres.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amenal.amenalbackend.budget.adapter.out.postgres.entities.DocumentEntity;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer>{

	@Query("SELECT l FROM DocumentEntity l WHERE l.avenant.id = :id")
	List<DocumentEntity> getDocumentsByAvenantId(Integer id);

	@Query("SELECT l FROM DocumentEntity l WHERE l.url = :url")
	Optional<DocumentEntity> findByUrl(String url);

}
