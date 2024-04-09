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

import com.amenal.amenalbackend.budget.core.domain.Personnel;
import com.amenal.amenalbackend.budget.core.port.in.PersonnelUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/personnels")
@RequiredArgsConstructor
public class PersonnelController {
	@Autowired
	private PersonnelUseCase personnelUseCase;

	@GetMapping
	public ResponseEntity<List<Personnel>> getAllPersonnels() {
		return ResponseEntity.ok(personnelUseCase.findAllPersonnels());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Personnel> getPersonnelById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(personnelUseCase.findPersonnelById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<Personnel> savePersonnel(@RequestBody Personnel personnel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(personnelUseCase.savePersonnel(personnel));
	}

	@PutMapping
	public ResponseEntity<Personnel> updatePersonnel(@RequestBody Personnel personnel) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(personnelUseCase.updatePersonnel(personnel));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Personnel> deletePersonnel(@PathVariable("id") Integer id) {
		try {
			personnelUseCase.deletePersonnel(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

}
