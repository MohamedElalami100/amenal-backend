package com.amenal.amenalbackend.budget.adapter.in.web;

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

import com.amenal.amenalbackend.budget.application.domain.DetailProduit;
import com.amenal.amenalbackend.budget.application.port.in.DetailProduitUseCase;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailProduits")
@RequiredArgsConstructor
public class DetailProduitController {
	@Autowired
	private DetailProduitUseCase detailProduitUseCase;

	@GetMapping
	public ResponseEntity<List<DetailProduit>> getAllDetailProduits() {
		return ResponseEntity.ok(detailProduitUseCase.findAllDetailProduits());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailProduit> getDetailProduitById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailProduitUseCase.findDetailProduitById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailProduit> saveDetailProduit(@RequestBody DetailProduit detailProduit) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailProduitUseCase.saveDetailProduit(detailProduit));
		} catch (DuplicateElementException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping
	public ResponseEntity<DetailProduit> updateDetailProduit(@RequestBody DetailProduit detailProduit) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailProduitUseCase.updateDetailProduit(detailProduit));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}  catch (DuplicateElementException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailProduit> deleteDetailProduit(@PathVariable("id") Integer id) {
		try {
			detailProduitUseCase.deleteDetailProduit(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

}
