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

import com.amenal.amenalbackend.achat.application.domain.EvaluationFournisseur;
import com.amenal.amenalbackend.achat.application.port.in.EvaluationFournisseurUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/evaluationFournisseurs")
@RequiredArgsConstructor
public class EvaluationFournisseurController {
	@Autowired
	private EvaluationFournisseurUseCase evaluationFournisseurUseCase;

	@GetMapping
	public ResponseEntity<List<EvaluationFournisseur>> getAllEvaluationFournisseurs() {
		return ResponseEntity.ok(evaluationFournisseurUseCase.findAllEvaluationFournisseurs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EvaluationFournisseur> getEvaluationFournisseurById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(evaluationFournisseurUseCase.findEvaluationFournisseurById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<EvaluationFournisseur> saveEvaluationFournisseur(@RequestBody EvaluationFournisseur evaluationFournisseur) {
		return ResponseEntity.status(HttpStatus.CREATED).body(evaluationFournisseurUseCase.saveEvaluationFournisseur(evaluationFournisseur));
	}

	@PutMapping
	public ResponseEntity<EvaluationFournisseur> updateEvaluationFournisseur(@RequestBody EvaluationFournisseur evaluationFournisseur) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(evaluationFournisseurUseCase.updateEvaluationFournisseur(evaluationFournisseur));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EvaluationFournisseur> deleteEvaluationFournisseur(@PathVariable("id") Integer id) {
		try {
			evaluationFournisseurUseCase.deleteEvaluationFournisseur(id);
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
