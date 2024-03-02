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

import com.amenal.amenalbackend.application.project.domain.DetailCharge;
import com.amenal.amenalbackend.application.project.port.in.DetailChargeUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailCharges")
@RequiredArgsConstructor
public class DetailChargeController {
	@Autowired
	private DetailChargeUseCase detailChargeUseCase;

	@GetMapping
	public ResponseEntity<List<DetailCharge>> getAllDetailCharges() {
		return ResponseEntity.ok(detailChargeUseCase.findAllDetailCharges());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailCharge> getDetailChargeById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailChargeUseCase.findDetailChargeById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailCharge> saveDetailCharge(@RequestBody DetailCharge detailCharge) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailChargeUseCase.saveDetailCharge(detailCharge));
	}

	@PutMapping
	public ResponseEntity<DetailCharge> updateDetailCharge(@RequestBody DetailCharge detailCharge) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailChargeUseCase.updateDetailCharge(detailCharge));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailCharge> deleteDetailCharge(@PathVariable("id") Integer id) {
		try {
			detailChargeUseCase.deleteDetailCharge(id);
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
