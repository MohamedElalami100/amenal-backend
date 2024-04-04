package com.amenal.amenalbackend.budget.adapter.in.web;

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

import com.amenal.amenalbackend.budget.application.domain.ProduitStandard;
import com.amenal.amenalbackend.budget.application.port.in.ProduitStandardUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produitStandards")
@RequiredArgsConstructor
public class ProduitStandardController {
	@Autowired
	private ProduitStandardUseCase produitStandardUseCase;

	@GetMapping
	public ResponseEntity<List<ProduitStandard>> getAllProduitStandards() {
		return ResponseEntity.ok(produitStandardUseCase.findAllProduitStandards());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProduitStandard> getProduitStandardById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(produitStandardUseCase.findProduitStandardById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ProduitStandard> saveProduitStandard(@RequestBody ProduitStandard produitStandard) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produitStandardUseCase.saveProduitStandard(produitStandard));
	}

	@PutMapping
	public ResponseEntity<ProduitStandard> updateProduitStandard(@RequestBody ProduitStandard produitStandard) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(produitStandardUseCase.updateProduitStandard(produitStandard));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProduitStandard> deleteProduitStandard(@PathVariable("id") Integer id) {
		try {
			produitStandardUseCase.deleteProduitStandard(id);
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
