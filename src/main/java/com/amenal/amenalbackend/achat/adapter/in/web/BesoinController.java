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

import com.amenal.amenalbackend.achat.application.domain.Besoin;
import com.amenal.amenalbackend.achat.application.port.in.BesoinUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/besoins")
@RequiredArgsConstructor
public class BesoinController {
	@Autowired
	private BesoinUseCase besoinUseCase;

	@GetMapping
	public ResponseEntity<List<Besoin>> getAllBesoins() {
		return ResponseEntity.ok(besoinUseCase.findAllBesoins());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Besoin> getBesoinById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(besoinUseCase.findBesoinById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Besoin> saveBesoin(@RequestBody Besoin besoin) {
		return ResponseEntity.status(HttpStatus.CREATED).body(besoinUseCase.saveBesoin(besoin));
	}

	@PutMapping
	public ResponseEntity<Besoin> updateBesoin(@RequestBody Besoin besoin) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(besoinUseCase.updateBesoin(besoin));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Besoin> deleteBesoin(@PathVariable("id") Integer id) {
		try {
			besoinUseCase.deleteBesoin(id);
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
