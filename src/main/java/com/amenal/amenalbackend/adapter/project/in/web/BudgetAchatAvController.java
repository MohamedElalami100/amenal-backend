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

import com.amenal.amenalbackend.application.project.domain.BudgetAchatAv;
import com.amenal.amenalbackend.application.project.port.in.BudgetAchatAvUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/budgetAchatAvs")
@RequiredArgsConstructor
public class BudgetAchatAvController {
	@Autowired
	private BudgetAchatAvUseCase budgetAchatAvUseCase;

	@GetMapping
	public ResponseEntity<List<BudgetAchatAv>> getAllBudgetAchatAvs() {
		return ResponseEntity.ok(budgetAchatAvUseCase.findAllBudgetAchatAvs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetAchatAv> getBudgetAchatAvById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(budgetAchatAvUseCase.findBudgetAchatAvById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@PostMapping
	public ResponseEntity<BudgetAchatAv> saveBudgetAchatAv(@RequestBody BudgetAchatAv budgetAchatAv) {
		return ResponseEntity.status(HttpStatus.CREATED).body(budgetAchatAvUseCase.saveBudgetAchatAv(budgetAchatAv));
	}

	@PutMapping
	public ResponseEntity<BudgetAchatAv> updateBudgetAchatAv(@RequestBody BudgetAchatAv budgetAchatAv) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(budgetAchatAvUseCase.updateBudgetAchatAv(budgetAchatAv));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BudgetAchatAv> deleteBudgetAchatAv(@PathVariable("id") Integer id) {
		try {
			budgetAchatAvUseCase.deleteBudgetAchatAv(id);
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
