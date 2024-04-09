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

import com.amenal.amenalbackend.achat.application.domain.Facture;
import com.amenal.amenalbackend.achat.application.dto.FactureDto;
import com.amenal.amenalbackend.achat.application.port.in.FactureUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
public class FactureController {
	@Autowired
	private FactureUseCase factureUseCase;

	@GetMapping
	public ResponseEntity<List<FactureDto>> getAllFactures() {
		return ResponseEntity.ok(factureUseCase.findAllFactures());
	}

	@GetMapping("/{id}")
	public ResponseEntity<FactureDto> getFactureById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(factureUseCase.findFactureById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Facture> saveFacture(@RequestBody Facture facture) {
		return ResponseEntity.status(HttpStatus.CREATED).body(factureUseCase.saveFacture(facture));
	}

	@PutMapping
	public ResponseEntity<Facture> updateFacture(@RequestBody Facture facture) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(factureUseCase.updateFacture(facture));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Facture> deleteFacture(@PathVariable("id") Integer id) {
		try {
			factureUseCase.deleteFacture(id);
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
