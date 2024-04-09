package com.amenal.amenalbackend.budget.adapter.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.budget.application.dto.DetailProduitTableDto;
import com.amenal.amenalbackend.budget.application.port.in.DetailProduitTableUseCase;

@RestController
@RequestMapping("/detailProduitTable")
public class DetailProduitTableController {
    
    private final DetailProduitTableUseCase detailProduitTableUseCase;
    
    @Autowired
    public DetailProduitTableController(DetailProduitTableUseCase detailProduitTableUseCase) {
        this.detailProduitTableUseCase = detailProduitTableUseCase;
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<DetailProduitTableDto>> getDetailProduitTable(@PathVariable Integer id) {
        return ResponseEntity.ok(detailProduitTableUseCase.getDetailProduitTableByProjectId(id));
    }

    @GetMapping("/project/{projectId}/lot/{lotId}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByLotAndProject(projectId, lotId));
    }

    @GetMapping("/project/{projectId}/produit/{produitDesignation}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByProduitAndProject(@PathVariable Integer projectId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByProduitAndProject(projectId, produitDesignation));
    }
    
    @GetMapping("/project/{projectId}/lot/{lotId}/produit/{produitDesignation}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByProduitAndLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByLotAndProduitAndProject(projectId, lotId, produitDesignation));
    }

    @GetMapping("/project/{projectId}/tache/{tacheId}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByTacheAndProject(@PathVariable Integer projectId, @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByTacheAndProject(projectId, tacheId));
    }

    @GetMapping("/avenant/{id}")
    public ResponseEntity<List<DetailProduitTableDto>> getDetailProduitTableByAvenantId(@PathVariable Integer id) {
        return ResponseEntity.ok(detailProduitTableUseCase.getDetailProduitTableByAvenantId(id));
    }

    @GetMapping("/lot/{lotId}/produit/{produitId}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByLotAndProduitAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer produitId) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByLotAndProduitAndAvenant(lotId, produitId));
    }

    @GetMapping("/avenant/{avenantId}/lot/{lotId}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByLotAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByLotAndAvenant(lotId, avenantId));
    }

    @GetMapping("/avenant/{avenantId}/produit/{produitId}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByProduitAndAvenant(
            @PathVariable Integer produitId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByProduitAndAvenant(produitId));
    }

    @GetMapping("/avenant/tache/{tacheId}")
    public ResponseEntity<List<DetailProduitTableDto>> getFilteredDetailProduitTableByTacheAndAvenant(
            @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailProduitTableUseCase.getFilteredDetailProduitTableByTacheAndAvenant(tacheId));
    }
}
