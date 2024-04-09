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

import com.amenal.amenalbackend.achat.core.domain.DemandeDevis;
import com.amenal.amenalbackend.achat.core.port.in.DemandeDevisUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/demandeDeviss")
@RequiredArgsConstructor
public class DemandeDevisController {
	@Autowired
	private DemandeDevisUseCase demandeDevisUseCase;

	@GetMapping
	public ResponseEntity<List<DemandeDevis>> getAllDemandeDeviss() {
		return ResponseEntity.ok(demandeDevisUseCase.findAllDemandeDeviss());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DemandeDevis> getDemandeDevisById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(demandeDevisUseCase.findDemandeDevisById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DemandeDevis> saveDemandeDevis(@RequestBody DemandeDevis demandeDevis) {
		return ResponseEntity.status(HttpStatus.CREATED).body(demandeDevisUseCase.saveDemandeDevis(demandeDevis));
	}

	@PutMapping
	public ResponseEntity<DemandeDevis> updateDemandeDevis(@RequestBody DemandeDevis demandeDevis) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(demandeDevisUseCase.updateDemandeDevis(demandeDevis));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DemandeDevis> deleteDemandeDevis(@PathVariable("id") Integer id) {
		try {
			demandeDevisUseCase.deleteDemandeDevis(id);
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
