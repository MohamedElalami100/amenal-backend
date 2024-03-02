package com.amenal.amenalbackend.adapter.project.in.web;

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

import com.amenal.amenalbackend.application.project.domain.Client;
import com.amenal.amenalbackend.application.project.port.in.ClientUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
	@Autowired
	private ClientUseCase clientUseCase;

	@GetMapping
	public ResponseEntity<List<Client>> getAllClients() {
		return ResponseEntity.ok(clientUseCase.findAllClients());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(clientUseCase.findClientById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientUseCase.saveClient(client));
	}

	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clientUseCase.updateClient(client));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Client> deleteClient(@PathVariable("id") Integer id) {
		try {
			clientUseCase.deleteClient(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

}
