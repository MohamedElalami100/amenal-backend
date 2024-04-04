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

import com.amenal.amenalbackend.achat.application.domain.ContratPlafond;
import com.amenal.amenalbackend.achat.application.port.in.ContratPlafondUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contratPlafonds")
@RequiredArgsConstructor
public class ContratPlafondController {
	@Autowired
	private ContratPlafondUseCase contratPlafondUseCase;

	@GetMapping
	public ResponseEntity<List<ContratPlafond>> getAllContratPlafonds() {
		return ResponseEntity.ok(contratPlafondUseCase.findAllContratPlafonds());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContratPlafond> getContratPlafondById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(contratPlafondUseCase.findContratPlafondById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ContratPlafond> saveContratPlafond(@RequestBody ContratPlafond contratPlafond) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contratPlafondUseCase.saveContratPlafond(contratPlafond));
	}

	@PutMapping
	public ResponseEntity<ContratPlafond> updateContratPlafond(@RequestBody ContratPlafond contratPlafond) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contratPlafondUseCase.updateContratPlafond(contratPlafond));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContratPlafond> deleteContratPlafond(@PathVariable("id") Integer id) {
		try {
			contratPlafondUseCase.deleteContratPlafond(id);
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
