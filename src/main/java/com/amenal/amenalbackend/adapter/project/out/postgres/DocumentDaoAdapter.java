package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DocumentEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DocumentRepository;
import com.amenal.amenalbackend.application.project.domain.Document;
import com.amenal.amenalbackend.application.project.port.out.DocumentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DocumentDaoAdapter implements DocumentDao {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Document findDocumentById(Integer id) {
		DocumentEntity documentEntity = documentRepository.findById(id).get();
		Document document = modelMapper.map(documentEntity, Document.class);
		return document;
	}

	@Override
	public List<Document> findAllDocuments() {
		List<DocumentEntity> documentEntities = documentRepository.findAll();
		return documentEntities.stream().map(documentEntity -> modelMapper.map(documentEntity, Document.class))
				.collect(Collectors.toList());
	}

	@Override
	public Document saveDocument(Document document) {
		DocumentEntity documentEntity = modelMapper.map(document, DocumentEntity.class);
		DocumentEntity savedEntity = documentRepository.save(documentEntity);
		return modelMapper.map(savedEntity, Document.class);
	}
	
	@Override
	public Document updateDocument(Document document) {
		DocumentEntity existingEntity = documentRepository.findByUrl(document.getUrl()).orElseThrow();

		// Use ModelMapper to map non-null properties from Document to existingEntity
		modelMapper.map(document, existingEntity);

		DocumentEntity updatedEntity = documentRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Document.class);
	}

	@Override
	public void deleteDocument(Integer id) {
		// Check if Document with the given ID exists
		DocumentEntity documentEntity = documentRepository.findById(id).orElseThrow();

		// Delete the entity
		documentRepository.delete(documentEntity);
	}

	@Override
	public List<Document> getDocumentsByAvenantId(Integer id) {
		List<DocumentEntity> documentEntities = documentRepository.getDocumentsByAvenantId(id);
		return documentEntities.stream().map(documentEntity -> modelMapper.map(documentEntity, Document.class))
				.collect(Collectors.toList());
	}

}
