package com.amenal.amenalbackend.adapter.project.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.application.project.dto.LotTableDto;
import com.amenal.amenalbackend.application.project.port.in.LotTableUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lotTable")
@RequiredArgsConstructor
public class LotTableController {
	@Autowired
	private LotTableUseCase lotTableUseCase;

	@GetMapping("/project/{id}/charge/{charge}")
	public ResponseEntity<List<LotTableDto>> getLotTableByProjectIdAndCharge(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(lotTableUseCase.getLotTableByProjectIdAndCharge(id, charge));
	}

	@GetMapping("/avenant/{id}/charge/{charge}")
	public ResponseEntity<List<LotTableDto>> getLotTableByAvenantIdAndCharge(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(lotTableUseCase.getLotTableByAvenantIdAndCharge(id, charge));
	}
}
