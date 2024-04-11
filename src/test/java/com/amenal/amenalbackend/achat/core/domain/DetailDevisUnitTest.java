package com.amenal.amenalbackend.achat.core.domain;

import com.amenal.amenalbackend.achat.AchatTestDataUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DetailDevisUnitTest {

    @Test
    public void testGetMntHt() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithValues(10.0, 5.0);

        // When
        Double result = detailDevis.getMntHt();

        // Then
        assertEquals(10.0 * 5, result);
    }

    @Test
    public void testGetMntTva() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithValues(10.0, 5.0);

        // When
        Double result = detailDevis.getMntTva();

        // Then
        assertEquals(10.0 * 5 * 0.2, result);
    }

    @Test
    public void testGetMntTtc() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithValues(10.0, 5.0);

        // When
        Double result = detailDevis.getMntTtc();

        // Then
        assertEquals(10.0 * 5 * 1.2, result);
    }

    @Test
    public void testGetMntHt_WhenPrixUnitaireIsNull() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithNullValues(null, 5.0);

        // When
        Double result = detailDevis.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntHt_WhenQteIsNull() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithNullValues(10.0, null);

        // When
        Double result = detailDevis.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTva_WhenMntHtIsNull() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithNullValues(10.0, null);

        // When
        Double result = detailDevis.getMntTva();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTtc_WhenMntHtIsNull() {
        // Given
        DetailDevis detailDevis = AchatTestDataUtil.createDetailDevisWithNullValues(10.0, null);

        // When
        Double result = detailDevis.getMntTtc();

        // Then
        assertNull(result);
    }
}
