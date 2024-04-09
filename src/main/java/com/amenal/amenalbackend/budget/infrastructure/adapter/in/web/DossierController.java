package com.amenal.amenalbackend.budget.infrastructure.adapter.in.web;

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

import com.amenal.amenalbackend.budget.core.domain.Dossier;
import com.amenal.amenalbackend.budget.core.port.in.DossierUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dossiers")
@RequiredArgsConstructor
public class DossierController {
	@Autowired
	private DossierUseCase dossierUseCase;

	@GetMapping
	public ResponseEntity<List<Dossier>> getAllDossiers() {
		return ResponseEntity.ok(dossierUseCase.findAllDossiers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Dossier> getDossierById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(dossierUseCase.findDossierById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Dossier> saveDossier(@RequestBody Dossier dossier) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dossierUseCase.saveDossier(dossier));
	}

	@PutMapping
	public ResponseEntity<Dossier> updateDossier(@RequestBody Dossier dossier) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(dossierUseCase.updateDossier(dossier));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Dossier> deleteDossier(@PathVariable("id") Integer id) {
		try {
			dossierUseCase.deleteDossier(id);
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
