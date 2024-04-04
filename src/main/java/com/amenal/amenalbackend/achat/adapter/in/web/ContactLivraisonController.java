package com.amenal.amenalbackend.achat.adapter.in.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.achat.application.domain.ContactLivraison;
import com.amenal.amenalbackend.achat.application.port.in.ContactLivraisonUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contactLivraisons")
@RequiredArgsConstructor
public class ContactLivraisonController {
	@Autowired
	private ContactLivraisonUseCase contactLivraisonUseCase;

	@GetMapping
	public ResponseEntity<List<ContactLivraison>> getAllContactLivraisons() {
		return ResponseEntity.ok(contactLivraisonUseCase.findAllContactLivraisons());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactLivraison> getContactLivraisonById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(contactLivraisonUseCase.findContactLivraisonById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ContactLivraison> saveContactLivraison(@RequestBody ContactLivraison contactLivraison) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactLivraisonUseCase.saveContactLivraison(contactLivraison));
	}

	@PutMapping
	public ResponseEntity<ContactLivraison> updateContactLivraison(@RequestBody ContactLivraison contactLivraison) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contactLivraisonUseCase.updateContactLivraison(contactLivraison));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContactLivraison> deleteContactLivraison(@PathVariable("id") Integer id) {
		try {
			contactLivraisonUseCase.deleteContactLivraison(id);
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
