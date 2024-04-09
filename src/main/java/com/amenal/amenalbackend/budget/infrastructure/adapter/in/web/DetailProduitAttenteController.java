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

import com.amenal.amenalbackend.budget.core.domain.DetailProduitAttente;
import com.amenal.amenalbackend.budget.core.port.in.DetailProduitAttenteUseCase;
import com.amenal.amenalbackend.budget.core.port.in.SaveViaDetailProduitAttenteUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detailProduitAttentes")
@RequiredArgsConstructor
public class DetailProduitAttenteController {
	@Autowired
	private DetailProduitAttenteUseCase detailProduitAttenteUseCase;
	
	@Autowired
	private SaveViaDetailProduitAttenteUseCase saveViaDetailProduitAttenteUseCase;

	@GetMapping
	public ResponseEntity<List<DetailProduitAttente>> getAllDetailProduitAttentes() {
		return ResponseEntity.ok(detailProduitAttenteUseCase.findAllDetailProduitAttentes());
	}

	@GetMapping("/avenant/{id}")
	public ResponseEntity<List<DetailProduitAttente>> getDetailProduitAttentesByAvenantId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(detailProduitAttenteUseCase.getDetailProduitAttentesByAvenantId(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailProduitAttente> getDetailProduitAttenteById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(detailProduitAttenteUseCase.findDetailProduitAttenteById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DetailProduitAttente> saveDetailProduitAttente(@RequestBody DetailProduitAttente detailProduitAttente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailProduitAttenteUseCase.saveDetailProduitAttente(detailProduitAttente));
	}

	@PostMapping("/addAll")
	public ResponseEntity<List<DetailProduitAttente>> saveAllDetailProduitAttentes(@RequestBody List<DetailProduitAttente> detailProduitAttentes) {
		return ResponseEntity.status(HttpStatus.CREATED).body(detailProduitAttenteUseCase.saveAllDetailProduitAttente(detailProduitAttentes));
	}
	
	@PutMapping
	public ResponseEntity<DetailProduitAttente> updateDetailProduitAttente(@RequestBody DetailProduitAttente detailProduitAttente) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detailProduitAttenteUseCase.updateDetailProduitAttente(detailProduitAttente));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DetailProduitAttente> deleteDetailProduitAttente(@PathVariable("id") Integer id) {
		try {
			detailProduitAttenteUseCase.deleteDetailProduitAttente(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/valider/{id}")
	public ResponseEntity<String> saveAllDetailProduitAttentes(@PathVariable("id") Integer id) {
		try {
			saveViaDetailProduitAttenteUseCase.valider(id);
	        return ResponseEntity.ok("Validation successful");
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validation failed: " + e.getMessage());
		}
	}	
	
	@DeleteMapping("/avenant/{id}")
	public ResponseEntity<DetailProduitAttente> deleteDetailProduitAttentesByAvenantId(@PathVariable("id") Integer id) {
		List<DetailProduitAttente> detailProduitAttentes = detailProduitAttenteUseCase.getDetailProduitAttentesByAvenantId(id);
		for (DetailProduitAttente detail: detailProduitAttentes) {
			try {
				detailProduitAttenteUseCase.deleteDetailProduitAttente(detail.getId());
			} catch (NoSuchElementException e) {
			}catch (DataIntegrityViolationException  e) {
				// Return a response with status 400 Bad Request for data integrity violation
			    return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.noContent().build();	
	}
}
