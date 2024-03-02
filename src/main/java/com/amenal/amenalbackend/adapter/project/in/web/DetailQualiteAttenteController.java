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

import com.amenal.amenalbackend.application.project.domain.DetailQualiteAttente;
import com.amenal.amenalbackend.application.project.port.in.DetailQualiteAttenteUseCase;
import com.amenal.amenalbackend.application.project.port.in.SaveViaDetailQualiteAttenteUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailQualiteAttentes")
@RequiredArgsConstructor
public class DetailQualiteAttenteController {
	@Autowired
	private DetailQualiteAttenteUseCase detailQualiteAttenteUseCase;
	
	@Autowired
	private SaveViaDetailQualiteAttenteUseCase saveViaDetailQualiteAttenteUseCase;

	@GetMapping
	public ResponseEntity<List<DetailQualiteAttente>> getAllDetailQualiteAttentes() {
		return ResponseEntity.ok(detailQualiteAttenteUseCase.findAllDetailQualiteAttentes());
	}
	
	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<DetailQualiteAttente>> getDetailQualiteAttentesByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(detailQualiteAttenteUseCase.getDetailQualiteAttentesByAvenantId(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailQualiteAttente> getDetailQualiteAttenteById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailQualiteAttenteUseCase.findDetailQualiteAttenteById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailQualiteAttente> saveDetailQualiteAttente(@RequestBody DetailQualiteAttente detailQualiteAttente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailQualiteAttenteUseCase.saveDetailQualiteAttente(detailQualiteAttente));
	}

	@PostMapping("/addAll")
	public ResponseEntity<List<DetailQualiteAttente>> saveAllDetailQualiteAttentes(@RequestBody List<DetailQualiteAttente> detailQualiteAttentes) {
		List<DetailQualiteAttente> addedDetails = new ArrayList<>();
		
		for(DetailQualiteAttente detailQualiteAttente: detailQualiteAttentes) {
			DetailQualiteAttente addedDetail = detailQualiteAttenteUseCase.saveDetailQualiteAttente(detailQualiteAttente);
			addedDetails.add(detailQualiteAttenteUseCase.saveDetailQualiteAttente(addedDetail));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addedDetails);
	}
	
	@PutMapping
	public ResponseEntity<DetailQualiteAttente> updateDetailQualiteAttente(@RequestBody DetailQualiteAttente detailQualiteAttente) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailQualiteAttenteUseCase.updateDetailQualiteAttente(detailQualiteAttente));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailQualiteAttente> deleteDetailQualiteAttente(@PathVariable("id") Integer id) {
		try {
			detailQualiteAttenteUseCase.deleteDetailQualiteAttente(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/valider/{id}")
	public ResponseEntity<String> saveAllDetailQualiteAttentes(@PathVariable("id") Integer id) {
		try {
			saveViaDetailQualiteAttenteUseCase.valider(id);
	        return ResponseEntity.ok("Validation successful");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validation failed: " + e.getMessage());
		}
	}

	@DeleteMapping("/avenant/{id}")
	public ResponseEntity<DetailQualiteAttente> deleteDetailQualiteAttentesByAvenantId(@PathVariable("id") Integer id) {
		List<DetailQualiteAttente> detailQualiteAttentes = detailQualiteAttenteUseCase.getDetailQualiteAttentesByAvenantId(id);
		for (DetailQualiteAttente detail: detailQualiteAttentes) {
			try {
				detailQualiteAttenteUseCase.deleteDetailQualiteAttente(detail.getId());
			} catch (NoSuchElementException e) {
			} catch (DataIntegrityViolationException  e) {
				// Return a response with status 400 Bad Request for data integrity violation
			    return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.noContent().build();	
	}
}
