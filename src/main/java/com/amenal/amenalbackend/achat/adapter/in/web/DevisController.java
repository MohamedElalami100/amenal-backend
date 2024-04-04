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

import com.amenal.amenalbackend.achat.application.domain.Devis;
import com.amenal.amenalbackend.achat.application.port.in.DevisUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deviss")
@RequiredArgsConstructor
public class DevisController {
	@Autowired
	private DevisUseCase devisUseCase;

	@GetMapping
	public ResponseEntity<List<Devis>> getAllDeviss() {
		return ResponseEntity.ok(devisUseCase.findAllDeviss());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Devis> getDevisById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(devisUseCase.findDevisById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Devis> saveDevis(@RequestBody Devis devis) {
		return ResponseEntity.status(HttpStatus.CREATED).body(devisUseCase.saveDevis(devis));
	}

	@PutMapping
	public ResponseEntity<Devis> updateDevis(@RequestBody Devis devis) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(devisUseCase.updateDevis(devis));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Devis> deleteDevis(@PathVariable("id") Integer id) {
		try {
			devisUseCase.deleteDevis(id);
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
