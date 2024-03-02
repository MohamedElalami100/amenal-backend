package com.amenal.amenalbackend.adapter.project.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.application.project.dto.ChartDataDto;
import com.amenal.amenalbackend.application.project.port.in.GetChartDataUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ChartData")
@RequiredArgsConstructor
public class GetChartDataController {
	@Autowired
	private GetChartDataUseCase getChartDataUseCase;

	@GetMapping("/project/{id}/charge/{charge}")
	public ResponseEntity<ChartDataDto> getChartDataByProjectId(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByProjectId(id, charge));
	}
	
	@GetMapping("/project/{id}/tache/charge/{charge}")
	public ResponseEntity<ChartDataDto> getChartDataByProjectIdAndTacheIds(@PathVariable("id") Integer id, @PathVariable("charge") String charge, @RequestParam("tacheIds") Integer[] tacheIds) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByProjectIdAndTacheIds(id, tacheIds, charge));
	}
	
	@GetMapping("/project/{id}/lot/charge/{charge}")
	public ResponseEntity<ChartDataDto> getChartDataByProjectIdAndLots(@PathVariable("id") Integer id, @PathVariable("charge") String charge, @RequestParam("lots") String[] lots) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByProjectIdAndLots(id, lots, charge));
	}
	
	@GetMapping("/project/{id}/produit/charge/{charge}")
	public ResponseEntity<ChartDataDto> getTacheTableByProjectId(@PathVariable("id") Integer id, @PathVariable("charge") String charge, @RequestParam("produits") String[] produits) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByProjectIdAndProduits(id, produits, charge));
	}

	@GetMapping("/avenant/{id}/charge/{charge}")
	public ResponseEntity<ChartDataDto> getTacheTableByAvenantId(@PathVariable("id") Integer id, @PathVariable("charge") String charge) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByAvenantId(id, charge));
	}
	
	@GetMapping("/avenant/{id}/tache/charge/{charge}")
	public ResponseEntity<ChartDataDto> getChartDataByAvenantIdAndTacheIds(@PathVariable("id") Integer id, @PathVariable("charge") String charge, @RequestParam("tacheIds") Integer[] tacheIds) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByAvenantIdAndTacheIds(id, tacheIds, charge));
	}
	
	@GetMapping("/avenant/{id}/lot/charge/{charge}")
	public ResponseEntity<ChartDataDto> getChartDataByAvenantIdAndLots(@PathVariable("id") Integer id, @PathVariable("charge") String charge, @RequestParam("lots") String[] lots) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByAvenantIdAndLots(id, lots, charge));
	}
	
	@GetMapping("/avenant/{id}/produit/charge/{charge}")
	public ResponseEntity<ChartDataDto> getTacheTableByAvenantId(@PathVariable("id") Integer id, @PathVariable("charge") String charge, @RequestParam("produits") String[] produits) {
		return ResponseEntity.ok(getChartDataUseCase.getChartDataByAvenantIdAndProduits(id, produits, charge));
	}
}
