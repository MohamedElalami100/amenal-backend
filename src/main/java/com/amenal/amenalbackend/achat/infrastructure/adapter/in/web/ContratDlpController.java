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

import com.amenal.amenalbackend.achat.application.domain.ContratDlp;
import com.amenal.amenalbackend.achat.application.port.in.ContratDlpUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contratDlps")
@RequiredArgsConstructor
public class ContratDlpController {
	@Autowired
	private ContratDlpUseCase contratDlpUseCase;

	@GetMapping
	public ResponseEntity<List<ContratDlp>> getAllContratDlps() {
		return ResponseEntity.ok(contratDlpUseCase.findAllContratDlps());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContratDlp> getContratDlpById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(contratDlpUseCase.findContratDlpById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ContratDlp> saveContratDlp(@RequestBody ContratDlp contratDlp) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contratDlpUseCase.saveContratDlp(contratDlp));
	}

	@PutMapping
	public ResponseEntity<ContratDlp> updateContratDlp(@RequestBody ContratDlp contratDlp) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contratDlpUseCase.updateContratDlp(contratDlp));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContratDlp> deleteContratDlp(@PathVariable("id") Integer id) {
		try {
			contratDlpUseCase.deleteContratDlp(id);
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
