package com.amenal.amenalbackend.aspects;

import com.amenal.amenalbackend.TestConfig;
import com.amenal.amenalbackend.budget.core.domain.DetailProduit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Import(TestConfig.class)
public class DoubleAspectTest {

    @Autowired
    private DetailProduit detailProduit;

    @Test
    public void DoubleAspectTestWithDetailProduit() {
        // Given
        detailProduit.setDim1(1.3333333);
        detailProduit.setDim2(2.0);

        // When
        Double result = detailProduit.getQte();

        // Then
        assertEquals(2.67, result);
    }

    @Test
    public void DoubleAspectTestWithDetailProduitWithNullValue() {
        // Given
        detailProduit.setDim1(null);

        // When
        Double result = detailProduit.getDim1();

        // Then
        assertNull(result);
    }
}
