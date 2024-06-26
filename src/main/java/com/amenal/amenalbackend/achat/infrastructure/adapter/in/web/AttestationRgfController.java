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

import com.amenal.amenalbackend.achat.core.domain.AttestationRgf;
import com.amenal.amenalbackend.achat.core.port.in.AttestationRgfUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attestationRgfs")
@RequiredArgsConstructor
public class AttestationRgfController {
	@Autowired
	private AttestationRgfUseCase attestationRgfUseCase;

	@GetMapping
	public ResponseEntity<List<AttestationRgf>> getAllAttestationRgfs() {
		return ResponseEntity.ok(attestationRgfUseCase.findAllAttestationRgfs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttestationRgf> getAttestationRgfById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(attestationRgfUseCase.findAttestationRgfById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<AttestationRgf> saveAttestationRgf(@RequestBody AttestationRgf attestationRgf) {
		return ResponseEntity.status(HttpStatus.CREATED).body(attestationRgfUseCase.saveAttestationRgf(attestationRgf));
	}

	@PutMapping
	public ResponseEntity<AttestationRgf> updateAttestationRgf(@RequestBody AttestationRgf attestationRgf) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(attestationRgfUseCase.updateAttestationRgf(attestationRgf));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AttestationRgf> deleteAttestationRgf(@PathVariable("id") Integer id) {
		try {
			attestationRgfUseCase.deleteAttestationRgf(id);
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
