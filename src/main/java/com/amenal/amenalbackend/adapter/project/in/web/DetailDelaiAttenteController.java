package com.amenal.amenalbackend.adapter.project.in.web;

import java.util.ArrayList;
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

import com.amenal.amenalbackend.application.project.domain.DetailDelaiAttente;
import com.amenal.amenalbackend.application.project.port.in.DetailDelaiAttenteUseCase;
import com.amenal.amenalbackend.application.project.port.in.SaveViaDetailDelaiAttenteUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailDelaiAttentes")
@RequiredArgsConstructor
public class DetailDelaiAttenteController {
	@Autowired
	private DetailDelaiAttenteUseCase detailDelaiAttenteUseCase;
	
	@Autowired
	private SaveViaDetailDelaiAttenteUseCase saveViaDetailDelaiAttenteUseCase;

	@GetMapping
	public ResponseEntity<List<DetailDelaiAttente>> getAllDetailDelaiAttentes() {
		return ResponseEntity.ok(detailDelaiAttenteUseCase.findAllDetailDelaiAttentes());
	}

	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<DetailDelaiAttente>> getDetailDelaiAttentesByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(detailDelaiAttenteUseCase.getDetailDelaiAttentesByAvenantId(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailDelaiAttente> getDetailDelaiAttenteById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailDelaiAttenteUseCase.findDetailDelaiAttenteById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailDelaiAttente> saveDetailDelaiAttente(@RequestBody DetailDelaiAttente detailDelaiAttente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailDelaiAttenteUseCase.saveDetailDelaiAttente(detailDelaiAttente));
	}

	@PostMapping("/addAll")
	public ResponseEntity<List<DetailDelaiAttente>> saveAllDetailDelaiAttentes(@RequestBody List<DetailDelaiAttente> detailDelaiAttentes) {
		List<DetailDelaiAttente> addedDetails = new ArrayList<>();
		
		for(DetailDelaiAttente detailDelaiAttente: detailDelaiAttentes) {
			try {
				DetailDelaiAttente addedDetail = detailDelaiAttenteUseCase.saveDetailDelaiAttente(detailDelaiAttente);
				addedDetails.add(detailDelaiAttenteUseCase.saveDetailDelaiAttenteWithErreur(addedDetail));
			} catch (Exception e) {
			}
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addedDetails);
	}
	
	@PutMapping
	public ResponseEntity<DetailDelaiAttente> updateDetailDelaiAttente(@RequestBody DetailDelaiAttente detailDelaiAttente) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailDelaiAttenteUseCase.updateDetailDelaiAttente(detailDelaiAttente));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailDelaiAttente> deleteDetailDelaiAttente(@PathVariable("id") Integer id) {
		try {
			detailDelaiAttenteUseCase.deleteDetailDelaiAttente(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/valider/{id}")
	public ResponseEntity<String> saveAllDetailDelaiAttentes(@PathVariable("id") Integer id) {
		try {
			saveViaDetailDelaiAttenteUseCase.valider(id);
	        return ResponseEntity.ok("Validation successful");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validation failed: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/avenant/{id}")
	public ResponseEntity<DetailDelaiAttente> deleteDetailDelaiAttentesByAvenantId(@PathVariable("id") Integer id) {
		List<DetailDelaiAttente> detailDelaiAttentes = detailDelaiAttenteUseCase.getDetailDelaiAttentesByAvenantId(id);
		for (DetailDelaiAttente detail: detailDelaiAttentes) {
			try {
				detailDelaiAttenteUseCase.deleteDetailDelaiAttente(detail.getId());
			} catch (NoSuchElementException e) {
			} catch (DataIntegrityViolationException  e) {
				// Return a response with status 400 Bad Request for data integrity violation
			    return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.noContent().build();	
	}

}
