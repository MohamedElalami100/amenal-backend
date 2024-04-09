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

import com.amenal.amenalbackend.achat.core.domain.CompteBanquaire;
import com.amenal.amenalbackend.achat.core.port.in.CompteBanquaireUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/compteBanquaires")
@RequiredArgsConstructor
public class CompteBanquaireController {
	@Autowired
	private CompteBanquaireUseCase compteBanquaireUseCase;

	@GetMapping
	public ResponseEntity<List<CompteBanquaire>> getAllCompteBanquaires() {
		return ResponseEntity.ok(compteBanquaireUseCase.findAllCompteBanquaires());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompteBanquaire> getCompteBanquaireById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(compteBanquaireUseCase.findCompteBanquaireById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<CompteBanquaire> saveCompteBanquaire(@RequestBody CompteBanquaire compteBanquaire) {
		return ResponseEntity.status(HttpStatus.CREATED).body(compteBanquaireUseCase.saveCompteBanquaire(compteBanquaire));
	}

	@PutMapping
	public ResponseEntity<CompteBanquaire> updateCompteBanquaire(@RequestBody CompteBanquaire compteBanquaire) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(compteBanquaireUseCase.updateCompteBanquaire(compteBanquaire));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CompteBanquaire> deleteCompteBanquaire(@PathVariable("id") Integer id) {
		try {
			compteBanquaireUseCase.deleteCompteBanquaire(id);
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
