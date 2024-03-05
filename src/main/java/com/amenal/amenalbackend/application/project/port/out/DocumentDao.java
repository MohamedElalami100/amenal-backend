package com.amenal.amenalbackend.application.project.port.out;

import java.util.List;

import com.amenal.amenalbackend.application.project.domain.Document;

public interface DocumentDao {
	Document findDocumentById(Integer id);
	
	List<Document> findAllDocuments();
	
	Document saveDocument(Document document);
	
	Document updateDocument(Document document);
	
	void deleteDocument(Integer id);

	List<Document> getDocumentsByAvenantId(Integer id);
	
}
