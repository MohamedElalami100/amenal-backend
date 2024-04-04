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

import com.amenal.amenalbackend.achat.application.domain.Commande;
import com.amenal.amenalbackend.achat.application.port.in.CommandeUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
public class CommandeController {
	@Autowired
	private CommandeUseCase commandeUseCase;

	@GetMapping
	public ResponseEntity<List<Commande>> getAllCommandes() {
		return ResponseEntity.ok(commandeUseCase.findAllCommandes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Commande> getCommandeById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(commandeUseCase.findCommandeById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Commande> saveCommande(@RequestBody Commande commande) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commandeUseCase.saveCommande(commande));
	}

	@PutMapping
	public ResponseEntity<Commande> updateCommande(@RequestBody Commande commande) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(commandeUseCase.updateCommande(commande));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Commande> deleteCommande(@PathVariable("id") Integer id) {
		try {
			commandeUseCase.deleteCommande(id);
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
