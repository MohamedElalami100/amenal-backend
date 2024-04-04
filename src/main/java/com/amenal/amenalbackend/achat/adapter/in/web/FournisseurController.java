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

import com.amenal.amenalbackend.achat.application.domain.Fournisseur;
import com.amenal.amenalbackend.achat.application.port.in.FournisseurUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {
	@Autowired
	private FournisseurUseCase fournisseurUseCase;

	@GetMapping
	public ResponseEntity<List<Fournisseur>> getAllFournisseurs() {
		return ResponseEntity.ok(fournisseurUseCase.findAllFournisseurs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(fournisseurUseCase.findFournisseurById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Fournisseur> saveFournisseur(@RequestBody Fournisseur fournisseur) {
		return ResponseEntity.status(HttpStatus.CREATED).body(fournisseurUseCase.saveFournisseur(fournisseur));
	}

	@PutMapping
	public ResponseEntity<Fournisseur> updateFournisseur(@RequestBody Fournisseur fournisseur) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(fournisseurUseCase.updateFournisseur(fournisseur));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Fournisseur> deleteFournisseur(@PathVariable("id") Integer id) {
		try {
			fournisseurUseCase.deleteFournisseur(id);
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
