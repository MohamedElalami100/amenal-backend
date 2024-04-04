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

import com.amenal.amenalbackend.achat.application.domain.ContactFournisseur;
import com.amenal.amenalbackend.achat.application.port.in.ContactFournisseurUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contactFournisseurs")
@RequiredArgsConstructor
public class ContactFournisseurController {
	@Autowired
	private ContactFournisseurUseCase contactFournisseurUseCase;

	@GetMapping
	public ResponseEntity<List<ContactFournisseur>> getAllContactFournisseurs() {
		return ResponseEntity.ok(contactFournisseurUseCase.findAllContactFournisseurs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactFournisseur> getContactFournisseurById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(contactFournisseurUseCase.findContactFournisseurById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ContactFournisseur> saveContactFournisseur(@RequestBody ContactFournisseur contactFournisseur) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactFournisseurUseCase.saveContactFournisseur(contactFournisseur));
	}

	@PutMapping
	public ResponseEntity<ContactFournisseur> updateContactFournisseur(@RequestBody ContactFournisseur contactFournisseur) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contactFournisseurUseCase.updateContactFournisseur(contactFournisseur));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContactFournisseur> deleteContactFournisseur(@PathVariable("id") Integer id) {
		try {
			contactFournisseurUseCase.deleteContactFournisseur(id);
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
