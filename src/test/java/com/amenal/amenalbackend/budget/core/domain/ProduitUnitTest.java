package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.budget.BudgetTestDataUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitUnitTest {
    @Test
    public void testGetMntRef_WithNonNullTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Double result = produit.getMntRef();

        // Then
        assertEquals(100.0 * 2, result);
    }

    @Test
    public void testGetMntRef_WithNullTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithNullTaches();

        // When
        Double result = produit.getMntRef();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMncBdg_WithNonNullTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Double result = produit.getMncBdg();

        // Then
        assertEquals(2*65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNonNullSecondaryTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();
        produit.getTaches().get(0).setCle(false);

        // When
        Double result = produit.getMncBdg();

        // Then
        assertEquals(65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNullSecondaryTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();
        produit.getTaches().get(0).setCle(null);

        // When
        Double result = produit.getMncBdg();

        // Then
        assertEquals(65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNullTaches() {
        // Given
        Produit produit = new Produit();

        // When
        Double result = produit.getMncBdg();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMrgRef_WithNonNullValues() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Double result = produit.getMrgRef();

        // Then
        assertEquals(200.0 - 130.0 , result);
    }

    @Test
    public void testGetMrgRef_WithNullValues() {
        // Given
        Produit produit = new Produit();

        // When
        Double result = produit.getMrgRef();

        // Then
        assertEquals(0.0, result);
    }

    @Test
    public void testGetMrpRef_WithNonNullValues() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Double result = produit.getMrpRef();

        // Then
        assertEquals((200.0 - 130.0)/200, result);
    }

    @Test
    public void testGetMrpRef_WithZeroMntRef() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithSecondaryTaches();

        // When
        Double result = produit.getMrpRef();

        // Then
        assertEquals(0.0, result);
    }

    @Test
    public void testGetDateDbtIni_WithNonNullTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        LocalDate result = produit.getDateDbtIni();

        // Then
        assertEquals(LocalDate.of(2024, 4, 1), result);
    }

    @Test
    public void testGetDateDbtIni_WithNullTaches() {
        // Given
        Produit produit = new Produit();

        // When
        LocalDate result = produit.getDateDbtIni();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetDateFinIni_WithNonNullTaches() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        LocalDate result = produit.getDateFinIni();

        // Then
        assertEquals(LocalDate.of(2024, 4, 1).plusDays(9), result);
    }

    @Test
    public void testGetDateFinIni_WithNullTaches() {
        // Given
        Produit produit = new Produit();

        // When
        LocalDate result = produit.getDateFinIni();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetDlaIni_WithNonNullDates() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Integer result = produit.getDlaIni();

        // Then
        assertEquals(10, result);
    }

    @Test
    public void testGetDlaIni_WithNullDates() {
        // Given
        Produit produit = new Produit();

        // When
        Integer result = produit.getDlaIni();

        // Then
        assertEquals(1, result);
    }

    @Test
    public void testGetQteCum_WithNonNullValues() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Double result = produit.getQteCum();

        // Then
        assertEquals(960.0 , result);
    }

    @Test
    public void testGetQteCum_WithNullValues() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithNullTaches();

        // When
        Double result = produit.getQteCum();

        // Then
        assertEquals(0.0 , result);
    }

    @Test
    public void testGetPucBdg_WithNonNullValues() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithTaches();

        // When
        Double result = produit.getPucBdg();

        // Then
        assertEquals(130.0/960.0 , result);
    }

    @Test
    public void testGetPucBdg_WithNullValues() {
        // Given
        Produit produit = BudgetTestDataUtil.createProduitWithNullTaches();

        // When
        Double result = produit.getPucBdg();

        // Then
        assertEquals(0.0 , result);
    }

}