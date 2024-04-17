package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.budget.BudgetTestDataUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TacheUnitTest {
    @Test
    public void testGetDateFin_WithPositiveDelai() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        LocalDate result = tache.getDateFin();

        // Then
        assertEquals(LocalDate.of(2024, 4, 10), result);
    }

    @Test
    public void testGetDateFin_WithNegativeDelai() {
        // Given
        Tache tache = new Tache();
        tache.setDlb(-5);
        tache.setDdb(LocalDate.of(2024, 4, 1));

        // When
        LocalDate result = tache.getDateFin();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetPucBdg_WithNonNullDetailChargesAndDetailProduits() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        Double result = tache.getPucBdg();

        // Then
        assertEquals(65.0 / 480, result);
    }

    @Test
    public void testGetPucBdg_WithNullDetailChargesAndDetailProduits() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithNullDetailChargesAndDetailProduits();

        // When
        Double result = tache.getPucBdg();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntRefB_WithNonNullPuRefAndQtePBdg() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        Double result = tache.getMntRefB();

        // Then
        assertEquals(4800.0, result);
    }

    @Test
    public void testGetMntRefB_WithNullPuRef() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();
        tache.setProduit(null);

        // When
        Double result = tache.getMntRefB();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntRefB_WithNullQtePBdg() {
        // Given
        Tache tache = new Tache();

        // When
        Double result = tache.getMntRefB();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMrgRefB_WithNonNullMntRefBAndMncBdg() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        Double result = tache.getMrgRefB();

        // Then
        assertEquals(4800.0 - 65.0, result);
    }

    @Test
    public void testGetMrgRefB_WithNullMncBdg() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithNullDetailChargesAndDetailProduits();

        // When
        Double result = tache.getMrgRefB();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMrpRefB_WithNonNullMntRefBAndMncBdg() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        Double result = tache.getMrpRefB();

        // Then
        assertEquals((4800.0 - 65.0)/4800, result);
    }

    @Test
    public void testGetQtePBdg_WithNonNullDetailProduits() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        Double result = tache.getQtePBdg();

        // Then
        assertEquals(480.0, result);
    }

    @Test
    public void testGetQtePBdg_WithNullDetailProduits() {
        // Given
        Tache tache = new Tache();

        // When
        Double result = tache.getQtePBdg();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMncBdg_WithNonNullDetailCharges() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithDetailChargesAndDetailProduits();

        // When
        Double result = tache.getMncBdg();

        // Then
        assertEquals(65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNullDetailCharges() {
        // Given
        Tache tache = BudgetTestDataUtil.createTacheWithNullDetailChargesAndDetailProduits();

        // When
        Double result = tache.getMncBdg();

        // Then
        assertNull(result);
    }

}