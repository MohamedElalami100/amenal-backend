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

import com.amenal.amenalbackend.achat.application.domain.DetailBesoin;
import com.amenal.amenalbackend.achat.application.port.in.DetailBesoinUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailBesoins")
@RequiredArgsConstructor
public class DetailBesoinController {
	@Autowired
	private DetailBesoinUseCase detailBesoinUseCase;

	@GetMapping
	public ResponseEntity<List<DetailBesoin>> getAllDetailBesoins() {
		return ResponseEntity.ok(detailBesoinUseCase.findAllDetailBesoins());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailBesoin> getDetailBesoinById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailBesoinUseCase.findDetailBesoinById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailBesoin> saveDetailBesoin(@RequestBody DetailBesoin detailBesoin) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailBesoinUseCase.saveDetailBesoin(detailBesoin));
	}

	@PutMapping
	public ResponseEntity<DetailBesoin> updateDetailBesoin(@RequestBody DetailBesoin detailBesoin) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailBesoinUseCase.updateDetailBesoin(detailBesoin));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailBesoin> deleteDetailBesoin(@PathVariable("id") Integer id) {
		try {
			detailBesoinUseCase.deleteDetailBesoin(id);
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
