package com.amenal.amenalbackend.achat.infrastructure.adapter.in.web;

import java.util.List;
import java.util.NoSuchElementException;

import com.amenal.amenalbackend.achat.infrastructure.dto.AddDetailReceptionDto;
import com.amenal.amenalbackend.utils.infrastructure.exception.DetailReceptionLargerThanDetailCommande;
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

import com.amenal.amenalbackend.achat.core.domain.DetailReception;
import com.amenal.amenalbackend.achat.core.port.in.DetailReceptionUseCase;
import com.amenal.amenalbackend.achat.infrastructure.dto.DetailReceptionDto;

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
	public ResponseEntity<?> saveDetailReception(@RequestBody AddDetailReceptionDto addDetailReceptionDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(detailReceptionUseCase
					.saveDetailReception(addDetailReceptionDto));
		} catch (DetailReceptionLargerThanDetailCommande e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> updateDetailReception(@RequestBody DetailReception detailReception) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailReceptionUseCase.updateDetailReception(detailReception));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DetailReceptionLargerThanDetailCommande e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
