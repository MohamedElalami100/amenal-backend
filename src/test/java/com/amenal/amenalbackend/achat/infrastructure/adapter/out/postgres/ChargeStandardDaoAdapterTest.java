package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.ChargeStandard;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.ChargeStandardEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.ChargeStandardRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ChargeStandardDaoAdapterTest {

    @Mock
    private ChargeStandardRepository chargeStandardRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ChargeStandardDaoAdapter chargeStandardDaoAdapter;

    @Test
    void testFindChargeStandardById() {
        ChargeStandardEntity entity = new ChargeStandardEntity();
        entity.setId(1);

        when(chargeStandardRepository.findById(1)).thenReturn(Optional.of(entity));

        ChargeStandard result = chargeStandardDaoAdapter.findChargeStandardById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllChargeStandards() {
        when(chargeStandardRepository.findAll()).thenReturn(Collections.emptyList());

        List<ChargeStandard> result = chargeStandardDaoAdapter.findAllChargeStandards();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveChargeStandard() {
        ChargeStandard chargeStandard = new ChargeStandard();
        chargeStandard.setId(1);
        ChargeStandardEntity entity = new ChargeStandardEntity();
        entity.setId(1);

        when(chargeStandardRepository.save(any(ChargeStandardEntity.class))).thenReturn(entity);

        ChargeStandard result = chargeStandardDaoAdapter.saveChargeStandard(chargeStandard);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateChargeStandard() {
        ChargeStandard chargeStandard = new ChargeStandard();
        chargeStandard.setId(1);
        ChargeStandardEntity entity = new ChargeStandardEntity();
        entity.setId(1);
        when(chargeStandardRepository.findById(1)).thenReturn(Optional.of(entity));
        when(chargeStandardRepository.save(any(ChargeStandardEntity.class))).thenReturn(entity);

        ChargeStandard result = chargeStandardDaoAdapter.updateChargeStandard(chargeStandard);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteChargeStandard() {
        ChargeStandardEntity entity = new ChargeStandardEntity();
        entity.setId(1);
        when(chargeStandardRepository.findById(1)).thenReturn(Optional.of(entity));

        chargeStandardDaoAdapter.deleteChargeStandard(1);

        verify(chargeStandardRepository, times(1)).delete(entity);
    }
}
