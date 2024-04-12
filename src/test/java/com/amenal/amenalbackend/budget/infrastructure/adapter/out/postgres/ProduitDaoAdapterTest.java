package com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.budget.core.domain.*;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.entities.ProduitEntity;
import com.amenal.amenalbackend.budget.infrastructure.adapter.out.postgres.repositories.ProduitRepository;
import com.amenal.amenalbackend.utils.infrastructure.exception.DuplicateElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProduitDaoAdapterTest {

    @Mock
    private ProduitRepository produitRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ProduitDaoAdapter produitDaoAdapter;

    @Test
    void testFindProduitById() {
        ProduitEntity entity = new ProduitEntity();
        entity.setId(1);

        when(produitRepository.findById(1)).thenReturn(Optional.of(entity));

        Produit result = produitDaoAdapter.findProduitById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllProduits() {
        when(produitRepository.findAll()).thenReturn(Collections.emptyList());

        List<Produit> result = produitDaoAdapter.findAllProduits();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveProduitWhenDuplicateExists() {
        Produit produit = new Produit();
        produit.setId(1);
        MetreAv metre = new MetreAv();
        metre.setId(1);
        BudgetAchatAv budgetAchatAv = new BudgetAchatAv();
        budgetAchatAv.setId(1);
        Avenant avenant = new Avenant();
        avenant.setId(1);
        budgetAchatAv.setAvenant(avenant);
        metre.setBudget(budgetAchatAv);
        produit.setMetre(metre);
        produit.setDesignation("test");
        ProduitEntity entity = new ProduitEntity();
        entity.setId(1);
        ProduitEntity entity2 = new ProduitEntity();
        entity2.setId(2);
        List<ProduitEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(produitRepository.getProduitsByAvenantIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);

        Produit result = produitDaoAdapter.saveProduit(produit);
        assertNotNull(result);
        assertEquals(2, result.getId());

    }

    @Test
    void testSaveProduitWhenDuplicateNotExists(){

        Produit produit = new Produit();
        produit.setId(1);
        MetreAv metre = new MetreAv();
        metre.setId(1);
        BudgetAchatAv budgetAchatAv = new BudgetAchatAv();
        budgetAchatAv.setId(1);
        Avenant avenant = new Avenant();
        avenant.setId(1);
        budgetAchatAv.setAvenant(avenant);
        metre.setBudget(budgetAchatAv);
        produit.setMetre(metre);
        produit.setDesignation("test");
        ProduitEntity entity = new ProduitEntity();
        entity.setId(1);
        List<ProduitEntity> sameEntities = new ArrayList<>();

        when(produitRepository.getProduitsByAvenantIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(produitRepository.save(any(ProduitEntity.class))).thenReturn(entity);

        Produit result = produitDaoAdapter.saveProduit(produit);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateProduitWhenDuplicateNotExists() throws DuplicateElementException{
        Produit produit = new Produit();
        produit.setId(1);
        produit.setDesignation("test");
        ProduitEntity existingEntity = new ProduitEntity();
        produit.setId(1);
        existingEntity.setDesignation("test");

        when(produitRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(produitRepository.save(any(ProduitEntity.class))).thenReturn(existingEntity);

        Produit result = produitDaoAdapter.updateProduit(produit);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }

    @Test
    void testupdateProduitWhenDuplicateExists() {
        Produit produit = new Produit();
        produit.setId(1);
        MetreAv metre = new MetreAv();
        metre.setId(1);
        BudgetAchatAv budgetAchatAv = new BudgetAchatAv();
        budgetAchatAv.setId(1);
        Avenant avenant = new Avenant();
        avenant.setId(1);
        budgetAchatAv.setAvenant(avenant);
        metre.setBudget(budgetAchatAv);
        produit.setMetre(metre);
        produit.setDesignation("test");
        ProduitEntity entity = new ProduitEntity();
        entity.setId(1);
        entity.setDesignation("test2");
        ProduitEntity entity2 = new ProduitEntity();
        entity2.setId(2);
        List<ProduitEntity> sameEntities = new ArrayList<>();
        sameEntities.add(entity2);

        when(produitRepository.getProduitsByAvenantIdAndDesignation(anyInt(), anyString())).thenReturn(sameEntities);
        when(produitRepository.findById(1)).thenReturn(Optional.of(entity));

        assertThrows(DuplicateElementException.class, () -> {
            produitDaoAdapter.updateProduit(produit);
        });

    }

    @Test
    void testSavProduitWhenDuplicateNotExists() throws DuplicateElementException{
        Produit produit = new Produit();
        produit.setId(1);
        produit.setDesignation("test");
        ProduitEntity existingEntity = new ProduitEntity();
        produit.setId(1);
        existingEntity.setDesignation("test");

        when(produitRepository.findById(1)).thenReturn(Optional.of(existingEntity));
        when(produitRepository.save(any(ProduitEntity.class))).thenReturn(existingEntity);

        Produit result = produitDaoAdapter.updateProduit(produit);
        assertNotNull(result);
        assertEquals(1, result.getId());

    }
    @Test
    void testUpdateProduit()  throws DuplicateElementException{
        Produit produit = new Produit();
        produit.setId(1);
        ProduitEntity entity = new ProduitEntity();
        entity.setId(1);
        when(produitRepository.findById(1)).thenReturn(Optional.of(entity));
        when(produitRepository.save(any(ProduitEntity.class))).thenReturn(entity);

        Produit result = produitDaoAdapter.updateProduit(produit);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteProduit() {
        ProduitEntity entity = new ProduitEntity();
        entity.setId(1);
        when(produitRepository.findById(1)).thenReturn(Optional.of(entity));

        produitDaoAdapter.deleteProduit(1);

        verify(produitRepository, times(1)).delete(entity);
    }
}
