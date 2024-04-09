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

import com.amenal.amenalbackend.achat.core.domain.DetailDemandeDevis;
import com.amenal.amenalbackend.achat.core.port.in.DetailDemandeDevisUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailDemandeDeviss")
@RequiredArgsConstructor
public class DetailDemandeDevisController {
	@Autowired
	private DetailDemandeDevisUseCase detailDemandeDevisUseCase;

	@GetMapping
	public ResponseEntity<List<DetailDemandeDevis>> getAllDetailDemandeDeviss() {
		return ResponseEntity.ok(detailDemandeDevisUseCase.findAllDetailDemandeDeviss());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailDemandeDevis> getDetailDemandeDevisById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailDemandeDevisUseCase.findDetailDemandeDevisById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailDemandeDevis> saveDetailDemandeDevis(@RequestBody DetailDemandeDevis detailDemandeDevis) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailDemandeDevisUseCase.saveDetailDemandeDevis(detailDemandeDevis));
	}

	@PutMapping
	public ResponseEntity<DetailDemandeDevis> updateDetailDemandeDevis(@RequestBody DetailDemandeDevis detailDemandeDevis) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailDemandeDevisUseCase.updateDetailDemandeDevis(detailDemandeDevis));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailDemandeDevis> deleteDetailDemandeDevis(@PathVariable("id") Integer id) {
		try {
			detailDemandeDevisUseCase.deleteDetailDemandeDevis(id);
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
