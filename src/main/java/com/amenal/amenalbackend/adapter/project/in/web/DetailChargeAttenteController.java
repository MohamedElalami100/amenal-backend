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

import com.amenal.amenalbackend.application.project.domain.DetailChargeAttente;
import com.amenal.amenalbackend.application.project.port.in.DetailChargeAttenteUseCase;
import com.amenal.amenalbackend.application.project.port.in.SaveViaDetailChargeAttenteUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailChargeAttentes")
@RequiredArgsConstructor
public class DetailChargeAttenteController {
	@Autowired
	private DetailChargeAttenteUseCase detailChargeAttenteUseCase;
	
	@Autowired
	private SaveViaDetailChargeAttenteUseCase saveViaDetailChargeAttenteUseCase;

	@GetMapping
	public ResponseEntity<List<DetailChargeAttente>> getAllDetailChargeAttentes() {
		return ResponseEntity.ok(detailChargeAttenteUseCase.findAllDetailChargeAttentes());
	}

	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<DetailChargeAttente>> getDetailChargeAttentesByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(detailChargeAttenteUseCase.getDetailChargeAttentesByAvenantId(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailChargeAttente> getDetailChargeAttenteById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailChargeAttenteUseCase.findDetailChargeAttenteById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailChargeAttente> saveDetailChargeAttente(@RequestBody DetailChargeAttente detailChargeAttente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailChargeAttenteUseCase.saveDetailChargeAttente(detailChargeAttente));
	}
	
	@PostMapping("/addAll")
	public ResponseEntity<List<DetailChargeAttente>> saveAllDetailChargeAttentes(@RequestBody List<DetailChargeAttente> detailChargeAttentes) {
		List<DetailChargeAttente> addedDetails = new ArrayList<>();
		
		
		for(DetailChargeAttente detailChargeAttente: detailChargeAttentes) {
			DetailChargeAttente addedDetail = detailChargeAttenteUseCase.saveDetailChargeAttente(detailChargeAttente);
			addedDetails.add(detailChargeAttenteUseCase.saveDetailChargeAttenteWithErreur(addedDetail));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addedDetails);
	}

	@PutMapping
	public ResponseEntity<DetailChargeAttente> updateDetailChargeAttente(@RequestBody DetailChargeAttente detailChargeAttente) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailChargeAttenteUseCase.updateDetailChargeAttente(detailChargeAttente));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailChargeAttente> deleteDetailChargeAttente(@PathVariable("id") Integer id) {
		try {
			detailChargeAttenteUseCase.deleteDetailChargeAttente(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/valider/{id}")
	public ResponseEntity<String> saveAllDetailChargeAttentes(@PathVariable("id") Integer id) {
		try {
			saveViaDetailChargeAttenteUseCase.valider(id);
	        return ResponseEntity.ok("Validation successful");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validation failed: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/avenant/{id}")
	public ResponseEntity<DetailChargeAttente> deleteDetailChargeAttentesByAvenantId(@PathVariable("id") Integer id) {
		List<DetailChargeAttente> detailChargeAttentes = detailChargeAttenteUseCase.getDetailChargeAttentesByAvenantId(id);
		for (DetailChargeAttente detail: detailChargeAttentes) {
			try {
				detailChargeAttenteUseCase.deleteDetailChargeAttente(detail.getId());
			} catch (NoSuchElementException e) {
				return ResponseEntity.notFound().build();
			} catch (DataIntegrityViolationException  e) {
				// Return a response with status 400 Bad Request for data integrity violation
			    return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.noContent().build();	
	}

}
