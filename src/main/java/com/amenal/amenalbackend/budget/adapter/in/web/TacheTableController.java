package com.amenal.amenalbackend.budget.adapter.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.budget.application.dto.TacheTableDto;
import com.amenal.amenalbackend.budget.application.port.in.TacheTableUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tacheTable")
@RequiredArgsConstructor
public class TacheTableController {
	@Autowired
	private TacheTableUseCase tacheTableUseCase;

	@GetMapping("/project/{id}/charge/{charge}")
	public ResponseEntity<List<TacheTableDto>> getTacheTableByProjectIdAndCharge(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(tacheTableUseCase.getTacheTableByProjectIdAndCharge(id, charge));
	}

	@GetMapping("/avenant/{id}/charge/{charge}")
	public ResponseEntity<List<TacheTableDto>> getTacheTableByAvenantIdAndCharge(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(tacheTableUseCase.getTacheTableByAvenantIdAndCharge(id, charge));
	}
	
}
