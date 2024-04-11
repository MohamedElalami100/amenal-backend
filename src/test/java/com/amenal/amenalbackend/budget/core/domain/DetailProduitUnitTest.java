package com.amenal.amenalbackend.budget.core.domain;

import com.amenal.amenalbackend.budget.BudgetTestDataUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetailProduitUnitTest {

    @Test
    public void testGetQte_WhenDimensionsAndNbrAreNonNull() {
        // Given
        DetailProduit detailProduit = BudgetTestDataUtil.createDetailProduitWithNonNullDimensionsAndNbr();

        // When
        Double result = detailProduit.getQte();

        // Then
        assertEquals(2.0 * 3.0 * 4.0 * 5.0, result);
    }

    @Test
    public void testGetQte_WhenDimensionsAndNbrAreNonNullAndNull() {
        // Given
        DetailProduit detailProduit = BudgetTestDataUtil.createDetailProduitWithNonNullAndNullDimensionsAndNbr();

        // When
        Double result = detailProduit.getQte();

        // Then
        assertEquals(2.0 * 3.0 * 5.0, result);
    }

    @Test
    public void testGetQte_WhenDimensionsAndNbrAreNull() {
        // Given
        DetailProduit detailProduit = BudgetTestDataUtil.createDetailProduitWithNullDimensionsAndNbr();

        // When
        Double result = detailProduit.getQte();

        // Then
        assertEquals(1.0, result);
    }
}
