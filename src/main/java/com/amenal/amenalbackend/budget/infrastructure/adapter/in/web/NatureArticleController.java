package com.amenal.amenalbackend.budget.infrastructure.adapter.in.web;

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

import com.amenal.amenalbackend.budget.core.domain.NatureArticle;
import com.amenal.amenalbackend.budget.core.port.in.NatureArticleUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/natureArticles")
@RequiredArgsConstructor
public class NatureArticleController {
	@Autowired
	private NatureArticleUseCase natureArticleUseCase;

	@GetMapping
	public ResponseEntity<List<NatureArticle>> getAllNatureArticles() {
		return ResponseEntity.ok(natureArticleUseCase.findAllNatureArticles());
	}

	@GetMapping("/{id}")
	public ResponseEntity<NatureArticle> getNatureArticleById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(natureArticleUseCase.findNatureArticleById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<NatureArticle> saveNatureArticle(@RequestBody NatureArticle natureArticle) {
		return ResponseEntity.status(HttpStatus.CREATED).body(natureArticleUseCase.saveNatureArticle(natureArticle));
	}

	@PutMapping
	public ResponseEntity<NatureArticle> updateNatureArticle(@RequestBody NatureArticle natureArticle) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(natureArticleUseCase.updateNatureArticle(natureArticle));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<NatureArticle> deleteNatureArticle(@PathVariable("id") Integer id) {
		try {
			natureArticleUseCase.deleteNatureArticle(id);
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
