package com.amenal.amenalbackend.budget.adapter.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.budget.application.dto.ProduitTableDto;
import com.amenal.amenalbackend.budget.application.port.in.ProduitTableUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produitTable")
@RequiredArgsConstructor
public class ProduitTableController {
	@Autowired
	private ProduitTableUseCase produitTableUseCase;

	@GetMapping("/project/{id}/charge/{charge}")
	public ResponseEntity<List<ProduitTableDto>> getProduitTableByProjectIdAndCharge(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(produitTableUseCase.getProduitTableByProjectIdAndCharge(id, charge));
	}

	@GetMapping("/avenant/{id}/charge/{charge}")
	public ResponseEntity<List<ProduitTableDto>> getProduitTableByAvenantId(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(produitTableUseCase.getProduitTableByAvenantIdAndCharge(id, charge));
	}
}
