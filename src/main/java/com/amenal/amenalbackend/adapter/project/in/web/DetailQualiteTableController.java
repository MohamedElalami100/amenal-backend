package com.amenal.amenalbackend.adapter.project.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amenal.amenalbackend.application.project.dto.DetailQualiteTableDto;
import com.amenal.amenalbackend.application.project.port.in.DetailQualiteTableUseCase;

@RestController
@RequestMapping("/detailQualiteTable")
public class DetailQualiteTableController {
    
    private final DetailQualiteTableUseCase detailQualiteTableUseCase;
    
    @Autowired
    public DetailQualiteTableController(DetailQualiteTableUseCase detailQualiteTableUseCase) {
        this.detailQualiteTableUseCase = detailQualiteTableUseCase;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getDetailQualiteTableByProject(@PathVariable Integer projectId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getDetailQualiteTableByProjectId(projectId));
    }

    @GetMapping("/project/{projectId}/lot/{lotId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByLotAndProject(projectId, lotId));
    }

    @GetMapping("/project/{projectId}/produit/{produitDesignation}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByProduitAndProject(@PathVariable Integer projectId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByProduitAndProject(projectId, produitDesignation));
    }
    
    @GetMapping("/project/{projectId}/lot/{lotId}/produit/{produitDesignation}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByProduitAndLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByLotAndProduitAndProject(projectId, lotId, produitDesignation));
    }
    

    @GetMapping("/project/{projectId}/tache/{tacheId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByTacheAndProject(@PathVariable Integer projectId, @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByTacheAndProject(projectId, tacheId));
    }

    @GetMapping("/avenant/{id}")
    public ResponseEntity<List<DetailQualiteTableDto>> getDetailQualiteTableByAvenantId(@PathVariable Integer id) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getDetailQualiteTableByAvenantId(id));
    }

    @GetMapping("/lot/{lotId}/produit/{produitId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByLotAndProduitAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer produitId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByLotAndProduitAndAvenant(lotId, produitId));
    }

    @GetMapping("/avenant/{avenantId}/lot/{lotId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByLotAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByLotAndAvenant(lotId, avenantId));
    }

    @GetMapping("/avenant/{avenantId}/produit/{produitId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByProduitAndAvenant(
            @PathVariable Integer produitId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByProduitAndAvenant(produitId));
    }

    @GetMapping("/avenant/tache/{tacheId}")
    public ResponseEntity<List<DetailQualiteTableDto>> getFilteredDetailQualiteTableByTacheAndAvenant(
            @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailQualiteTableUseCase.getFilteredDetailQualiteTableByTacheAndAvenant(tacheId));
    }
}
