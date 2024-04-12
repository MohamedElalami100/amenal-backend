package com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amenal.amenalbackend.achat.infrastructure.dto.FactureDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.amenal.amenalbackend.achat.core.domain.Facture;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.FactureEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.FactureRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class FactureDaoAdapterTest {

    @Mock
    private FactureRepository factureRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private FactureDaoAdapter factureDaoAdapter;

    @Test
    void testFindFactureById() {
        FactureEntity entity = new FactureEntity();
        entity.setId(1);

        when(factureRepository.findById(1)).thenReturn(Optional.of(entity));

        FactureDto result = factureDaoAdapter.findFactureById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllFactures() {
        when(factureRepository.findAll()).thenReturn(Collections.emptyList());

        List<FactureDto> result = factureDaoAdapter.findAllFactures();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveFacture() {
        Facture facture = new Facture();
        facture.setId(1);
        FactureEntity entity = new FactureEntity();
        entity.setId(1);

        when(factureRepository.save(any(FactureEntity.class))).thenReturn(entity);

        Facture result = factureDaoAdapter.saveFacture(facture);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateFacture() {
        Facture facture = new Facture();
        facture.setId(1);
        FactureEntity entity = new FactureEntity();
        entity.setId(1);
        when(factureRepository.findById(1)).thenReturn(Optional.of(entity));
        when(factureRepository.save(any(FactureEntity.class))).thenReturn(entity);

        Facture result = factureDaoAdapter.updateFacture(facture);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteFacture() {
        FactureEntity entity = new FactureEntity();
        entity.setId(1);
        when(factureRepository.findById(1)).thenReturn(Optional.of(entity));

        factureDaoAdapter.deleteFacture(1);

        verify(factureRepository, times(1)).delete(entity);
    }
}
