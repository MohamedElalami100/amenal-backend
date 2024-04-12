package com.amenal.amenalbackend.budget.core.port.out;

import java.util.List;

import com.amenal.amenalbackend.budget.core.domain.Document;

public interface DocumentDao {
	Document findDocumentByUrl(String url);
	
	List<Document> findAllDocuments();
	
	Document saveDocument(Document document);
	
	Document updateDocument(Document document);
	
	void deleteDocument(String url);

	List<Document> getDocumentsByAvenantId(Integer id);
	
}
