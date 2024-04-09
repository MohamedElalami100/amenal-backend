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

import com.amenal.amenalbackend.budget.core.domain.Avenant;
import com.amenal.amenalbackend.budget.core.port.in.AvenantUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/avenants")
@RequiredArgsConstructor
public class AvenantController {
	@Autowired
	private AvenantUseCase avenantUseCase;

	@GetMapping
	public ResponseEntity<List<Avenant>> getAllAvenants() {
		return ResponseEntity.ok(avenantUseCase.findAllAvenants());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Avenant> getAvenantById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(avenantUseCase.findAvenantById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/project/{id}")
	public ResponseEntity<List<Avenant>> getAvenantsByProjectId(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(avenantUseCase.getAvenantsByProjectId(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Avenant> saveAvenant(@RequestBody Avenant avenant) {
		return ResponseEntity.status(HttpStatus.CREATED).body(avenantUseCase.saveAvenant(avenant));
	}

	@PutMapping
	public ResponseEntity<Avenant> updateAvenant(@RequestBody Avenant avenant) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(avenantUseCase.updateAvenant(avenant));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Avenant> deleteAvenant(@PathVariable("id") Integer id) {
		try {
			avenantUseCase.deleteAvenant(id);
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
