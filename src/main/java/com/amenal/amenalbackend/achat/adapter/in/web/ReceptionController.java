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

import com.amenal.amenalbackend.achat.application.domain.Reception;
import com.amenal.amenalbackend.achat.application.port.in.ReceptionUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/receptions")
@RequiredArgsConstructor
public class ReceptionController {
	@Autowired
	private ReceptionUseCase receptionUseCase;

	@GetMapping
	public ResponseEntity<List<Reception>> getAllReceptions() {
		return ResponseEntity.ok(receptionUseCase.findAllReceptions());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reception> getReceptionById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(receptionUseCase.findReceptionById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Reception> saveReception(@RequestBody Reception reception) {
		return ResponseEntity.status(HttpStatus.CREATED).body(receptionUseCase.saveReception(reception));
	}

	@PutMapping
	public ResponseEntity<Reception> updateReception(@RequestBody Reception reception) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(receptionUseCase.updateReception(reception));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Reception> deleteReception(@PathVariable("id") Integer id) {
		try {
			receptionUseCase.deleteReception(id);
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
