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

import com.amenal.amenalbackend.achat.core.domain.CompteBanquaire;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.entities.CompteBanquaireEntity;
import com.amenal.amenalbackend.achat.infrastructure.adapter.out.postgres.repositories.CompteBanquaireRepository;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CompteBanquaireDaoAdapterTest {

    @Mock
    private CompteBanquaireRepository compteBanquaireRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private CompteBanquaireDaoAdapter compteBanquaireDaoAdapter;

    @Test
    void testFindCompteBanquaireById() {
        CompteBanquaireEntity entity = new CompteBanquaireEntity();
        entity.setId(1);

        when(compteBanquaireRepository.findById(1)).thenReturn(Optional.of(entity));

        CompteBanquaire result = compteBanquaireDaoAdapter.findCompteBanquaireById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAllCompteBanquaires() {
        when(compteBanquaireRepository.findAll()).thenReturn(Collections.emptyList());

        List<CompteBanquaire> result = compteBanquaireDaoAdapter.findAllCompteBanquaires();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveCompteBanquaire() {
        CompteBanquaire compteBanquaire = new CompteBanquaire();
        compteBanquaire.setId(1);
        CompteBanquaireEntity entity = new CompteBanquaireEntity();
        entity.setId(1);

        when(compteBanquaireRepository.save(any(CompteBanquaireEntity.class))).thenReturn(entity);

        CompteBanquaire result = compteBanquaireDaoAdapter.saveCompteBanquaire(compteBanquaire);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateCompteBanquaire() {
        CompteBanquaire compteBanquaire = new CompteBanquaire();
        compteBanquaire.setId(1);
        CompteBanquaireEntity entity = new CompteBanquaireEntity();
        entity.setId(1);
        when(compteBanquaireRepository.findById(1)).thenReturn(Optional.of(entity));
        when(compteBanquaireRepository.save(any(CompteBanquaireEntity.class))).thenReturn(entity);

        CompteBanquaire result = compteBanquaireDaoAdapter.updateCompteBanquaire(compteBanquaire);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteCompteBanquaire() {
        CompteBanquaireEntity entity = new CompteBanquaireEntity();
        entity.setId(1);
        when(compteBanquaireRepository.findById(1)).thenReturn(Optional.of(entity));

        compteBanquaireDaoAdapter.deleteCompteBanquaire(1);

        verify(compteBanquaireRepository, times(1)).delete(entity);
    }
}
