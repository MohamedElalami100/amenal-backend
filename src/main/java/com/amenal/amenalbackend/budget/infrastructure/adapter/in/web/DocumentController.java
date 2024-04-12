package com.amenal.amenalbackend.budget.infrastructure.adapter.in.web;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amenal.amenalbackend.budget.core.domain.Document;
import com.amenal.amenalbackend.budget.core.port.in.DocumentUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
	@Autowired
	private DocumentUseCase documentUseCase;

	@GetMapping
	public ResponseEntity<List<Document>> getAllDocuments() {
		return ResponseEntity.ok(documentUseCase.findAllDocuments());
	}

	@GetMapping("/{url}")
	public ResponseEntity<Document> getDocumentById(@PathVariable("url") String url) {
		try {
			return ResponseEntity.ok(documentUseCase.findDocumentByUrl(url));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<Document>> getDocumentsByAvenantId(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(documentUseCase.getDocumentsByAvenantId(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/avenant/{id}")
	public ResponseEntity<Document> saveDocument(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) {
	    try {
	        if (file.isEmpty()) {
	            // Handle empty file
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }

	        // Proceed with file upload and document saving
	        Document savedDocument = documentUseCase.uploadFileAndSaveDocument(file, id);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocument);
	    } catch (GeneralSecurityException | IOException e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        // Return an appropriate error response to the client
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    } catch (NoSuchElementException e) {
			// return a response with status 404 if an avenant with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<Document> updateDocument(@RequestBody Document document) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(documentUseCase.updateDocument(document));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{url}")
	public ResponseEntity<Document> deleteDocument(@PathVariable("url") String url) {
		try {
			documentUseCase.deleteDocument(url);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

}
