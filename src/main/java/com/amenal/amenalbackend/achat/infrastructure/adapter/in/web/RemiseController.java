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

import com.amenal.amenalbackend.achat.core.domain.Remise;
import com.amenal.amenalbackend.achat.core.port.in.RemiseUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/remises")
@RequiredArgsConstructor
public class RemiseController {
	@Autowired
	private RemiseUseCase remiseUseCase;

	@GetMapping
	public ResponseEntity<List<Remise>> getAllRemises() {
		return ResponseEntity.ok(remiseUseCase.findAllRemises());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Remise> getRemiseById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(remiseUseCase.findRemiseById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Remise> saveRemise(@RequestBody Remise remise) {
		return ResponseEntity.status(HttpStatus.CREATED).body(remiseUseCase.saveRemise(remise));
	}

	@PutMapping
	public ResponseEntity<Remise> updateRemise(@RequestBody Remise remise) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(remiseUseCase.updateRemise(remise));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Remise> deleteRemise(@PathVariable("id") Integer id) {
		try {
			remiseUseCase.deleteRemise(id);
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
