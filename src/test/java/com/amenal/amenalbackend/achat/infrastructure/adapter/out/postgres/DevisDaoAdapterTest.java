package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.DevisDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.Devis;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.DevisEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.DevisRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DevisDaoAdapterTest {

    @Mock
    private DevisRepository devisRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private DevisDaoAdapter devisDaoAdapter;

    @Test
    void testFindDevisById() {
        DevisEntity entity = new DevisEntity();
        entity.setId(1);

        when(devisRepository.findById(1)).thenReturn(Optional.of(entity));

        DevisDto result = devisDaoAdapter.findDevisById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllDeviss() {
        when(devisRepository.findAll()).thenReturn(Collections.emptyList());

        List<DevisDto> result = devisDaoAdapter.findAllDeviss();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveDevis() {
        Devis devis = new Devis();
        devis.setId(1);
        DevisEntity entity = new DevisEntity();
        entity.setId(1);

        when(devisRepository.save(any(DevisEntity.class))).thenReturn(entity);

        Devis result = devisDaoAdapter.saveDevis(devis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateDevis() {
        Devis devis = new Devis();
        devis.setId(1);
        DevisEntity entity = new DevisEntity();
        entity.setId(1);
        when(devisRepository.findById(1)).thenReturn(Optional.of(entity));
        when(devisRepository.save(any(DevisEntity.class))).thenReturn(entity);

        Devis result = devisDaoAdapter.updateDevis(devis);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteDevis() {
        DevisEntity entity = new DevisEntity();
        entity.setId(1);
        when(devisRepository.findById(1)).thenReturn(Optional.of(entity));

        devisDaoAdapter.deleteDevis(1);

        verify(devisRepository, times(1)).delete(entity);
    }
}
