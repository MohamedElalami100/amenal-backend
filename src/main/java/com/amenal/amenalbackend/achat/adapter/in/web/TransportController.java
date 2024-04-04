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

import com.amenal.amenalbackend.achat.application.domain.Transport;
import com.amenal.amenalbackend.achat.application.port.in.TransportUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transports")
@RequiredArgsConstructor
public class TransportController {
	@Autowired
	private TransportUseCase transportUseCase;

	@GetMapping
	public ResponseEntity<List<Transport>> getAllTransports() {
		return ResponseEntity.ok(transportUseCase.findAllTransports());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transport> getTransportById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(transportUseCase.findTransportById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Transport> saveTransport(@RequestBody Transport transport) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transportUseCase.saveTransport(transport));
	}

	@PutMapping
	public ResponseEntity<Transport> updateTransport(@RequestBody Transport transport) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(transportUseCase.updateTransport(transport));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Transport> deleteTransport(@PathVariable("id") Integer id) {
		try {
			transportUseCase.deleteTransport(id);
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
