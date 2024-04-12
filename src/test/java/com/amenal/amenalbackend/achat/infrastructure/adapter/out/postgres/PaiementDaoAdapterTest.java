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

import com.amenal.amenalbackend.achat.core.domain.Paiement;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.PaiementEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.PaiementRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PaiementDaoAdapterTest {

    @Mock
    private PaiementRepository paiementRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private PaiementDaoAdapter paiementDaoAdapter;

    @Test
    void testFindPaiementById() {
        PaiementEntity entity = new PaiementEntity();
        entity.setId(1);

        when(paiementRepository.findById(1)).thenReturn(Optional.of(entity));

        Paiement result = paiementDaoAdapter.findPaiementById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllPaiements() {
        when(paiementRepository.findAll()).thenReturn(Collections.emptyList());

        List<Paiement> result = paiementDaoAdapter.findAllPaiements();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSavePaiement() {
        Paiement paiement = new Paiement();
        paiement.setId(1);
        PaiementEntity entity = new PaiementEntity();
        entity.setId(1);

        when(paiementRepository.save(any(PaiementEntity.class))).thenReturn(entity);

        Paiement result = paiementDaoAdapter.savePaiement(paiement);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdatePaiement() {
        Paiement paiement = new Paiement();
        paiement.setId(1);
        PaiementEntity entity = new PaiementEntity();
        entity.setId(1);
        when(paiementRepository.findById(1)).thenReturn(Optional.of(entity));
        when(paiementRepository.save(any(PaiementEntity.class))).thenReturn(entity);

        Paiement result = paiementDaoAdapter.updatePaiement(paiement);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeletePaiement() {
        PaiementEntity entity = new PaiementEntity();
        entity.setId(1);
        when(paiementRepository.findById(1)).thenReturn(Optional.of(entity));

        paiementDaoAdapter.deletePaiement(1);

        verify(paiementRepository, times(1)).delete(entity);
    }
}
