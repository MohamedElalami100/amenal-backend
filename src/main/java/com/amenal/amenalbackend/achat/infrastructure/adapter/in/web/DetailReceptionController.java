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

import com.amenal.amenalbackend.achat.application.domain.DetailReception;
import com.amenal.amenalbackend.achat.application.dto.DetailReceptionDto;
import com.amenal.amenalbackend.achat.application.port.in.DetailReceptionUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailReceptions")
@RequiredArgsConstructor
public class DetailReceptionController {
	@Autowired
	private DetailReceptionUseCase detailReceptionUseCase;

	@GetMapping
	public ResponseEntity<List<DetailReceptionDto>> getAllDetailReceptions() {
		return ResponseEntity.ok(detailReceptionUseCase.findAllDetailReceptions());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailReceptionDto> getDetailReceptionById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailReceptionUseCase.findDetailReceptionById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailReception> saveDetailReception(@RequestBody DetailReception detailReception) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailReceptionUseCase.saveDetailReception(detailReception));
	}

	@PutMapping
	public ResponseEntity<DetailReception> updateDetailReception(@RequestBody DetailReception detailReception) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailReceptionUseCase.updateDetailReception(detailReception));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailReception> deleteDetailReception(@PathVariable("id") Integer id) {
		try {
			detailReceptionUseCase.deleteDetailReception(id);
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
