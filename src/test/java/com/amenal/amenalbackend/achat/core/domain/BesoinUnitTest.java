package com.amenal.amenalbackend.achat.core.domain;
import com.amenal.amenalbackend.achat.AchatTestDataUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BesoinUnitTest {

    @Test
    public void testGetPrixUnitaireHT_WhenChargeIsNull() {
        // Given
        Besoin besoin = AchatTestDataUtil.createBesoinWhenChargeIsNull();

        // When
        Double result = besoin.getPrixUnitaireHT();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntHt_WhenPrixUnitaireIsNull() {
        // Given
        Besoin besoin = AchatTestDataUtil.createBesoinWhenPrixUnitaireIsNull();

        // When
        Double result = besoin.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTva() {
        // Given
        Besoin besoin = AchatTestDataUtil.createBesoin();

        // When
        Double result = besoin.getMntTva();

        // Then
        assertEquals(10.0 * 5 * 0.2, result);
    }

    @Test
    public void testGetMntTtc() {
        // Given
        Besoin besoin = AchatTestDataUtil.createBesoin();

        // When
        Double result = besoin.getMntTtc();

        // Then
        assertEquals(10.0 * 5 * 1.2, result);
    }
}
