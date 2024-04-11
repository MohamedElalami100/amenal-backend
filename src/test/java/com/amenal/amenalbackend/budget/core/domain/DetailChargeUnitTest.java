package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.budget.BudgetTestDataUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DetailChargeUnitTest {

    @Test
    public void testGetMontant_WhenPrixAndQteAreNonNull() {
        // Given
        DetailCharge detailCharge = BudgetTestDataUtil.createDetailChargeWithNonNullPriceAndQte();

        // When
        Double result = detailCharge.getMontant();

        // Then
        assertEquals(10.0 * 5.0, result);
    }

    @Test
    public void testGetMontant_WhenPrixIsNull() {
        // Given
        DetailCharge detailCharge = BudgetTestDataUtil.createDetailChargeWithNullPrice();

        // When
        Double result = detailCharge.getMontant();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMontant_WhenQteIsNull() {
        // Given
        DetailCharge detailCharge = BudgetTestDataUtil.createDetailChargeWithNullQte();

        // When
        Double result = detailCharge.getMontant();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMontant_WhenBothPrixAndQteAreNull() {
        // Given
        DetailCharge detailCharge = BudgetTestDataUtil.createDetailChargeWithNullPriceAndQte();

        // When
        Double result = detailCharge.getMontant();

        // Then
        assertNull(result);
    }
}
