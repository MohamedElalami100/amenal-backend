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

import com.amenal.amenalbackend.achat.core.domain.ChargeStandard;
import com.amenal.amenalbackend.achat.core.port.in.ChargeStandardUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chargeStandards")
@RequiredArgsConstructor
public class ChargeStandardController {
	@Autowired
	private ChargeStandardUseCase chargeStandardUseCase;

	@GetMapping
	public ResponseEntity<List<ChargeStandard>> getAllChargeStandards() {
		return ResponseEntity.ok(chargeStandardUseCase.findAllChargeStandards());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChargeStandard> getChargeStandardById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(chargeStandardUseCase.findChargeStandardById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ChargeStandard> saveChargeStandard(@RequestBody ChargeStandard chargeStandard) {
		return ResponseEntity.status(HttpStatus.CREATED).body(chargeStandardUseCase.saveChargeStandard(chargeStandard));
	}

	@PutMapping
	public ResponseEntity<ChargeStandard> updateChargeStandard(@RequestBody ChargeStandard chargeStandard) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(chargeStandardUseCase.updateChargeStandard(chargeStandard));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ChargeStandard> deleteChargeStandard(@PathVariable("id") Integer id) {
		try {
			chargeStandardUseCase.deleteChargeStandard(id);
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
