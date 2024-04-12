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

import com.amenal.amenalbackend.budget.core.domain.GrpQualite;
import com.amenal.amenalbackend.budget.core.port.in.GrpQualiteUseCase;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/grpQualites")
@RequiredArgsConstructor
public class GrpQualiteController {
	@Autowired
	private GrpQualiteUseCase grpQualiteUseCase;

	@GetMapping
	public ResponseEntity<List<GrpQualite>> getAllGrpQualites() {
		return ResponseEntity.ok(grpQualiteUseCase.findAllGrpQualites());
	}

	@GetMapping("/{id}")
	public ResponseEntity<GrpQualite> getGrpQualiteById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(grpQualiteUseCase.findGrpQualiteById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<GrpQualite> saveGrpQualite(@RequestBody GrpQualite grpQualite) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(grpQualiteUseCase.saveGrpQualite(grpQualite));
		} catch (DuplicateElementException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping
	public ResponseEntity<GrpQualite> updateGrpQualite(@RequestBody GrpQualite grpQualite) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(grpQualiteUseCase.updateGrpQualite(grpQualite));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}  catch (DuplicateElementException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GrpQualite> deleteGrpQualite(@PathVariable("id") Integer id) {
		try {
			grpQualiteUseCase.deleteGrpQualite(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

}
