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

import com.amenal.amenalbackend.budget.application.domain.Produit;
import com.amenal.amenalbackend.budget.application.port.in.ProduitUseCase;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produits")
@RequiredArgsConstructor
public class ProduitController {
	@Autowired
	private ProduitUseCase produitUseCase;

	@GetMapping
	public ResponseEntity<List<Produit>> getAllProduits() {
		return ResponseEntity.ok(produitUseCase.findAllProduits());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produit> getProduitById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(produitUseCase.findProduitById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<Produit>> getProduitsByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(produitUseCase.getProduitsByAvenantId(id));
	}

	@PostMapping
	public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(produitUseCase.saveProduit(produit));
		} catch (DuplicateElementException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
	}
	
	@PostMapping("/addAll")
	public ResponseEntity<List<Produit>> saveProduits(@RequestBody List<Produit> produits) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produitUseCase.saveProduits(produits));
	}

	@PutMapping
	public ResponseEntity<Produit> updateProduit(@RequestBody Produit produit) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(produitUseCase.updateProduit(produit));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DuplicateElementException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produit> deleteProduit(@PathVariable("id") Integer id) {
		try {
			produitUseCase.deleteProduit(id);
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
