package com.amenal.amenalbackend.adapter.project.in.web;

import com.amenal.amenalbackend.application.project.dto.DetailDelaiTableDto;
import com.amenal.amenalbackend.application.project.dto.RowDelaiDto;
import com.amenal.amenalbackend.application.project.port.in.DetailDelaiTableUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detailDelaiTable")
public class DetailDelaiTableController {

    private final DetailDelaiTableUseCase detailDelaiTableUseCase;

    @Autowired
    public DetailDelaiTableController(DetailDelaiTableUseCase detailDelaiTableUseCase) {
        this.detailDelaiTableUseCase = detailDelaiTableUseCase;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<DetailDelaiTableDto>> getDetailDelaiTable(@PathVariable Integer projectId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getDetailDelaiTableByProjectId(projectId));
    }

    @GetMapping("/project/{projectId}/lot/{lotId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByLotAndProject(projectId, lotId));
    }

    @GetMapping("/project/{projectId}/produit/{produitDesignation}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByProduitAndProject(@PathVariable Integer projectId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByProduitAndProject(projectId, produitDesignation));
    }

    @GetMapping("/project/{projectId}/lot/{lotId}/produit/{produitDesignation}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByProduitAndLotAndProject(@PathVariable Integer projectId, @PathVariable Integer lotId, @PathVariable String produitDesignation) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByLotAndProduitAndProject(projectId, lotId, produitDesignation));
    }
    
    @GetMapping("/project/{projectId}/tache/{tacheId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByTacheAndProject(@PathVariable Integer projectId, @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByTacheAndProject(projectId, tacheId));
    }

    @GetMapping("/activitePrincipale/{tacheId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByActivitePrincipaleAndProject(@PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByActivitePrincipaleAndProject(tacheId));
    }

    @GetMapping("/avenant/{id}")
    public ResponseEntity<List<DetailDelaiTableDto>> getDetailDelaiTableByAvenantId(@PathVariable Integer id) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getDetailDelaiTableByAvenantId(id));
    }

    @GetMapping("/lot/{lotId}/produit/{produitId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByLotAndProduitAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer produitId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByLotAndProduitAndAvenant(lotId, produitId));
    }

    @GetMapping("/avenant/{avenantId}/lot/{lotId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByLotAndAvenant(
            @PathVariable Integer lotId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByLotAndAvenant(lotId, avenantId));
    }

    @GetMapping("/avenant/{avenantId}/produit/{produitId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByProduitAndAvenant(
            @PathVariable Integer produitId, @PathVariable Integer avenantId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByProduitAndAvenant(produitId));
    }

    @GetMapping("/avenant/tache/{tacheId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByTacheAndAvenant(
            @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByTacheAndAvenant(tacheId));
    }

    @GetMapping("/avenant/activitePrincipale/{tacheId}")
    public ResponseEntity<List<RowDelaiDto>> getFilteredDetailDelaiTableByActivitePrincipaleAndAvenant(
            @PathVariable Integer tacheId) {
        return ResponseEntity.ok(detailDelaiTableUseCase.getFilteredDetailDelaiTableByActivitePrincipaleAndAvenant(tacheId));
    }
}
