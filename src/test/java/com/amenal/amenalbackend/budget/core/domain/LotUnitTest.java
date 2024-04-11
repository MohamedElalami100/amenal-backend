package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.budget.BudgetTestDataUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LotUnitTest {

    @Test
    public void testGetMntRefB_WithNonNullTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        Double result = lot.getMntRefB();

        // Then
        assertEquals(4800.0 * 2, result);
    }

    @Test
    public void testGetMntRefB_WithNullTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithNullTaches();

        // When
        Double result = lot.getMntRefB();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMncBdg_WithNonNullTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        Double result = lot.getMncBdg();

        // Then
        assertEquals(2*65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNonNullSecondaryTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();
        lot.getTaches().get(0).setCleAttachement(false);

        // When
        Double result = lot.getMncBdg();

        // Then
        assertEquals(65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNullSecondaryTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();
        lot.getTaches().get(0).setCleAttachement(null);

        // When
        Double result = lot.getMncBdg();

        // Then
        assertEquals(65.0, result);
    }

    @Test
    public void testGetMncBdg_WithNullTaches() {
        // Given
        Lot lot = new Lot();

        // When
        Double result = lot.getMncBdg();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMrgRefB_WithNonNullValues() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        Double result = lot.getMrgRefB();

        // Then
        assertEquals(9600.0 - 130.0 , result);
    }

    @Test
    public void testGetMrgRefB_WithNullValues() {
        // Given
        Lot lot = new Lot();

        // When
        Double result = lot.getMrgRefB();

        // Then
        assertEquals(0.0, result);
    }

    @Test
    public void testGetMrpRefB_WithNonNullValues() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        Double result = lot.getMrpRefB();

        // Then
        assertEquals((9600.0 - 130.0)/9600, result);
    }

    @Test
    public void testGetMrpRefB_WithZeroMntRefB() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithSecondaryTaches();

        // When
        Double result = lot.getMrpRefB();

        // Then
        assertEquals(0.0, result);
    }

    @Test
    public void testGetDateDbtIni_WithNonNullTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        LocalDate result = lot.getDateDbtIni();

        // Then
        assertEquals(LocalDate.of(2024, 4, 1), result);
    }

    @Test
    public void testGetDateDbtIni_WithNullTaches() {
        // Given
        Lot lot = new Lot();

        // When
        LocalDate result = lot.getDateDbtIni();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetDateFinIni_WithNonNullTaches() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        LocalDate result = lot.getDateFinIni();

        // Then
        assertEquals(LocalDate.of(2024, 4, 1).plusDays(9), result);
    }

    @Test
    public void testGetDateFinIni_WithNullTaches() {
        // Given
        Lot lot = new Lot();

        // When
        LocalDate result = lot.getDateFinIni();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetDlaIni_WithNonNullDates() {
        // Given
        Lot lot = BudgetTestDataUtil.createLotWithTaches();

        // When
        Integer result = lot.getDlaIni();

        // Then
        assertEquals(10, result);
    }

    @Test
    public void testGetDlaIni_WithNullDates() {
        // Given
        Lot lot = new Lot();

        // When
        Integer result = lot.getDlaIni();

        // Then
        assertEquals(1, result);
    }
}
