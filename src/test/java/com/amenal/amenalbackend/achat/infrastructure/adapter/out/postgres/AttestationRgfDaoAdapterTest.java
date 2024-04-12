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

import com.amenal.amenalbackend.achat.core.domain.AttestationRgf;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.AttestationRgfEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.AttestationRgfRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AttestationRgfDaoAdapterTest {

    @Mock
    private AttestationRgfRepository attestationRgfRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private AttestationRgfDaoAdapter attestationRgfDaoAdapter;

    @Test
    void testFindAttestationRgfById() {
        AttestationRgfEntity entity = new AttestationRgfEntity();
        entity.setId(1);

        when(attestationRgfRepository.findById(1)).thenReturn(Optional.of(entity));

        AttestationRgf result = attestationRgfDaoAdapter.findAttestationRgfById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllAttestationRgfs() {
        when(attestationRgfRepository.findAll()).thenReturn(Collections.emptyList());

        List<AttestationRgf> result = attestationRgfDaoAdapter.findAllAttestationRgfs();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveAttestationRgf() {
        AttestationRgf attestationRgf = new AttestationRgf();
        attestationRgf.setId(1);
        AttestationRgfEntity entity = new AttestationRgfEntity();
        entity.setId(1);

        when(attestationRgfRepository.save(any(AttestationRgfEntity.class))).thenReturn(entity);

        AttestationRgf result = attestationRgfDaoAdapter.saveAttestationRgf(attestationRgf);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateAttestationRgf() {
        AttestationRgf attestationRgf = new AttestationRgf();
        attestationRgf.setId(1);
        AttestationRgfEntity entity = new AttestationRgfEntity();
        entity.setId(1);
        when(attestationRgfRepository.findById(1)).thenReturn(Optional.of(entity));
        when(attestationRgfRepository.save(any(AttestationRgfEntity.class))).thenReturn(entity);

        AttestationRgf result = attestationRgfDaoAdapter.updateAttestationRgf(attestationRgf);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteAttestationRgf() {
        AttestationRgfEntity entity = new AttestationRgfEntity();
        entity.setId(1);
        when(attestationRgfRepository.findById(1)).thenReturn(Optional.of(entity));

        attestationRgfDaoAdapter.deleteAttestationRgf(1);

        verify(attestationRgfRepository, times(1)).delete(entity);
    }
}
