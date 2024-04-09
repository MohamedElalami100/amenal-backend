package com.amenal.amenalbackend.budget.infrastructure.adapter.in.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.amenal.amenalbackend.budget.core.domain.MetreAv;
import com.amenal.amenalbackend.budget.core.port.in.MetreAvUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/metreAvs")
@RequiredArgsConstructor
public class MetreAvController {
	@Autowired
	private MetreAvUseCase metreAvUseCase;

	@GetMapping
	public ResponseEntity<List<MetreAv>> getAllMetreAvs() {
		return ResponseEntity.ok(metreAvUseCase.findAllMetreAvs());
	}
	
	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<MetreAv>> getMetresByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(metreAvUseCase.getMetresByAvenantId(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MetreAv> getMetreAvById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(metreAvUseCase.findMetreAvById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<MetreAv> saveMetreAv(@RequestBody MetreAv metreAv) {
		return ResponseEntity.status(HttpStatus.CREATED).body(metreAvUseCase.saveMetreAv(metreAv));
	}

	@PutMapping
	public ResponseEntity<MetreAv> updateMetreAv(@RequestBody MetreAv metreAv) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(metreAvUseCase.updateMetreAv(metreAv));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MetreAv> deleteMetreAv(@PathVariable("id") Integer id) {
		try {
			metreAvUseCase.deleteMetreAv(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

}
