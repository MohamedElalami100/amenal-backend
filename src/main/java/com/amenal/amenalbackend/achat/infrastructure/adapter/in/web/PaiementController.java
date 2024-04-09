package com.amenal.amenalbackend.achat.infrastructure.adapter.in.web;

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

import com.amenal.amenalbackend.achat.core.domain.Paiement;
import com.amenal.amenalbackend.achat.core.port.in.PaiementUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {
	@Autowired
	private PaiementUseCase paiementUseCase;

	@GetMapping
	public ResponseEntity<List<Paiement>> getAllPaiements() {
		return ResponseEntity.ok(paiementUseCase.findAllPaiements());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paiement> getPaiementById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(paiementUseCase.findPaiementById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Paiement> savePaiement(@RequestBody Paiement paiement) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paiementUseCase.savePaiement(paiement));
	}

	@PutMapping
	public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(paiementUseCase.updatePaiement(paiement));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Paiement> deletePaiement(@PathVariable("id") Integer id) {
		try {
			paiementUseCase.deletePaiement(id);
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
