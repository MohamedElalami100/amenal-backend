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

import com.amenal.amenalbackend.budget.core.domain.Banque;
import com.amenal.amenalbackend.budget.core.port.in.BanqueUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/banques")
@RequiredArgsConstructor
public class BanqueController {
	@Autowired
	private BanqueUseCase banqueUseCase;

	@GetMapping
	public ResponseEntity<List<Banque>> getAllBanques() {
		return ResponseEntity.ok(banqueUseCase.findAllBanques());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Banque> getBanqueById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(banqueUseCase.findBanqueById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Banque> saveBanque(@RequestBody Banque banque) {
		return ResponseEntity.status(HttpStatus.CREATED).body(banqueUseCase.saveBanque(banque));
	}

	@PutMapping
	public ResponseEntity<Banque> updateBanque(@RequestBody Banque banque) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(banqueUseCase.updateBanque(banque));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Banque> deleteBanque(@PathVariable("id") Integer id) {
		try {
			banqueUseCase.deleteBanque(id);
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
