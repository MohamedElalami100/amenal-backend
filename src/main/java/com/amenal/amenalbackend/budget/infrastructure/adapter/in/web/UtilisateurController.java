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

import com.amenal.amenalbackend.budget.core.domain.Utilisateur;
import com.amenal.amenalbackend.budget.core.port.in.UtilisateurUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {
	@Autowired
	private UtilisateurUseCase utilisateurUseCase;

	@GetMapping
	public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
		return ResponseEntity.ok(utilisateurUseCase.findAllUtilisateurs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(utilisateurUseCase.findUtilisateurById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Utilisateur> saveUtilisateur(@RequestBody Utilisateur utilisateur) {
		return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurUseCase.saveUtilisateur(utilisateur));
	}

	@PutMapping
	public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur utilisateur) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(utilisateurUseCase.updateUtilisateur(utilisateur));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable("id") Integer id) {
		try {
			utilisateurUseCase.deleteUtilisateur(id);
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
