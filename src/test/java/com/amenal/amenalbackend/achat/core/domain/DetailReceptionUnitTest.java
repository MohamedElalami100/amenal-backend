package com.amenal.amenalbackend.achat.core.domain;

import com.amenal.amenalbackend.achat.AchatTestDataUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DetailReceptionUnitTest {

    @Test
    public void testGetMntHt_WhenDetailCommandeIsNull() {
        // Given
        DetailReception detailReception = AchatTestDataUtil.createDetailReceptionWithValues(null, 5.0);

        // When
        Double result = detailReception.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntHt_WhenPrixUnitaireIsNull() {
        // Given
        DetailReception detailReception = AchatTestDataUtil.createDetailReceptionWithNullValues(null, 5.0);

        // When
        Double result = detailReception.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntHt_WhenQteIsNull() {
        // Given
        DetailReception detailReception = AchatTestDataUtil.createDetailReceptionWithNullValues(10.0, null);

        // When
        Double result = detailReception.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTHt() {
        // Given
        DetailReception detailReception = AchatTestDataUtil.createDetailReceptionWithValues(10.0, 5.0);

        // When
        Double result = detailReception.getMntHt();

        // Then
        assertEquals(10.0 * 5 , result);
    }
    @Test
    public void testGetMntTva() {
        // Given
        DetailReception detailReception = AchatTestDataUtil.createDetailReceptionWithValues(10.0, 5.0);

        // When
        Double result = detailReception.getMntTva();

        // Then
        assertEquals(10.0 * 5 * 0.2, result);
    }

    @Test
    public void testGetMntTtc() {
        // Given
        DetailReception detailReception = AchatTestDataUtil.createDetailReceptionWithValues(10.0, 5.0);

        // When
        Double result = detailReception.getMntTtc();

        // Then
        assertEquals(10.0 * 5 * 1.2, result);
    }
}
