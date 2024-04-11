package com.amenal.amenalbackend.achat.core.domain;

import com.amenal.amenalbackend.achat.AchatTestDataUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReceptionUnitTest {

    @Test
    public void testGetMntHt_WhenDetailReceptionsIsNull() {
        // Given
        Reception reception = AchatTestDataUtil.createReceptionWithNullDetailReceptions();

        // When
        Double result = reception.getMntHt();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTva_WhenDetailReceptionsIsNull() {
        // Given
        Reception reception = AchatTestDataUtil.createReceptionWithNullDetailReceptions();

        // When
        Double result = reception.getMntTva();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntTtc_WhenDetailReceptionsIsNull() {
        // Given
        Reception reception = AchatTestDataUtil.createReceptionWithNullDetailReceptions();

        // When
        Double result = reception.getMntTtc();

        // Then
        assertNull(result);
    }

    @Test
    public void testGetMntHt() {
        // Given
        List<DetailReception> detailReceptionList = AchatTestDataUtil.createDetailReceptionList();
        Reception reception = AchatTestDataUtil.createReceptionWithDetailReceptions(detailReceptionList);

        // When
        Double result = reception.getMntHt();

        // Then
        assertEquals(5.0 * 10.0 + 3.0 * 15.0, result);
    }

    @Test
    public void testGetMntTva() {
        // Given
        List<DetailReception> detailReceptionList = AchatTestDataUtil.createDetailReceptionList();
        Reception reception = AchatTestDataUtil.createReceptionWithDetailReceptions(detailReceptionList);

        // When
        Double result = reception.getMntTva();

        // Then
        assertEquals((5.0 * 10.0 * 0.2) + (3.0 * 15.0 * 0.2), result);
    }

    @Test
    public void testGetMntTtc() {
        // Given
        List<DetailReception> detailReceptionList = AchatTestDataUtil.createDetailReceptionList();
        Reception reception = AchatTestDataUtil.createReceptionWithDetailReceptions(detailReceptionList);

        // When
        Double result = reception.getMntTtc();

        // Then
        assertEquals((5.0 * 10.0 * 1.2) + (3.0 * 15.0 * 1.2), result);
    }
}
