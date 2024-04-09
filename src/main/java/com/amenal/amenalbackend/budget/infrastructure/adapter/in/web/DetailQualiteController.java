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

import com.amenal.amenalbackend.budget.core.domain.DetailQualite;
import com.amenal.amenalbackend.budget.core.port.in.DetailQualiteUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailQualites")
@RequiredArgsConstructor
public class DetailQualiteController {
	@Autowired
	private DetailQualiteUseCase detailQualiteUseCase;

	@GetMapping
	public ResponseEntity<List<DetailQualite>> getAllDetailQualites() {
		return ResponseEntity.ok(detailQualiteUseCase.findAllDetailQualites());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailQualite> getDetailQualiteById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailQualiteUseCase.findDetailQualiteById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailQualite> saveDetailQualite(@RequestBody DetailQualite detailQualite) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailQualiteUseCase.saveDetailQualite(detailQualite));
	}

	@PutMapping
	public ResponseEntity<DetailQualite> updateDetailQualite(@RequestBody DetailQualite detailQualite) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailQualiteUseCase.updateDetailQualite(detailQualite));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailQualite> deleteDetailQualite(@PathVariable("id") Integer id) {
		try {
			detailQualiteUseCase.deleteDetailQualite(id);
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
