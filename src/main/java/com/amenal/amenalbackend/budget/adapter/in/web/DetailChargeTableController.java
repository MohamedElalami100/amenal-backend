package com.amenal.amenalbackend.budget.adapter.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.budget.application.dto.DetailChargeTableDto;
import com.amenal.amenalbackend.budget.application.port.in.DetailChargeTableUseCase;

@RestController
@RequestMapping("/detailChargeTable")
public class DetailChargeTableController {
    
    private final DetailChargeTableUseCase detailChargeTableUseCase;
    
    @Autowired
    public DetailChargeTableController(DetailChargeTableUseCase detailChargeTableUseCase) {
        this.detailChargeTableUseCase = detailChargeTableUseCase;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<DetailChargeTableDto>> getDetailChargeTable(@PathVariable Integer projectId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getDetailChargeTableByProjectId(projectId));
    }

    @GetMapping("/project/{projectId}/lot/{lotId}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByLotAndProject(projectId, lotId));
    }

    @GetMapping("/project/{projectId}/produit/{produitDesignation}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByProduitAndProject(@PathVariable Integer projectId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByProduitAndProject(projectId, produitDesignation));
    }
    
    @GetMapping("/project/{projectId}/lot/{lotId}/produit/{produitDesignation}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByProduitAndLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByLotAndProduitAndProject(projectId, lotId, produitDesignation));
    }

    @GetMapping("/project/{projectId}/tache/{tacheId}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByTacheAndProject(@PathVariable Integer projectId, @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByTacheAndProject(projectId, tacheId));
    }

    @GetMapping("/avenant/{id}")
    public ResponseEntity<List<DetailChargeTableDto>> getDetailChargeTableByAvenantId(@PathVariable Integer id) {
        return ResponseEntity.ok(detailChargeTableUseCase.getDetailChargeTableByAvenantId(id));
    }

    @GetMapping("/lot/{lotId}/produit/{produitId}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByLotAndProduitAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer produitId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByLotAndProduitAndAvenant(lotId, produitId));
    }

    @GetMapping("/avenant/{avenantId}/lot/{lotId}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByLotAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByLotAndAvenant(lotId, avenantId));
    }

    @GetMapping("/avenant/{avenantId}/produit/{produitId}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByProduitAndAvenant(
            @PathVariable Integer produitId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByProduitAndAvenant(produitId));
    }

    @GetMapping("/avenant/tache/{tacheId}")
    public ResponseEntity<List<DetailChargeTableDto>> getFilteredDetailChargeTableByTacheAndAvenant(
            @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailChargeTableUseCase.getFilteredDetailChargeTableByTacheAndAvenant(tacheId));
    }
}
