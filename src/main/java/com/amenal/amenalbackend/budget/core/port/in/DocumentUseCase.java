package com.amenal.amenalbackend.budget.application.port.in;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amenal.amenalbackend.budget.application.domain.Avenant;
import com.amenal.amenalbackend.budget.application.domain.Document;
import com.amenal.amenalbackend.budget.application.port.out.AvenantDao;
import com.amenal.amenalbackend.budget.application.port.out.CloudStorageDao;
import com.amenal.amenalbackend.budget.application.port.out.DocumentDao;

import org.springframework.util.StringUtils;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocumentUseCase {
	private CloudStorageDao cloudStorageDao;
	
	private DocumentDao documentDao;
	
	private AvenantDao avenantDao;

	public DocumentUseCase(CloudStorageDao cloudStorageDao, DocumentDao documentDao, AvenantDao avenantDao) {
		super();
		this.cloudStorageDao = cloudStorageDao;
		this.documentDao = documentDao;		
		this.avenantDao = avenantDao;
	}
	
	public Document findDocumentById(Integer id) {
	    return documentDao.findDocumentById(id);
	}

	public List<Document> findAllDocuments() {
		return documentDao.findAllDocuments();
	}
	
	public Document updateDocument(Document document) {
		return documentDao.updateDocument(document);
	}
	
	public void deleteDocument(Integer id) {
		documentDao.deleteDocument(id);
	}
	
	public Document uploadFileAndSaveDocument(MultipartFile file, Integer AvenantId) throws GeneralSecurityException, IOException {
		//get avenant:
		Avenant avenant = avenantDao.findAvenantById(AvenantId);

	    Path tempDir = Files.createTempDirectory("temp-dir");

	    //get the original filename
	    String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

	    //create a temporary file with the original filename inside the temporary directory
	    Path tempFilePath = tempDir.resolve(originalFilename);

	    //transfer the content of the multipart file to the temporary file
	    try (OutputStream outputStream = Files.newOutputStream(tempFilePath)) {
	        outputStream.write(file.getBytes());
	    }

	    //upload file
	    Document document = cloudStorageDao.uploadFileToCloud(tempFilePath.toFile());

		document.setAvenant(avenant);
		
		//save document metadata to db:
		Document savedDocument = documentDao.saveDocument(document);
		
		return savedDocument;
		
	}

	public List<Document> getDocumentsByAvenantId(Integer id) {
	    return documentDao.getDocumentsByAvenantId(id);
	}
	
}
