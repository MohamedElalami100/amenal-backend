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

import com.amenal.amenalbackend.budget.application.domain.Tache;
import com.amenal.amenalbackend.budget.application.port.in.TacheUseCase;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/taches")
@RequiredArgsConstructor
public class TacheController {
	@Autowired
	private TacheUseCase tacheUseCase;

	@GetMapping
	public ResponseEntity<List<Tache>> getAllTaches() {
		return ResponseEntity.ok(tacheUseCase.findAllTaches());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tache> getTacheById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(tacheUseCase.findTacheById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Tache> saveTache(@RequestBody Tache tache) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(tacheUseCase.saveTache(tache));
		} catch (DuplicateElementException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} 
	}

	@PostMapping("/addAll")
	public ResponseEntity<List<Tache>> saveTaches(@RequestBody List<Tache> taches) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tacheUseCase.saveTaches(taches));
	}
	
	@PutMapping
	public ResponseEntity<Tache> updateTache(@RequestBody Tache tache) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(tacheUseCase.updateTache(tache));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DuplicateElementException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tache> deleteTache(@PathVariable("id") Integer id) {
		try {
			tacheUseCase.deleteTache(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

}
