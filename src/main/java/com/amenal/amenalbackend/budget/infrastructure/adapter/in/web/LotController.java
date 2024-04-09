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

import com.amenal.amenalbackend.budget.application.domain.Lot;
import com.amenal.amenalbackend.budget.application.port.in.LotUseCase;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lots")
@RequiredArgsConstructor
public class LotController {
	@Autowired
	private LotUseCase lotUseCase;

	@GetMapping
	public ResponseEntity<List<Lot>> getAllLots() {
		return ResponseEntity.ok(lotUseCase.findAllLots());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lot> getLotById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(lotUseCase.findLotById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<Lot>> getLotsByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(lotUseCase.getLotsByAvenantId(id));
	}
	
	@GetMapping("/project/{id}")
	public ResponseEntity<List<Lot>> getLotsByProjectId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(lotUseCase.getLotsByProjectId(id));
	}

	@PostMapping
	public ResponseEntity<Lot> saveLot(@RequestBody Lot lot) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(lotUseCase.saveLot(lot));	
		}
		catch (DuplicateElementException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
	}
	
	@PostMapping("/addAll")
	public ResponseEntity<List<Lot>> saveLots(@RequestBody List<Lot> lots) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lotUseCase.saveLots(lots));
	}

	@PutMapping
	public ResponseEntity<Lot> updateLot(@RequestBody Lot lot) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(lotUseCase.updateLot(lot));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DuplicateElementException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Lot> deleteLot(@PathVariable("id") Integer id) {
		try {
			lotUseCase.deleteLot(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

}
