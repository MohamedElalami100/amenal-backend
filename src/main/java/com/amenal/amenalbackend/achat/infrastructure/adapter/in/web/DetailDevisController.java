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

import com.amenal.amenalbackend.achat.core.domain.DetailDevis;
import com.amenal.amenalbackend.achat.core.port.in.DetailDevisUseCase;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailDevisDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailDeviss")
@RequiredArgsConstructor
public class DetailDevisController {
	@Autowired
	private DetailDevisUseCase detailDevisUseCase;

	@GetMapping
	public ResponseEntity<List<DetailDevisDto>> getAllDetailDeviss() {
		return ResponseEntity.ok(detailDevisUseCase.findAllDetailDeviss());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailDevisDto> getDetailDevisById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailDevisUseCase.findDetailDevisById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailDevis> saveDetailDevis(@RequestBody DetailDevis detailDevis) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailDevisUseCase.saveDetailDevis(detailDevis));
	}

	@PutMapping
	public ResponseEntity<DetailDevis> updateDetailDevis(@RequestBody DetailDevis detailDevis) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailDevisUseCase.updateDetailDevis(detailDevis));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailDevis> deleteDetailDevis(@PathVariable("id") Integer id) {
		try {
			detailDevisUseCase.deleteDetailDevis(id);
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
