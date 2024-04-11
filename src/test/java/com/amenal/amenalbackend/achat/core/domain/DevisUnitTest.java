package com.amenal.amenalbackend.achat.core.domain;

import com.amenal.amenalbackend.achat.AchatTestDataUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DevisUnitTest {

    @Test
    public void testGetMntHt_WhenDetailDevisIsNull() {
        // Given
        Devis devis = AchatTestDataUtil.createDevisWithNullDetailDevis();

        // When
        Double result = devis.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTva_WhenDetailDevisIsNull() {
        // Given
        Devis devis = AchatTestDataUtil.createDevisWithNullDetailDevis();

        // When
        Double result = devis.getMntTva();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTtc_WhenDetailDevisIsNull() {
        // Given
        Devis devis = AchatTestDataUtil.createDevisWithNullDetailDevis();

        // When
        Double result = devis.getMntTtc();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntHt() {
        // Given
        List<DetailDevis> detailDevisList = AchatTestDataUtil.createDetailDevisList();

        Devis devis = AchatTestDataUtil.createDevisWithDetailDevis(detailDevisList);

        // When
        Double result = devis.getMntHt();

        // Then
        assertEquals(5.0 * 10.0 + 3.0 * 15.0, result);
    }

    @Test
    public void testGetMntTva() {
        // Given
        List<DetailDevis> detailDevisList = AchatTestDataUtil.createDetailDevisList();

        Devis devis = AchatTestDataUtil.createDevisWithDetailDevis(detailDevisList);

        // When
        Double result = devis.getMntTva();

        // Then
        assertEquals((5.0 * 10.0 * 0.2) + (3.0 * 15.0 * 0.2), result);
    }

    @Test
    public void testGetMntTtc() {
        // Given
        List<DetailDevis> detailDevisList = AchatTestDataUtil.createDetailDevisList();

        Devis devis = AchatTestDataUtil.createDevisWithDetailDevis(detailDevisList);

        // When
        Double result = devis.getMntTtc();

        // Then
        assertEquals((5.0 * 10.0 * 1.2) + (3.0 * 15.0 * 1.2), result);
    }
}
