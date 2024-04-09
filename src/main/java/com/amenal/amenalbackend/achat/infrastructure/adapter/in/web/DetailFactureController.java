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

import com.amenal.amenalbackend.achat.core.domain.DetailFacture;
import com.amenal.amenalbackend.achat.core.port.in.DetailFactureUseCase;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailFactureDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailFactures")
@RequiredArgsConstructor
public class DetailFactureController {
	@Autowired
	private DetailFactureUseCase detailFactureUseCase;

	@GetMapping
	public ResponseEntity<List<DetailFactureDto>> getAllDetailFactures() {
		return ResponseEntity.ok(detailFactureUseCase.findAllDetailFactures());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailFactureDto> getDetailFactureById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailFactureUseCase.findDetailFactureById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailFacture> saveDetailFacture(@RequestBody DetailFacture detailFacture) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailFactureUseCase.saveDetailFacture(detailFacture));
	}

	@PutMapping
	public ResponseEntity<DetailFacture> updateDetailFacture(@RequestBody DetailFacture detailFacture) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailFactureUseCase.updateDetailFacture(detailFacture));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailFacture> deleteDetailFacture(@PathVariable("id") Integer id) {
		try {
			detailFactureUseCase.deleteDetailFacture(id);
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
